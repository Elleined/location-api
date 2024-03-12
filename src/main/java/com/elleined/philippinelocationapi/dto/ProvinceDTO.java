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
public class ProvinceDTO {
    private int id;

    @NotBlank(message = "Province name cannot be empty, blank or null")
    private String name;

    @Positive(message = "Region id must be positive")
    private int regionId;

    private String regionName;

    private int cityCount;
    private int baranggayCount;
}
