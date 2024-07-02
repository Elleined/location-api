package com.elleined.philippinelocationapi.service.baranggay;

import com.elleined.philippinelocationapi.exception.ResourceNotFoundException;
import com.elleined.philippinelocationapi.model.baranggay.Baranggay;
import com.elleined.philippinelocationapi.model.city.City;
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
    public Page<Baranggay> findAllByName(String name, Pageable pageable) {
        return baranggayRepository.findAllByName(name, pageable);
    }

    @Override
    public Page<Baranggay> getAllBy(City city, Pageable pageable) {
        return baranggayRepository.findAll(city, pageable);
    }
}
