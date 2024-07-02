package com.elleined.philippinelocationapi.service.baranggay;

import com.elleined.philippinelocationapi.exception.resource.ResourceNotFoundException;
import com.elleined.philippinelocationapi.exception.resource.ResourceNotOwnedException;
import com.elleined.philippinelocationapi.model.baranggay.Baranggay;
import com.elleined.philippinelocationapi.model.city.City;
import com.elleined.philippinelocationapi.model.province.Province;
import com.elleined.philippinelocationapi.model.region.Region;
import com.elleined.philippinelocationapi.repository.baranggay.BaranggayRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class BaranggayServiceImpl implements BaranggayService {
    private final BaranggayRepository baranggayRepository;

    @Override
    public Baranggay getById(int id) throws ResourceNotFoundException {
        return baranggayRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Baranggay with id of " + id + " does not exists!"));
    }

    @Override
    public Page<Baranggay> findAllByName(Region region, Province province, City city, String name, Pageable pageable) {
        return baranggayRepository.findAllByName(region, province, city, name, pageable);
    }

    @Override
    public Page<Baranggay> getAllBy(Region region, Province province, City city, Pageable pageable) {
        return baranggayRepository.findAll(region, province, city, pageable);
    }
}
