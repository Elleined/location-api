package com.elleined.locationapi.model.location;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(
        name = "tbl_location",
        indexes = @Index(name = "location_name_idx", columnList = "location_name")
)
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public abstract class Location {

    @Id
    @GeneratedValue(
            strategy = GenerationType.TABLE,
            generator = "autoIncrement")
    @SequenceGenerator(
            allocationSize = 1,
            name = "autoIncrement",
            sequenceName = "autoIncrement"
    )
    @Column(name = "location_id")
    private int id;

    @Column(
            name = "location_name",
            nullable = false
    )
    private String locationName;
}
