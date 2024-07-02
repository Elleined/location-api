package com.elleined.philippinelocationapi.request.baranggay;

import com.elleined.philippinelocationapi.request.LocationRequest;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
public class BaranggayRequest extends LocationRequest {

    @Positive(message = "Please provide city")
    private int cityId;
}
