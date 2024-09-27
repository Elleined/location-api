package com.elleined.philippinelocationapi.dto.province;

import com.elleined.philippinelocationapi.dto.LocationDTO;
import com.elleined.philippinelocationapi.dto.region.RegionDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class ProvinceDTO extends LocationDTO {
    private RegionDTO regionDTO;
}
