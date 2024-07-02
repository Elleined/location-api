package com.elleined.philippinelocationapi.dto.city;

import com.elleined.philippinelocationapi.controller.BaranggayController;
import com.elleined.philippinelocationapi.controller.CityController;
import com.elleined.philippinelocationapi.dto.LocationDTO;
import com.elleined.philippinelocationapi.dto.province.ProvinceDTO;
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
public class CityDTO extends LocationDTO {
    private ProvinceDTO provinceDTO;

    @Builder
    public CityDTO(int id, String name, ProvinceDTO provinceDTO) {
        super(id, name);
        this.provinceDTO = provinceDTO;
    }

    @Override
    public CityDTO addLinks(boolean doInclude) {
        super.addLinks(doInclude);
        return this;
    }

    @Override
    protected List<Link> getAllRelatedLinks(boolean doInclude) {
        return List.of(
                linkTo(methodOn(BaranggayController.class)
                        .getAllBy(this.getProvinceDTO().getRegionDTO().getId(), this.getProvinceDTO().getId(),this.getId(),0, 0, null, null, doInclude))
                        .withSelfRel()
                        .withTitle("Get all baranggays by city")
                        .withType(HttpMethod.GET.name())
        );
    }

    @Override
    protected List<Link> getAllSelfLinks(boolean doInclude) {
        return List.of(
                linkTo(methodOn(CityController.class)
                        .getAllBy(this.getProvinceDTO().getRegionDTO().getId(), this.getProvinceDTO().getId(),0, 0, null, null, doInclude))
                        .withSelfRel()
                        .withTitle("Get all")
                        .withType(HttpMethod.GET.name()),

                linkTo(methodOn(CityController.class)
                        .findAllByName(this.getProvinceDTO().getRegionDTO().getId(), this.getProvinceDTO().getId(), null, 0, 0, null, null, doInclude))
                        .withSelfRel()
                        .withTitle("Get all by name")
                        .withType(HttpMethod.GET.name())
        );
    }
}
