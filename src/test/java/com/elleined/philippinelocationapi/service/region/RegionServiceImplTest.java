package com.elleined.philippinelocationapi.service.region;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.elleined.philippinelocationapi.model.region.Region;
import com.elleined.philippinelocationapi.repository.region.RegionRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class RegionServiceImplTest {

    @Mock
    private RegionRepository regionRepository;

    @InjectMocks
    private RegionServiceImpl regionService;

    @Test
    void save() {
        // Expected Value

        // Mock data
        Region expected = new Region();

        // Set up method

        // Stubbing methods
        when(regionRepository.save(any(Region.class))).thenReturn(expected);

        // Calling the method
        Region actual = regionService.save(expected);

        // Behavior Verifications
        verify(regionRepository).save(any(Region.class));

        // Assertions
        assertEquals(expected, actual);
    }

    @Test
    void saveAll() {
        // Expected Value

        // Mock data
        Region region1 = new Region();
        Region region2 = new Region();

        // Set up method
        List<Region> expectedRegions = List.of(region1, region2);

        // Stubbing methods
        when(regionRepository.saveAll(anyList())).thenReturn(expectedRegions);

        // Calling the method
        List<Region> actualRegions = regionService.saveAll(expectedRegions);

        // Behavior Verifications
        verify(regionRepository).saveAll(anyList());

        // Assertions
        assertIterableEquals(expectedRegions, actualRegions);
    }

    @Test
    void existsById() {
        // Expected Value

        // Mock data

        // Set up method

        // Stubbing methods
        when(regionRepository.existsById(anyInt())).thenReturn(true);

        // Calling the method
        boolean isExists = regionService.existsById(1);

        // Behavior Verifications
        verify(regionRepository).existsById(anyInt());

        // Assertions
        assertTrue(isExists);
    }

    @Test
    void getAll() {
        // Expected Value

        // Mock data
        Region region1 = new Region();
        Region region2 = new Region();

        // Set up method
        List<Region> expectedRegions = List.of(region1, region2);

        // Stubbing methods
        when(regionRepository.findAll()).thenReturn(expectedRegions);

        // Calling the method
        List<Region> actualRegions = regionService.getAll();

        // Behavior Verifications
        verify(regionRepository).findAll();

        // Assertions
        assertIterableEquals(expectedRegions, actualRegions);
    }

    @Test
    void getAllById() {
        // Expected Value

        // Mock data

        // Set up method

        // Stubbing methods
        when(regionRepository.findAllById(anyList())).thenReturn(new ArrayList<>());

        // Calling the method
        List<Region> actualRegions = regionService.getAllById(List.of(1, 2));

        // Behavior Verifications
        verify(regionRepository).findAllById(anyList());

        // Assertions
    }

    @Test
    void searchByName() {
        // Expected Value

        // Mock data

        // Set up method

        // Stubbing methods

        // Calling the method

        // Behavior Verifications

        // Assertions
    }
}