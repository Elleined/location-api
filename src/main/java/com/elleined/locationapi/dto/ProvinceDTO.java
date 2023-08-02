package com.elleined.locationapi.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProvinceDTO {
    private int id;
    private String name;
    private int regionId;

    private int cityCount;
    private int baranggayCount;

}
