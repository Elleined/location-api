package com.elleined.philippinelocationapi.dto;

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
public class BaranggayDTO {

    private int id;
    @NotBlank(message = "Baranggay name must not be empty, blank, or null")
    private String name;

    @Positive(message = "City id cannot be negative")
    private int cityId;
    private String cityName;

    private int provinceId;
    private String provinceName;

    private int regionId;
    private String regionName;
}
