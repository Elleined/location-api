package com.elleined.philippinelocationapi.repository.baranggay;

import com.elleined.philippinelocationapi.model.baranggay.Baranggay;
import com.elleined.philippinelocationapi.model.city.City;
import com.elleined.philippinelocationapi.model.province.Province;
import com.elleined.philippinelocationapi.model.region.Region;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BaranggayRepository extends JpaRepository<Baranggay, Integer> {

    @Query("""
            SELECT
                b
            FROM
                Baranggay b
            WHERE
                b.name
            LIKE
                CONCAT('%', :name, '%')
            AND
                b.city.province.region = :region
            AND
                b.city.province = :province
            AND
                b.city = :city
            """)
    Page<Baranggay> findAllByName(@Param("region") Region region,
                                  @Param("province") Province province,
                                  @Param("city") City city,
                                  @Param("name") String name,
                                  Pageable pageable);


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
    Page<Baranggay> findAll(@Param("region") Region region,
                            @Param("province") Province province,
                            @Param("city") City city,
                            Pageable pageable);
}