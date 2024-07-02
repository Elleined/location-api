package com.elleined.philippinelocationapi.dto.baranggay;

import com.elleined.philippinelocationapi.controller.BaranggayController;
import com.elleined.philippinelocationapi.dto.LocationDTO;
import com.elleined.philippinelocationapi.dto.city.CityDTO;
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
public class BaranggayDTO extends LocationDTO {
    private CityDTO cityDTO;

    @Builder
    public BaranggayDTO(int id, String name, CityDTO cityDTO) {
        super(id, name);
        this.cityDTO = cityDTO;
    }

    @Override
    public BaranggayDTO addLinks(boolean doInclude) {
        super.addLinks(doInclude);
        return this;
    }

    @Override
    protected List<Link> getAllRelatedLinks(boolean doInclude) {
        return List.of();
    }

    @Override
    protected List<Link> getAllSelfLinks(boolean doInclude) {
        return List.of(
                linkTo(methodOn(BaranggayController.class)
                        .getAllBy(this.getCityDTO().getProvinceDTO().getRegionDTO().getId(), this.getCityDTO().getProvinceDTO().getId(),this.getCityDTO().getId(), 0, 0, null, null, doInclude))
                        .withSelfRel()
                        .withTitle("Get all")
                        .withType(HttpMethod.GET.name()),

                linkTo(methodOn(BaranggayController.class)
                        .findAllByName(this.getCityDTO().getProvinceDTO().getRegionDTO().getId(), this.getCityDTO().getProvinceDTO().getId(),this.getCityDTO().getId(), null,0, 0, null, null, doInclude))
                        .withSelfRel()
                        .withTitle("Get all by name")
                        .withType(HttpMethod.GET.name())
        );
    }
}
