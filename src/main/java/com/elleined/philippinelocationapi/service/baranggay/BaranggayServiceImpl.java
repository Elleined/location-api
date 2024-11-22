package com.elleined.philippinelocationapi.service.baranggay;

import com.elleined.philippinelocationapi.exception.resource.ResourceNotFoundException;
import com.elleined.philippinelocationapi.model.baranggay.Baranggay;
import com.elleined.philippinelocationapi.model.city.City;
import com.elleined.philippinelocationapi.model.province.Province;
import com.elleined.philippinelocationapi.model.region.Region;
import com.elleined.philippinelocationapi.repository.baranggay.BaranggayRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class BaranggayServiceImpl implements BaranggayService {
    private final BaranggayRepository baranggayRepository;

    @Override
    public List<Baranggay> getAllBy(Region region, Province province, City city) {
        return baranggayRepository.findAll(region, province, city);
    }

    @Override
    public Baranggay getById(Region region, Province province, City city, int id) {
        if (!region.has(id))
            throw new ResourceNotFoundException("Region with id of " + region.getId() + " doesn't have province with id of " + province.getId());

        if (!province.has(id))
            throw new ResourceNotFoundException("Province with id of " + province.getId() + " doesn't have city with id of " + city.getId());

        if (!city.has(id))
            throw new ResourceNotFoundException("City with id of " + city.getId() + " doesn't have baranggay with id of " + id);

        return baranggayRepository.findById(region, province, city, id).orElseThrow(() -> new ResourceNotFoundException("Baranggay with id of " + id + " does not exists!"));
    }
}
