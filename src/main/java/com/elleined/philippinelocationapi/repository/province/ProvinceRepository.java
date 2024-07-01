package com.elleined.philippinelocationapi.repository.province;

import com.elleined.philippinelocationapi.model.province.Province;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProvinceRepository extends JpaRepository<Province, Integer> {

    @Query("SELECT p FROM Province p WHERE p.name LIKE CONCAT('%', :name, '%') ORDER BY p.id")
    Page<Province> findAllByName(@Param("name") String name, Pageable pageable);
}