package com.elleined.philippinelocationapi.dto.baranggay;

import com.elleined.philippinelocationapi.dto.LocationDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class BaranggayDTO extends LocationDTO {
    private int cityId;
    private int provinceId;
    private int regionId;

    @Builder
    public BaranggayDTO(int id, String name, int cityId, int provinceId, int regionId) {
        super(id, name);
        this.cityId = cityId;
        this.provinceId = provinceId;
        this.regionId = regionId;
    }
}
