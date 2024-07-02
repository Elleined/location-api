package com.elleined.philippinelocationapi.service.province;

import com.elleined.philippinelocationapi.model.province.Province;
import com.elleined.philippinelocationapi.model.region.Region;
import com.elleined.philippinelocationapi.service.LocationService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProvinceService extends LocationService<Province> {
    Page<Province> getAllBy(Region region, Pageable pageable);
}
