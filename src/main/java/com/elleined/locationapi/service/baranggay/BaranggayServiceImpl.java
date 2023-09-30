package com.elleined.locationapi.service.baranggay;

import com.elleined.locationapi.dto.BaranggayDTO;
import com.elleined.locationapi.exception.AlreadyExistsException;
import com.elleined.locationapi.exception.ResourceNotFoundException;
import com.elleined.locationapi.mapper.BaranggayMapper;
import com.elleined.locationapi.model.Baranggay;
import com.elleined.locationapi.model.City;
import com.elleined.locationapi.repository.BaranggayRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class BaranggayServiceImpl implements BaranggayService {
    private final BaranggayRepository baranggayRepository;
    private final BaranggayMapper baranggayMapper;

    @Override
    public Baranggay save(BaranggayDTO baranggayDTO) {
        if (isAlreadyExists(baranggayDTO)) throw new AlreadyExistsException("Baranggay with id of " + baranggayDTO.getId() + " already exists!");
        Baranggay baranggay = baranggayMapper.toEntity(baranggayDTO);
        baranggayRepository.save(baranggay);
        log.debug("Baranggay with id of {} and with name of {} saved successfully", baranggay.getId(), baranggay.getLocationName());
        return baranggay;
    }

    @Override
    public List<Baranggay> saveAll(List<BaranggayDTO> baranggayDTOS) {
        if (isAlreadyExists(baranggayDTOS)) throw new AlreadyExistsException("One of the provided id already exists!");
        List<Baranggay> baranggays = baranggayDTOS.stream()
                .map(baranggayMapper::toEntity)
                .toList();
        baranggayRepository.saveAll(baranggays);
        log.debug("Saving all baranggays success");
        return baranggays;
    }

    @Override
    public Baranggay getById(int id) throws ResourceNotFoundException {
        return baranggayRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Baranggay with id of " + id + " does not exists!"));
    }

    @Override
    public boolean isAlreadyExists(BaranggayDTO baranggayDTO) {
        return baranggayRepository.existsById(baranggayDTO.getId());
    }

    @Override
    public boolean isAlreadyExists(List<BaranggayDTO> baranggayDTOS) {
        return baranggayDTOS.stream().anyMatch(this::isAlreadyExists);
    }

    @Override
    public List<Baranggay> searchByLocationName(String locationName) {
        return baranggayRepository.searchByLocationName(locationName).stream()
                .sorted(Comparator.comparing(Baranggay::getLocationName))
                .toList();
    }

    @Override
    public List<Baranggay> getAllBy(City city) {
        return city.getBaranggays().stream()
                .sorted(Comparator.comparing(Baranggay::getLocationName))
                .toList();
    }
}
