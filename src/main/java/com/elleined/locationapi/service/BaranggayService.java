package com.elleined.locationapi.service;

import com.elleined.locationapi.exception.ResourceNotFoundException;
import com.elleined.locationapi.model.location.Baranggay;
import com.elleined.locationapi.model.location.City;
import com.elleined.locationapi.repository.BaranggayRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class BaranggayService {

    private final BaranggayRepository baranggayRepository;

    void save(Baranggay baranggay) {
        baranggayRepository.save(baranggay);
        log.debug("Baranggay with id of {} and with name of {} saved successfully", baranggay.getId(), baranggay.getLocationName());
    }

    void saveAll(Set<Baranggay> baranggays) {
        baranggayRepository.saveAll(baranggays);
    }

    Baranggay getById(int id) throws ResourceNotFoundException {
        return baranggayRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Baranggay with id of " + id + " does not exists!"));
    }

    Set<Baranggay> getAllByCity(City city) {
        return city.getBaranggays();
    }

    void delete(int id) {
        baranggayRepository.deleteById(id);
        log.debug("Baranggay with id of {} deleted successfully!", id);
    }

    void update(Baranggay baranggay, City city, String name) {
        baranggay.setCity(city);
        baranggay.setLocationName(name);
        log.debug("Baranggay with id of {} updated with new city of {} and new name of {}", baranggay.getId(), city.getLocationName(), name);
    }
}
