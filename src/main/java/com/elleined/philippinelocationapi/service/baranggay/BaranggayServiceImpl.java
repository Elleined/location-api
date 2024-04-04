package com.elleined.philippinelocationapi.service.baranggay;

import com.elleined.philippinelocationapi.exception.ResourceNotFoundException;
import com.elleined.philippinelocationapi.model.Location;
import com.elleined.philippinelocationapi.model.baranggay.Baranggay;
import com.elleined.philippinelocationapi.model.city.City;
import com.elleined.philippinelocationapi.repository.baranggay.BaranggayRepository;
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

    @Override
    public Baranggay save(Baranggay baranggay) {
        return baranggayRepository.save(baranggay);
    }

    @Override
    public List<Baranggay> saveAll(List<Baranggay> baranggays) {
        return baranggayRepository.saveAll(baranggays);
    }

    @Override
    public boolean existsById(int id) {
        return baranggayRepository.existsById(id);
    }

    @Override
    public List<Baranggay> getAll() {
        return baranggayRepository.findAll();
    }

    @Override
    public Baranggay getById(int id) throws ResourceNotFoundException {
        return baranggayRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Baranggay with id of " + id + " does not exists!"));
    }

    @Override
    public List<Baranggay> getAllById(List<Integer> ids) {
        return baranggayRepository.findAllById(ids);
    }

    @Override
    public List<Baranggay> searchByName(String name) {
        return baranggayRepository.searchByLocationName(name).stream()
                .sorted(Comparator.comparing(Location::getName))
                .toList();
    }

    @Override
    public List<Baranggay> getAllBy(City city) {
        return city.getBaranggays().stream()
                .sorted(Comparator.comparing(Location::getName))
                .toList();
    }
}
