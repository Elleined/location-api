package com.elleined.philippinelocationapi.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public abstract class LocationDTO extends DTO {
    private String name;

    public LocationDTO(int id, String name) {
        super(id);
        this.name = name;
    }
}
