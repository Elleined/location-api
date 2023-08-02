package com.elleined.locationapi.dto;

import com.elleined.locationapi.model.address.Address;
import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class UserDTO {
    private int id;
    private String UUID;
    private AddressDTO address;
    private Set<AddressDTO> deliveryAddresses;
}
