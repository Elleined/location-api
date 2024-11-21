package com.elleined.philippinelocationapi.service.baranggay;

import com.elleined.philippinelocationapi.model.baranggay.Baranggay;
import com.elleined.philippinelocationapi.model.city.City;
import com.elleined.philippinelocationapi.model.province.Province;
import com.elleined.philippinelocationapi.model.region.Region;

import java.util.List;

public interface BaranggayService {
    List<Baranggay> getAllBy(Region region, Province province, City city);

    Baranggay getById(Region region, Province province, City city, int id);
}
