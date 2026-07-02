package com.code.dtos;

import org.hibernate.validator.constraints.Length;

import com.code.models.Ward;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class WardDto {

    @NotBlank(message = "Mã phường không được để trống")
    @Length(max = 10, message = "Mã phường không được vượt quá 10 ký tự")
    private String code;

    @NotBlank(message = "Tên phường không được để trống")
    @Length(max = 255, message = "Tên phường không được vượt quá 255 ký tự")
    private String name;

    @NotNull(message = "Trạng thái của phường không được để trống")
    private Boolean is_active;

    @NotNull(message = "Tỉnh thành của phường không được để trống")
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

    public Boolean isIs_active() {
        return is_active;
    }

    public void setIs_active(Boolean is_active) {
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