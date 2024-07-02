package com.elleined.philippinelocationapi.model.city;

import com.elleined.philippinelocationapi.model.Location;
import com.elleined.philippinelocationapi.model.baranggay.Baranggay;
import com.elleined.philippinelocationapi.model.province.Province;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.Set;

@Entity
@Table(
        name = "tbl_city",
        indexes = @Index(name = "name_idx", columnList = "name")
)
@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
public class City extends Location {

    @ManyToOne(optional = false)
    @JoinColumn(
            name = "province_id",
            referencedColumnName = "id",
            nullable = false,
            updatable = false
    )
    private Province province;

    @OneToMany(mappedBy = "city")
    private Set<Baranggay> baranggays;
}
