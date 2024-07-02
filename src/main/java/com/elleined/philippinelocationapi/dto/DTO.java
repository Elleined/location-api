package com.elleined.philippinelocationapi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public abstract class DTO extends HateoasDTO {
    private int id;
}
