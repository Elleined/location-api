package com.elleined.philippinelocationapi.request.city;

import com.elleined.philippinelocationapi.request.LocationRequest;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
public class CityRequest extends LocationRequest {

    @Positive(message = "Please provide province")
    private int provinceId;
}
