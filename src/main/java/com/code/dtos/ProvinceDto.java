package com.code.dtos;

import java.io.Serializable;

import com.code.models.Province;

public class ProvinceDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private String code;
    private String name;
    private boolean is_active;
    
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

    public boolean isIs_active() {
        return is_active;
    }

    public void setIs_active(boolean is_active) {
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