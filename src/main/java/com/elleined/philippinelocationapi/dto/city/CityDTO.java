package com.elleined.philippinelocationapi.dto.city;

import com.elleined.philippinelocationapi.dto.LocationDTO;
import com.elleined.philippinelocationapi.dto.province.ProvinceDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class CityDTO extends LocationDTO {
    private ProvinceDTO provinceDTO;
}
