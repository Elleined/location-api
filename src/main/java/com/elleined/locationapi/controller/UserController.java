package com.elleined.locationapi.controller;

import com.elleined.locationapi.dto.UserDTO;
import com.elleined.locationapi.service.LocationService;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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
        return locationService.getUserById(id);
    }

    @GetMapping("/{currentUserUUID}")
    public UserDTO getByUUID(@PathVariable("currentUserUUID") String currentUserUUID) {
        return locationService.getUserByUUID(currentUserUUID);
    }
}
