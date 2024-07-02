package com.elleined.philippinelocationapi.dto.province;

import com.elleined.philippinelocationapi.controller.CityController;
import com.elleined.philippinelocationapi.controller.ProvinceController;
import com.elleined.philippinelocationapi.controller.RegionController;
import com.elleined.philippinelocationapi.dto.LocationDTO;
import com.elleined.philippinelocationapi.dto.region.RegionDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpMethod;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Getter
@Setter
public class ProvinceDTO extends LocationDTO {
    private RegionDTO regionDTO;

    @Builder
    public ProvinceDTO(int id, String name, RegionDTO regionDTO) {
        super(id, name);
        this.regionDTO = regionDTO;
    }

    @Override
    public ProvinceDTO addLinks(boolean doInclude) {
        super.addLinks(doInclude);
        return this;
    }

    @Override
    protected List<Link> getAllRelatedLinks(boolean doInclude) {
        return List.of(
                linkTo(methodOn(CityController.class)
                        .getAllBy(this.getRegionDTO().getId(), this.getId(), 0, 0, null, null, doInclude))
                        .withSelfRel()
                        .withTitle("Get all cities by province")
                        .withType(HttpMethod.GET.name())
        );
    }

    @Override
    protected List<Link> getAllSelfLinks(boolean doInclude) {
        return List.of(
                linkTo(methodOn(ProvinceController.class)
                        .getAllBy(this.getRegionDTO().getId(), 0, 0, null, null, doInclude))
                        .withSelfRel()
                        .withTitle("Get all")
                        .withType(HttpMethod.GET.name()),

                linkTo(methodOn(ProvinceController.class)
                        .findAllByName(this.getRegionDTO().getId(), null, 0, 0, null, null, doInclude))
                        .withSelfRel()
                        .withTitle("Get all by name")
                        .withType(HttpMethod.GET.name())
        );
    }
}
