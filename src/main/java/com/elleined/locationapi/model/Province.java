package com.elleined.locationapi.model;


import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Table(
        name = "tbl_province",
        indexes = @Index(name = "location_name_idx", columnList = "location_name")
)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Province {

    @Id
    @Column(name = "location_id")
    private int id;

    @Column(name = "location_name")
    private String locationName;

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

    public int getCityCount() {
        return this.getCities().size();
    }

    public int getBaranggayCount() {
        return (int) this.getCities().stream()
                .map(City::getBaranggays)
                .count();
    }
}
