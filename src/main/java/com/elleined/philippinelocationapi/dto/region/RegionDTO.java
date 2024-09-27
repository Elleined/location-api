package com.elleined.philippinelocationapi.dto.region;

import com.elleined.philippinelocationapi.dto.LocationDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class RegionDTO extends LocationDTO {
    private String description;
}
