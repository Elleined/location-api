package com.elleined.philippinelocationapi.service.province;

import com.elleined.philippinelocationapi.model.province.Province;
import com.elleined.philippinelocationapi.model.region.Region;

import java.util.List;

public interface ProvinceService {
    List<Province> getAllBy(Region region);
    Province getById(Region region, int id);
}
