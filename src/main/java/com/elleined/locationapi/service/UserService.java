package com.elleined.locationapi.service;

import com.elleined.locationapi.dto.AddressDTO;
import com.elleined.locationapi.dto.UserDTO;
import com.elleined.locationapi.exception.ResourceNotFoundException;
import com.elleined.locationapi.model.User;
import com.elleined.locationapi.model.address.Address;
import com.elleined.locationapi.model.address.DeliveryAddress;
import com.elleined.locationapi.model.address.UserAddress;
import com.elleined.locationapi.model.location.Baranggay;
import com.elleined.locationapi.model.location.City;
import com.elleined.locationapi.model.location.Province;
import com.elleined.locationapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    private final AddressService addressService;


    User save(String UUID, String details, Province province, City city, Baranggay baranggay) {
        UserAddress userAddress = addressService.saveUserAddress(details, province, city, baranggay);
        User user = User.builder()
                .UUID(UUID)
                .userAddress(userAddress)
                .deliveryAddress(new HashSet<>())
                .build();

        userRepository.save(user);
        log.debug("User with id of {} and with UUID of {} saved succesfully!", user.getId(), UUID);
        return user;
    }

    User getById(int id) throws ResourceNotFoundException {
        return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User with id of " + id + " does not exists!"));
    }

    User getByUUID(String UUID) {
        return userRepository.findByUUID(UUID).orElseThrow(() -> new ResourceNotFoundException("User with UUID of " + UUID + " does not exists!"));
    }

    Address getAddress(String UUID) {
        return getByUUID(UUID).getUserAddress();
    }

    Set<DeliveryAddress> getDeliveryAddresses(String UUID) {
        return getByUUID(UUID).getDeliveryAddress();
    }
}
