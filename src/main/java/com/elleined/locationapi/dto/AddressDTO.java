package com.elleined.locationapi.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AddressDTO {

    private int id;
    private String details;
    private String provinceName;
    private String cityName;
    private String baranggayName;
}
