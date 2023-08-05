package com.elleined.locationapi.service;

import com.elleined.locationapi.model.address.DeliveryAddress;
import com.elleined.locationapi.model.address.UserAddress;
import com.elleined.locationapi.repository.AddressRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
class AddressService {
    private final AddressRepository addressRepository;

    void saveUserAddress(UserAddress userAddress) {
        addressRepository.save(userAddress);
        log.debug("UserAddress with id of {} saved successfully!", userAddress.getId());
    }

    void saveDeliveryAddress(DeliveryAddress deliveryAddress) {
        addressRepository.save(deliveryAddress);
        log.debug("Delivery address with id of {} saved successfully!", deliveryAddress.getId());
    }
}
