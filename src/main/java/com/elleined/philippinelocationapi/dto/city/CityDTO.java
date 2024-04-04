package com.elleined.philippinelocationapi.dto.city;

import com.elleined.philippinelocationapi.dto.LocationDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
public class CityDTO extends LocationDTO {
    private int provinceId;
    private int regionId;

    private Set<Integer> baranggayIds;

    @Builder
    public CityDTO(int id, String name, int provinceId, int regionId, Set<Integer> baranggayIds) {
        super(id, name);
        this.provinceId = provinceId;
        this.regionId = regionId;
        this.baranggayIds = baranggayIds;
    }
}
