package com.elleined.locationapi.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(
        name = "tbl_baranggay",
        indexes = @Index(name = "location_name_idx", columnList = "location_name")
)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Baranggay {


    @Id
    @Column(name = "location_id")
    private int id;

    @Column(name = "location_name")
    private String locationName;

    @ManyToOne
    @JoinColumn(
            name = "city_id",
            referencedColumnName = "location_id"
    )
    private City city;
}
