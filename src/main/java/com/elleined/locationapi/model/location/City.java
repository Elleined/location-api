package com.elleined.locationapi.model.location;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Table(
        name = "tbl_city",
        indexes = @Index(name = "location_name_idx", columnList = "location_name")
)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class City {

    @Id
    @Column(name = "location_id")
    private int id;

    @Column(name = "location_name")
    private String locationName;

    @ManyToOne
    @JoinColumn(
            name = "province_id",
            referencedColumnName = "location_id"
    )
    private Province province;

    // city id reference is in tbl baraggay table
    @OneToMany(
            mappedBy = "city",
            cascade = CascadeType.REMOVE,
            orphanRemoval = true
    )
    @Setter(AccessLevel.NONE)
    private Set<Baranggay> baranggays;
}
