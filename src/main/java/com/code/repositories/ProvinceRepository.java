package com.code.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.code.models.Province;

public interface ProvinceRepository extends JpaRepository<Province, String> {

}