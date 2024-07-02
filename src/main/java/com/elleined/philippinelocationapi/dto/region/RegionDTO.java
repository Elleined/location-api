package com.elleined.philippinelocationapi.dto.region;

import com.elleined.philippinelocationapi.dto.LocationDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.Link;

import java.util.List;

@Getter
@Setter
public class RegionDTO extends LocationDTO {
    private String description;

    @Builder
    public RegionDTO(int id, String name, String description) {
        super(id, name);
        this.description = description;
    }

    @Override
    public RegionDTO addLinks(boolean doInclude) {
        super.addLinks(doInclude);
        return this;
    }

    @Override
    protected List<Link> getAllRelatedLinks(boolean doInclude) {
        return List.of();
    }

    @Override
    protected List<Link> getAllSelfLinks(boolean doInclude) {
        return List.of();
    }
}
