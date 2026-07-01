package com.code.dtos;

import org.hibernate.validator.constraints.Length;

import com.code.models.Ward;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class WardDto {

    @NotBlank
    @Length(max = 10)
    private String code;

    @NotBlank
    @Length(max = 255)
    private String name;

    @NotNull
    private boolean is_active;

    @Nullable
    private ProvinceDto province;

    public WardDto() { }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isIs_active() {
        return is_active;
    }

    public void setIs_active(boolean is_active) {
        this.is_active = is_active;
    }

    public ProvinceDto getProvince() {
        return province;
    }

    public void setProvince(ProvinceDto province) {
        this.province = province;
    }

    public static WardDto toDto(Ward ward) {
        WardDto dto = new WardDto();
        dto.setCode(ward.getCode());
        dto.setName(ward.getName());
        dto.setIs_active(ward.isActive());
        dto.setProvince(ward.getProvince() != null ? ProvinceDto.toDto(ward.getProvince()) : null);
        return dto;
    }

    public static Ward toEntity(WardDto dto) {
        Ward ward = new Ward();
        ward.setCode(dto.getCode());
        ward.setName(dto.getName());
        ward.setActive(dto.isIs_active());
        return ward;
    }

}