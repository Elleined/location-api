package com.elleined.philippinelocationapi.dto.region;

import com.elleined.philippinelocationapi.dto.LocationDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
public class RegionDTO extends LocationDTO {
    private String description;
    private Set<Integer> provinceIds;

    @Builder
    public RegionDTO(int id, String name, String description, Set<Integer> provinceIds) {
        super(id, name);
        this.description = description;
        this.provinceIds = provinceIds;
    }
}
