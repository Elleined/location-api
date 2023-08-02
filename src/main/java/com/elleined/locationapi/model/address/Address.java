package com.elleined.locationapi.model.address;

import com.elleined.locationapi.model.location.Baranggay;
import com.elleined.locationapi.model.location.City;
import com.elleined.locationapi.model.location.Province;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tbl_address")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public abstract class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(
            name = "address_id",
            unique = true,
            updatable = false
    )
    private int id;

    @Column(
            name = "details",
            unique = true
    )
    private String details;

    @OneToOne
    @JoinColumn(
            name = "province_id",
            referencedColumnName = "location_id"
    )
    private Province province;

    @OneToOne
    @JoinColumn(
            name = "city_id",
            referencedColumnName = "location_id"
    )
    private City city;

    @OneToOne
    @JoinColumn(
            name = "baranggay_id",
            referencedColumnName = "location_id"
    )
    private Baranggay baranggay;
}
