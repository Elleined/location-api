package com.elleined.philippinelocationapi.dto.region;

import com.elleined.philippinelocationapi.controller.ProvinceController;
import com.elleined.philippinelocationapi.controller.RegionController;
import com.elleined.philippinelocationapi.dto.LocationDTO;
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
        return List.of(
                linkTo(methodOn(ProvinceController.class)
                        .getAllBy(this.getId(), 0, 0, null, null, doInclude))
                        .withSelfRel()
                        .withTitle("Get all provinces by region")
                        .withType(HttpMethod.GET.name())
        );
    }

    @Override
    protected List<Link> getAllSelfLinks(boolean doInclude) {
        return List.of(
                linkTo(methodOn(RegionController.class)
                        .getAll(0, 0, null, null, doInclude))
                        .withSelfRel()
                        .withTitle("Get all")
                        .withType(HttpMethod.GET.name()),

                linkTo(methodOn(RegionController.class)
                        .findAllByName(null, 0, 0, null, null, doInclude))
                        .withSelfRel()
                        .withTitle("Get all by name")
                        .withType(HttpMethod.GET.name())
        );
    }
}
