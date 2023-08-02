package com.elleined.locationapi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BaranggayDTO {

    private int id;
    @NotBlank(message = "Baranggay name must not be empty, blank, or null")
    private String name;

    @Positive(message = "City id cannot be negative")
    private int cityId;
    private String cityName;

    private int provinceId;
    private String provinceName;
}
