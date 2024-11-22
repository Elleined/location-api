package com.elleined.philippinelocationapi.model.region;

import com.elleined.philippinelocationapi.model.Location;
import com.elleined.philippinelocationapi.model.province.Province;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.Set;

@Entity
@Table(
        name = "tbl_region",
        indexes = {
                @Index(name = "name_idx", columnList = "name")
        }
)
@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
public class Region extends Location {

    @Column(name = "region_description")
    private String description;

    @OneToMany(mappedBy = "region")
    private Set<Province> provinces;

    public boolean has(int id) {
        return this.getProvinces().stream()
                .anyMatch(province -> province.getId() == id);
    }
}
