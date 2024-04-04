package com.elleined.philippinelocationapi.request.baranggay;

import com.elleined.philippinelocationapi.request.LocationRequest;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class BaranggayRequest extends LocationRequest {

    @Positive(message = "Please provide city")
    private int cityId;

    @Builder
    public BaranggayRequest(String name, int cityId) {
        super(name);
        this.cityId = cityId;
    }
}
