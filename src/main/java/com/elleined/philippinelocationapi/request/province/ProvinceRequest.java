package com.elleined.philippinelocationapi.request.province;

import com.elleined.philippinelocationapi.request.LocationRequest;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ProvinceRequest extends LocationRequest {

    @Positive(message = "Please provide region")
    private int regionId;

    @Builder
    public ProvinceRequest(String name, int regionId) {
        super(name);
        this.regionId = regionId;
    }
}
