package com.elleined.philippinelocationapi.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public abstract class LocationRequest extends Request {

    @NotNull(message = "Please provide name")
    private String name;

    public LocationRequest(String name) {
        this.name = name;
    }
}
