package com.elleined.locationapi.repository;

import com.elleined.locationapi.model.address.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Integer> {
}