package com.elleined.philippinelocationapi.model.region;

import com.elleined.philippinelocationapi.model.Location;
import com.elleined.philippinelocationapi.model.province.Province;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "tbl_region", indexes = {
        @Index(name = "name_idx", columnList = "name")
})
@NoArgsConstructor
@Getter
@Setter
public class Region extends Location {

    @Column(name = "region_description")
    private String description;

    // region id reference is in tbl province table
    @OneToMany(mappedBy = "region")
    private Set<Province> provinces;

    @Builder
    public Region(int id, String name, String description, Set<Province> provinces) {
        super(id, name);
        this.description = description;
        this.provinces = provinces;
    }
}
