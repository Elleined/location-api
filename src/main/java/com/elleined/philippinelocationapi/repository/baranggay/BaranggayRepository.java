package com.elleined.philippinelocationapi.repository.baranggay;

import com.elleined.philippinelocationapi.model.baranggay.Baranggay;
import com.elleined.philippinelocationapi.model.city.City;
import com.elleined.philippinelocationapi.model.province.Province;
import com.elleined.philippinelocationapi.model.region.Region;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BaranggayRepository extends JpaRepository<Baranggay, Integer> {

    @Query("""
            SELECT
                b
            FROM
                Baranggay b
            WHERE
                b.city.province.region = :region
            AND
                b.city.province = :province
            AND
                b.city = :city
            """)
    List<Baranggay> findAll(@Param("region") Region region,
                            @Param("province") Province province,
                            @Param("city") City city);

    @Query("""
            SELECT
                b
            FROM
                Baranggay b
            WHERE
                b.city.province.region = :region
            AND
                b.city.province = :province
            AND
                b.city = :city
            AND
                b.id = :id
            """)
    Optional<Baranggay> findById(@Param("region") Region region,
                                 @Param("province") Province province,
                                 @Param("city") City city,
                                 @Param("id") int id);
}