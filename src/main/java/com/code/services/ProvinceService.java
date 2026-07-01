package com.code.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.code.dtos.ProvinceDto;
import com.code.models.Province;
import com.code.repositories.ProvinceRepository;

@Service
public class ProvinceService {

    protected ProvinceRepository provinceRepository;

    public ProvinceService(ProvinceRepository provinceRepository) {
        this.provinceRepository = provinceRepository;
    }

    @Transactional(readOnly = true)
    public List<ProvinceDto> readProvinces() {
        return this.provinceRepository.findAll().stream().map(ProvinceDto::toDto).toList();
    }

    @Transactional(readOnly = true)
    public ProvinceDto getProvince(String code) {
        try {
            return ProvinceDto.toDto(this.provinceRepository.findById(code).get());
        }
        catch (Exception exception) {
            System.out.println(exception.getMessage());
            return null;
        }
    }

    @Transactional
    public boolean createProvince(ProvinceDto provinceDto) {
        try {
            if (this.provinceRepository.existsById(provinceDto.getCode())) {
                return false;
            }
            Province province = ProvinceDto.toEntity(provinceDto);
            this.provinceRepository.save(province);
            return true;
        }
        catch (Exception exception) {
            System.out.println(exception.getMessage());
            return false;
        }
    }

    @Transactional
    public boolean deleteProvince(String code) {
        try {
            Province province = this.provinceRepository.findById(code).get();
            this.provinceRepository.delete(province);
            return true;
        }
        catch (Exception exception) {
            System.out.println(exception);
            return false;
        }
    }

    @Transactional
    public boolean updateProvince(ProvinceDto provinceDto) {
        try {
            Province province = this.provinceRepository.findById(provinceDto.getCode()).get();
            province.setName(provinceDto.getName());
            province.setActive(provinceDto.isIs_active());
            this.provinceRepository.save(province);
            return true;
        }
        catch (Exception exception) {
            System.out.println(exception.getMessage());
            return false;
        }
    }

    public boolean updateProvinceActive(String code, boolean active) {
        try {
            Province province = this.provinceRepository.findById(code).get();
            province.setActive(active);
            this.provinceRepository.save(province);
            return true;
        }
        catch (Exception exception) {
            System.out.println(exception.getMessage());
            return false;
        }
    }

}