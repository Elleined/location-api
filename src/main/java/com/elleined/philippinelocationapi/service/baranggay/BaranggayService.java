package com.elleined.philippinelocationapi.service.baranggay;

import com.elleined.philippinelocationapi.model.baranggay.Baranggay;
import com.elleined.philippinelocationapi.model.city.City;
import com.elleined.philippinelocationapi.model.province.Province;
import com.elleined.philippinelocationapi.model.region.Region;
import com.elleined.philippinelocationapi.service.LocationService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BaranggayService extends LocationService<Baranggay> {
    Page<Baranggay> getAllBy(Region region, Province province, City city, Pageable pageable);
    Page<Baranggay> findAllByName(Region region, Province province, City city, String name, Pageable pageable);
}
