package com.elleined.locationapi.model.location;


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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
}
