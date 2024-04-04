package com.elleined.philippinelocationapi.request.city;

import com.elleined.philippinelocationapi.request.LocationRequest;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CityRequest extends LocationRequest {

    @Positive(message = "Please provide province")
    private int provinceId;

    @Builder
    public CityRequest(String name, int provinceId) {
        super(name);
        this.provinceId = provinceId;
    }
}
