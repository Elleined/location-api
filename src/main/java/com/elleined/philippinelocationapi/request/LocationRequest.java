package com.elleined.philippinelocationapi.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
public abstract class LocationRequest extends Request {

    @NotNull(message = "Please provide name")
    private String name;
}
