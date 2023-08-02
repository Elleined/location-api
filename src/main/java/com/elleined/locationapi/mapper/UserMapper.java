package com.elleined.locationapi.mapper;

import com.elleined.locationapi.dto.UserDTO;
import com.elleined.locationapi.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring", uses = AddressMapper.class)
public abstract class UserMapper {

    @Mappings({
            @Mapping(target = "address", source = "user.userAddress"),
            @Mapping(target = "deliveryAddresses", source = "user.deliveryAddress")
    })
    public abstract UserDTO toDTO(User user);
}
