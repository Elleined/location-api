package com.elleined.locationapi.controller;

import com.elleined.locationapi.dto.AddressDTO;
import com.elleined.locationapi.dto.UserDTO;
import com.elleined.locationapi.service.LocationService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final LocationService locationService;

    @PostMapping
    public UserDTO saveUser(@RequestParam("UUID") String UUID) {
        return locationService.saveUser(UUID);
    }

    @GetMapping("/id/{id}")
    public UserDTO getByUUID(@Positive @PathVariable("id") int id) {
        return locationService.getById(id);
    }

    @GetMapping("/{currentUserUUID}")
    public UserDTO getByUUID(@PathVariable("currentUserUUID") String currentUserUUID) {
        return locationService.getByUUID(currentUserUUID);
    }

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
