package com.elleined.philippinelocationapi.repository.province;

import com.elleined.philippinelocationapi.model.province.Province;
import com.elleined.philippinelocationapi.model.region.Region;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProvinceRepository extends JpaRepository<Province, Integer> {

    @Query("""
            SELECT
                p
            FROM
                Province p
            WHERE
                p.name
            LIKE
                CONCAT('%', :name, '%')
            AND
                p.region = :region
            """)
    Page<Province> findAllByName(@Param("region") Region region,
                                 @Param("name") String name,
                                 Pageable pageable);

    @Query("SELECT p FROM Province p WHERE p.region = :region")
    Page<Province> findAll(@Param("region") Region region,
                           Pageable pageable);

    @Query("SELECT p FROM Province p WHERE p.region = :region")
    List<Province> findAll(@Param("region") Region region);
}