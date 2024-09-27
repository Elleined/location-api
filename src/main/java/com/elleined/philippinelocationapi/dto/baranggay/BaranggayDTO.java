package com.elleined.philippinelocationapi.dto.baranggay;

import com.elleined.philippinelocationapi.dto.LocationDTO;
import com.elleined.philippinelocationapi.dto.city.CityDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class BaranggayDTO extends LocationDTO {
    private CityDTO cityDTO;

}
