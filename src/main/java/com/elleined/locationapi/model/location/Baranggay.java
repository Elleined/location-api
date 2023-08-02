package com.elleined.locationapi.model.location;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tbl_baranggay")
@NoArgsConstructor
@Getter
@Setter
public class Baranggay extends Location {

    @ManyToOne
    @JoinColumn(
            name = "city_id",
            referencedColumnName = "location_id"
    )
    private City city;

    @Builder(builderMethodName = "baranggayBuilder")
    public Baranggay(int id, String locationName, City city) {
        super(id, locationName);
        this.city = city;
    }
}
