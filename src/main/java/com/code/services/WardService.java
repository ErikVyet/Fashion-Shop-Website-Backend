package com.code.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.code.dtos.WardDto;
import com.code.models.Ward;
import com.code.repositories.ProvinceRepository;
import com.code.repositories.WardRepository;

@Service
public class WardService {

    protected final WardRepository wardRepository;
    protected final ProvinceRepository provinceRepository;

    public WardService(WardRepository wardRepository, ProvinceRepository provinceService) {
        this.wardRepository = wardRepository;
        this.provinceRepository = provinceService;
    }

    @Transactional(readOnly = true)
    public List<WardDto> readWards() {
        return this.wardRepository.findAll().stream().map(WardDto::toDto).toList();
    }

    @Transactional(readOnly = true)
    public WardDto getWard(String code) {
        return this.wardRepository.findById(code).map(WardDto::toDto).orElse(null);
    }

    @Transactional
    public boolean createWard(WardDto wardDto) {
        try {
            if (this.wardRepository.existsById(wardDto.getCode())) {
                return false;
            }
            Ward ward = WardDto.toEntity(wardDto);
            ward.setProvince(this.provinceRepository.getReferenceById(wardDto.getProvince().getCode()));
            this.wardRepository.save(ward);
            return true;
        }
        catch (Exception exception) {
            System.out.println(exception.getMessage());
            return false;
        }
    }

    @Transactional
    public boolean deleteWard(String code) {
        try {
            this.wardRepository.deleteById(code);
            return true;
        }
        catch (Exception exception) {
            System.out.println(exception.getMessage());
            return false;
        }
    }

    @Transactional
    public boolean updateWard(WardDto wardDto) {
        try {
            if (wardDto == null) {
                return false;
            }
            Ward ward = WardDto.toEntity(wardDto);
            ward.setProvince(this.provinceRepository.getReferenceById(wardDto.getProvince().getCode()));
            this.wardRepository.save(ward);
            return true;
        }
        catch (Exception exception) {
            System.out.println(exception.getMessage());
            return false;
        }
    }

    @Transactional
    public boolean updateWardActive(String code, boolean active) {
        try {
            Ward ward = this.wardRepository.findById(code).orElse(null);
            if (ward == null) {
                return false;
            }
            ward.setActive(active);
            this.wardRepository.save(ward);
            return true;
        }
        catch (Exception exception) {
            System.out.println(exception.getMessage());
            return false;
        }
    }

}