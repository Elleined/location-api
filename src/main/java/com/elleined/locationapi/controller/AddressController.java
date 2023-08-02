package com.elleined.locationapi.controller;

import com.elleined.locationapi.dto.AddressDTO;
import com.elleined.locationapi.service.LocationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping("/addresses")
public class AddressController {

    private final LocationService locationService;

    @PostMapping("/userAddress/{currentUserUUID}")
    public AddressDTO saveUserAddress(@PathVariable("currentUserUUID") String currentUserUUID,
                                      @Valid @RequestBody AddressDTO addressDTO) {

        return locationService.saveUserAddress(currentUserUUID, addressDTO);
    }

    @PostMapping("/deliveryAddresses/{currentUserUUID}")
    public AddressDTO saveDeliveryAddress(@PathVariable("currentUserUUID") String currentUserUUID,
                                          @Valid @RequestBody AddressDTO addressDTO) {
        return locationService.saveDeliveryAddress(currentUserUUID, addressDTO);
    }

    @GetMapping("/userAddress/{currentUserUUID}")
    public AddressDTO getAddress(@PathVariable("currentUserUUID") String currentUserUUID) {
        return locationService.getAddress(currentUserUUID);
    }

    @GetMapping("/deliveryAddresses/{currentUserUUID}")
    public Set<AddressDTO> getDeliveryAddresses(@PathVariable("currentUserUUID") String currentUserUUID) {
        return locationService.getDeliveryAddresses(currentUserUUID);
    }
}
