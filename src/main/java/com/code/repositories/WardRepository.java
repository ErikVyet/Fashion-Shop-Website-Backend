package com.code.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.code.models.Ward;

public interface WardRepository extends JpaRepository<Ward, String> {

}