package com.elleined.philippinelocationapi.repository.province;

import com.elleined.philippinelocationapi.model.province.Province;
import com.elleined.philippinelocationapi.model.region.Region;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProvinceRepository extends JpaRepository<Province, Integer> {

    @Query("SELECT p FROM Province p WHERE p.region = :region")
    List<Province> findAll(@Param("region") Region region);

    @Query("SELECT p FROM Province p WHERE p.region = :region AND p.id = :id")
    Optional<Province> findById(@Param("region") Region region,
                                @Param("id") int id);
}