package com.elleined.philippinelocationapi.dto.city;

import com.elleined.philippinelocationapi.dto.LocationDTO;
import com.elleined.philippinelocationapi.dto.province.ProvinceDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.Link;

import java.util.List;

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
        return List.of();
    }

    @Override
    protected List<Link> getAllSelfLinks(boolean doInclude) {
        return List.of();
    }
}
