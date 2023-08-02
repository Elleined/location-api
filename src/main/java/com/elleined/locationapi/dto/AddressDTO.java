package com.elleined.locationapi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AddressDTO {

    private int id;

    @NotBlank(message = "details cannot be null, empty, or blank")
    private String details;

    private String provinceName;
    @Positive(message = "Province id cannot be less than zero or negative")
    private int provinceId;

    private String cityName;

    @Positive(message = "City id cannot be less than zero or negative")
    private int cityId;

    private String baranggayName;

    @Positive(message = "Baranggay id cannot be less than zero or negative")
    private int baranggayId;
}
