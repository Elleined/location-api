package com.elleined.philippinelocationapi.dto.province;

import com.elleined.philippinelocationapi.dto.LocationDTO;
import com.elleined.philippinelocationapi.dto.region.RegionDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.Link;

import javax.swing.plaf.synth.Region;
import java.util.List;
import java.util.Set;

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
        return List.of();
    }

    @Override
    protected List<Link> getAllSelfLinks(boolean doInclude) {
        return List.of();
    }
}
