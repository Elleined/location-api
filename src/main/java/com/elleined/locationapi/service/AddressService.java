package com.elleined.locationapi.service;

import com.elleined.locationapi.dto.AddressDTO;
import com.elleined.locationapi.mapper.AddressMapper;
import com.elleined.locationapi.model.User;
import com.elleined.locationapi.model.address.DeliveryAddress;
import com.elleined.locationapi.model.address.UserAddress;
import com.elleined.locationapi.model.location.Baranggay;
import com.elleined.locationapi.model.location.City;
import com.elleined.locationapi.model.location.Province;
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

    UserAddress saveUserAddress(User currentUser, String details, Province province, City city, Baranggay baranggay) {
        UserAddress address = UserAddress.userAddressBuilder()
                .details(details)
                .province(province)
                .city(city)
                .baranggay(baranggay)
                .user(currentUser)
                .build();

        addressRepository.save(address);
        log.debug("UserAddress with id of {} saved successfully!", address.getId());
        return address;
    }

    DeliveryAddress saveDeliveryAddress(User currentUser, String details, Province province, City city, Baranggay baranggay) {
        DeliveryAddress address = DeliveryAddress.deliveryAddressBuilder()
                .details(details)
                .province(province)
                .city(city)
                .baranggay(baranggay)
                .user(currentUser)
                .build();

        addressRepository.save(address);
        log.debug("Delivery address with id of {} saved successfully!", address.getId());
        return address;
    }
}
