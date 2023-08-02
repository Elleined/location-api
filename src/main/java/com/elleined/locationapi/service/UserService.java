package com.elleined.locationapi.service;

import com.elleined.locationapi.dto.UserDTO;
import com.elleined.locationapi.model.User;
import com.elleined.locationapi.model.address.Address;
import com.elleined.locationapi.model.address.UserAddress;
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
public class UserService {

    private final UserRepository userRepository;

    public User save(String UUID, UserAddress userAddress) {
        User user = User.builder()
                .UUID(UUID)
                .userAddress(userAddress)
                .deliveryAddress(new HashSet<>())
                .build();

        userRepository.save(user);
        log.debug("User with id of {} saved successfully with address of {}", user.getId(), userAddress);
        return user;
    }
}
