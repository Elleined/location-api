package com.elleined.philippinelocationapi.model.baranggay;

import com.elleined.philippinelocationapi.model.Location;
import com.elleined.philippinelocationapi.model.city.City;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Table(
        name = "tbl_baranggay",
        indexes = @Index(name = "name_idx", columnList = "name")
)
@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
public class Baranggay extends Location {

    @ManyToOne(optional = false)
    @JoinColumn(
            name = "city_id",
            referencedColumnName = "id",
            nullable = false,
            updatable = false
    )
    private City city;
}
