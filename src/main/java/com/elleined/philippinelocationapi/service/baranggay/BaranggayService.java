package com.elleined.philippinelocationapi.service.baranggay;

import com.elleined.philippinelocationapi.dto.BaranggayDTO;
import com.elleined.philippinelocationapi.model.Baranggay;
import com.elleined.philippinelocationapi.model.City;
import com.elleined.philippinelocationapi.service.LocationService;

import java.util.List;

public interface BaranggayService extends LocationService<Baranggay, BaranggayDTO> {
    List<Baranggay> getAllBy(City city);
}