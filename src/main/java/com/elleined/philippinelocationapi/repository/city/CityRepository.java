package com.elleined.philippinelocationapi.repository.city;

import com.elleined.philippinelocationapi.model.city.City;
import com.elleined.philippinelocationapi.model.province.Province;
import com.elleined.philippinelocationapi.model.region.Region;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CityRepository extends JpaRepository<City, Integer> {

    @Query("""
            SELECT
                c
            FROM
                City c
            WHERE
                c.province.region = :region
            AND
                c.province = :province
            """)
    List<City> findAll(@Param("region") Region region,
                       @Param("province") Province province);

    @Query("""
            SELECT
                c
            FROM
                City c
            WHERE
                c.province.region = :region
            AND
                c.province = :province
            AND
                c.id = :id
            """)
    Optional<City> findById(@Param("region") Region region,
                            @Param("province") Province province,
                            @Param("id") int id);
}