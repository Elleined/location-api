package com.elleined.philippinelocationapi.dto.baranggay;

import com.elleined.philippinelocationapi.dto.LocationDTO;
import com.elleined.philippinelocationapi.dto.city.CityDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.Link;

import java.util.List;

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
        return List.of();
    }
}
