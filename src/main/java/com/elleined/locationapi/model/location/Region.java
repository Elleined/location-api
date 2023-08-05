package com.elleined.locationapi.model.location;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Table(
        name = "tbl_region",
        indexes = @Index(name = "location_name_idx", columnList = "location_name")
)
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Region extends Location {

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

    @Builder
    public Region(int id, String locationName, String description, Set<Province> provinces) {
        super(id, locationName);
        this.description = description;
        this.provinces = provinces;
    }
}
