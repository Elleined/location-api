package com.elleined.philippinelocationapi.model.baranggay;

import com.elleined.philippinelocationapi.model.Location;
import com.elleined.philippinelocationapi.model.city.City;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(
        name = "tbl_baranggay",
        indexes = @Index(name = "name_idx", columnList = "name")
)
@NoArgsConstructor
@Getter
@Setter
public class Baranggay extends Location {

    @ManyToOne(optional = false)
    @JoinColumn(
            name = "city_id",
            referencedColumnName = "id",
            nullable = false
    )
    private City city;

    @Builder
    public Baranggay(int id, String name, City city) {
        super(id, name);
        this.city = city;
    }
}
