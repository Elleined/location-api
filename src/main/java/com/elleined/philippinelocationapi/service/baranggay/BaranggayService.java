package com.elleined.philippinelocationapi.service.baranggay;

import com.elleined.philippinelocationapi.dto.baranggay.BaranggayDTO;
import com.elleined.philippinelocationapi.model.baranggay.Baranggay;
import com.elleined.philippinelocationapi.model.city.City;
import com.elleined.philippinelocationapi.service.LocationService;

import java.util.List;

public interface BaranggayService extends LocationService<Baranggay, BaranggayDTO> {
    List<Baranggay> getAllBy(City city);
}
