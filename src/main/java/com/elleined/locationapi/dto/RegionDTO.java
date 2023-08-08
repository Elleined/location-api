package com.elleined.locationapi.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegionDTO {
    private int id;
    @NotBlank(message = "Region name cannot be null, empty, or blank")
    private String name;

    @NotBlank(message = "Region description cannot be null, empty, or blank")
    private String description;

    private int provinceCount;
    private int cityCount;
    private int baranggayCount;
}
