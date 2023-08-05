package com.elleined.locationapi.model.location;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Table(
        name = "tbl_city",
        indexes = @Index(name = "location_name_idx", columnList = "location_name")
)
@NoArgsConstructor
@Getter
@Setter
public class City extends Location {

    @Column(
            name = "zip_code",
            updatable = false,
            nullable = false,
            unique = true
    )
    private int zipCode;

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

    @Builder(builderMethodName = "cityBuilder")
    public City(int id, String locationName, int zipCode, Province province, Set<Baranggay> baranggays) {
        super(id, locationName);
        this.zipCode = zipCode;
        this.province = province;
        this.baranggays = baranggays;
    }
}
