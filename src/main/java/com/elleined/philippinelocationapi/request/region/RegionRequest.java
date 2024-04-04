package com.elleined.philippinelocationapi.request.region;

import com.elleined.philippinelocationapi.request.LocationRequest;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class RegionRequest extends LocationRequest {

    @NotNull(message = "Please provide description")
    private String description;

    @Builder
    public RegionRequest(String name, String description) {
        super(name);
        this.description = description;
    }
}
