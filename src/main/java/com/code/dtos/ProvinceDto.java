package com.code.dtos;

import java.io.Serializable;

import org.hibernate.validator.constraints.Length;

import com.code.models.Province;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ProvinceDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotBlank(message = "Mã tỉnh thành không được để trống")
    @Length(max = 10, message = "Mã tỉnh thành không được vượt quá 10 ký tự")
    private String code;

    @NotBlank(message = "Tên tỉnh thành không được để trống")
    @Length(max = 255, message = "Tên tỉnh thành không được vượt quá 255 ký tự")
    private String name;

    @NotNull(message = "Trạng thái của tỉnh thành không được để trống")
    private Boolean is_active;
    
    public ProvinceDto() { }

    public ProvinceDto(String code, String name, boolean is_active) {
        this.code = code;
        this.name = name;
        this.is_active = is_active;
    }

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

    public static ProvinceDto toDto(Province province) {
        return new ProvinceDto(province.getCode(), province.getName(), province.isActive());
    }

    public static Province toEntity(ProvinceDto provinceDto) {
        Province province = new Province();
        province.setCode(provinceDto.getCode());
        province.setName(provinceDto.getName());
        province.setActive(provinceDto.isIs_active());
        return province;
    }

}