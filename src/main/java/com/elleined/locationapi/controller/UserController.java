package com.elleined.locationapi.controller;

import com.elleined.locationapi.service.LocationService;
import com.elleined.locationapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final LocationService locationService;
    private final UserService userService;


}
