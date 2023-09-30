package com.elleined.locationapi.service.baranggay;

import com.elleined.locationapi.dto.BaranggayDTO;
import com.elleined.locationapi.model.Baranggay;
import com.elleined.locationapi.model.City;
import com.elleined.locationapi.service.LocationService;

import java.util.List;

public interface BaranggayService extends LocationService<Baranggay, BaranggayDTO> {
    List<Baranggay> getAllBy(City city);
}
