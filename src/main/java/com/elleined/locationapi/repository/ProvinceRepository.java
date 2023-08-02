package com.elleined.locationapi.repository;

import com.elleined.locationapi.model.location.Province;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProvinceRepository extends JpaRepository<Province, Integer> {
}