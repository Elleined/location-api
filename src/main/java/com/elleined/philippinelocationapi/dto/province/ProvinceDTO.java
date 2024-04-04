package com.elleined.philippinelocationapi.dto.province;

import com.elleined.philippinelocationapi.dto.LocationDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
public class ProvinceDTO extends LocationDTO {
    private int regionId;

    private Set<Integer> cityIds;

    @Builder
    public ProvinceDTO(int id, String name, int regionId, Set<Integer> cityIds) {
        super(id, name);
        this.regionId = regionId;
        this.cityIds = cityIds;
    }
}
