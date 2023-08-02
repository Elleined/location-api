package com.elleined.locationapi.service;

import com.elleined.locationapi.exception.ResourceNotFoundException;
import com.elleined.locationapi.model.User;
import com.elleined.locationapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
class UserService {

    private final UserRepository userRepository;

    User save(String UUID) {
        User user = User.builder()
                .UUID(UUID)
                .deliveryAddresses(new HashSet<>())
                .build();

        userRepository.save(user);
        log.debug("User with id of {} and with UUID of {} saved successfully!", user.getId(), UUID);
        return user;
    }

    User getById(int id) throws ResourceNotFoundException {
        return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User with id of " + id + " does not exists!"));
    }

    User getByUUID(String UUID) throws ResourceNotFoundException {
        return userRepository.findByUUID(UUID).orElseThrow(() -> new ResourceNotFoundException("User with UUID of " + UUID + " does not exists!"));
    }
}
