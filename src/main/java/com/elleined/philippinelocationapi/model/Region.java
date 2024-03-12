package com.elleined.philippinelocationapi.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Collection;
import java.util.Set;

@Entity
@Table(name = "tbl_region", indexes = {
        @Index(name = "location_name_idx", columnList = "location_name")
})
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Region {

    @Id
    @Column(name = "location_id")
    private int id;

    @Column(name = "location_name")
    private String locationName;

    @Column(name = "region_description")
    private String description;

    // region id reference is in tbl province table
    @OneToMany(
            mappedBy = "region",
            cascade = CascadeType.REMOVE,
            orphanRemoval = true
    )
    @Setter(AccessLevel.NONE)
    private Set<Province> provinces;

    public int getProvinceCount() {
        return this.getProvinces().size();
    }

    public int getCityCount() {
        return (int) this.getProvinces().stream()
                .map(Province::getCities)
                .count();
    }

    public int getBaranggayCount() {
        return (int) this.getProvinces().stream()
                .map(Province::getCities)
                .flatMap(Collection::stream)
                .map(City::getBaranggays)
                .count();
    }
}
