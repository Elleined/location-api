package com.elleined.locationapi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CityDTO {
    private int id;

    @NotBlank(message = "City name must not be blank")
    private String name;

    @Positive(message = "Province id must be positive!")
    private int provinceId;
    private String provinceName;

    private int regionId;
    private String regionName;

    private int baranggayCount;
}
