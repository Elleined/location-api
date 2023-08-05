package com.elleined.locationapi.model.location;


import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Table(name = "tbl_province")
@NoArgsConstructor
@Getter
@Setter
public class Province extends Location {

    @ManyToOne
    @JoinColumn(
            name = "region_id",
            referencedColumnName = "location_id"
    )
    private Region region;

    // province id reference is in tbl city table
    @OneToMany(
            mappedBy = "province",
            cascade = CascadeType.REMOVE,
            orphanRemoval = true
    )
    @Setter(AccessLevel.NONE)
    private Set<City> cities;

    @Builder(builderMethodName = "provinceBuilder")
    public Province(int id, String locationName, Region region, Set<City> cities) {
        super(id, locationName);
        this.region = region;
        this.cities = cities;
    }
}
