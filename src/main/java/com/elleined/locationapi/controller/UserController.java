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

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final LocationService locationService;

    @PostMapping
    public UserDTO saveUser(@NotNull @RequestParam("UUID") String UUID) {
        return locationService.saveUser(UUID);
    }

    @PostMapping("/saveUserAddress/{currentUserUUID}")
    public AddressDTO saveUserAddress(@NotNull @PathVariable("currentUserUUID") String currentUserUUID,
                                      @Valid @RequestBody AddressDTO addressDTO) {

        return locationService.saveUserAddress(currentUserUUID, addressDTO);
    }

    @PostMapping("/saveDeliveryAddress/{currentUserUUID}")
    public AddressDTO saveDeliveryAddress(@NotNull @PathVariable("currentUserUUID") String currentUserUUID,
                                          @Valid @RequestBody AddressDTO addressDTO) {
        return locationService.saveDeliveryAddress(currentUserUUID, addressDTO);
    }

    @GetMapping("/id/{id}")
    public UserDTO getByUUID(@Positive @PathVariable("id") int id) {
        return locationService.getById(id);
    }

    @GetMapping("/{currentUserUUID}")
    public UserDTO getByUUID(@NotNull @PathVariable("currentUserUUID") String currentUserUUID) {
        return locationService.getByUUID(currentUserUUID);
    }

    @GetMapping("/userAddress/{currentUserUUID}")
    public AddressDTO getAddress(@NotNull @PathVariable("currentUserUUID") String currentUserUUID) {
        return locationService.getAddress(currentUserUUID);
    }

    @GetMapping("/deliveryAddresses/{currentUserUUID}")
    public Set<AddressDTO> getDeliveryAddresses(@NotNull @PathVariable("currentUserUUID") String currentUserUUID) {
        return locationService.getDeliveryAddresses(currentUserUUID);
    }
}
