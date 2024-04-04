package com.elleined.philippinelocationapi.service.baranggay;

import com.elleined.philippinelocationapi.dto.BaranggayDTO;
import com.elleined.philippinelocationapi.exception.AlreadyExistsException;
import com.elleined.philippinelocationapi.exception.ResourceNotFoundException;
import com.elleined.philippinelocationapi.mapper.BaranggayMapper;
import com.elleined.philippinelocationapi.model.baranggay.Baranggay;
import com.elleined.philippinelocationapi.model.city.City;
import com.elleined.philippinelocationapi.repository.baranggay.BaranggayRepository;
import com.elleined.philippinelocationapi.service.city.CityService;
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
class BaranggayServiceImpl implements BaranggayService {
    private final BaranggayRepository baranggayRepository;
    private final BaranggayMapper baranggayMapper;

    private final CityService cityService;

    @Override
    public List<Baranggay> saveAll(List<BaranggayDTO> baranggayDTOS) throws AlreadyExistsException {
        if (baranggayDTOS.stream().anyMatch(this::isAlreadyExists)) throw new AlreadyExistsException("Cannot save all baranggays! because onr of the provided id already exists!");

        List<Baranggay> baranggays = baranggayDTOS.stream()
                .map(baranggayDTO -> {
                        City city = cityService.getById(baranggayDTO.getCityId());
                        return baranggayMapper.toEntity(baranggayDTO, city);
                }).toList();

        baranggayRepository.saveAll(baranggays);
        log.debug("Saving all baranggays success...");
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
