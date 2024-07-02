package com.elleined.philippinelocationapi.service.baranggay;

import com.elleined.philippinelocationapi.model.baranggay.Baranggay;
import com.elleined.philippinelocationapi.model.city.City;
import com.elleined.philippinelocationapi.service.LocationService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BaranggayService extends LocationService<Baranggay> {
    Page<Baranggay> getAllBy(City city, Pageable pageable);
}
