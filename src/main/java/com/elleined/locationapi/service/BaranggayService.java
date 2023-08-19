package com.elleined.locationapi.service;

import com.elleined.locationapi.dto.BaranggayDTO;
import com.elleined.locationapi.exception.ResourceNotFoundException;
import com.elleined.locationapi.model.Baranggay;
import com.elleined.locationapi.repository.BaranggayRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
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
        log.debug("Saving all baranggays success");
    }

    public Baranggay getById(int id) throws ResourceNotFoundException {
        return baranggayRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Baranggay with id of " + id + " does not exists!"));
    }

    public boolean isAlreadyExists(BaranggayDTO baranggayDTO) {
        return baranggayRepository.existsById(baranggayDTO.getId());
    }

    public boolean isAlreadyExists(Collection<BaranggayDTO> baranggayDTOS) {
        return baranggayDTOS.stream().anyMatch(this::isAlreadyExists);
    }

    public List<Baranggay> searchByLocationName(String locationName) {
        return baranggayRepository.searchByLocationName(locationName);
    }
}
