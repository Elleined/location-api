package com.elleined.philippinelocationapi.model.province;


import com.elleined.philippinelocationapi.model.Location;
import com.elleined.philippinelocationapi.model.city.City;
import com.elleined.philippinelocationapi.model.region.Region;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(
        name = "tbl_province",
        indexes = @Index(name = "name_idx", columnList = "name")
)
@NoArgsConstructor
@Getter
@Setter
public class Province extends Location {

    @ManyToOne(optional = false)
    @JoinColumn(
            name = "region_id",
            referencedColumnName = "id",
            nullable = false
    )
    private Region region;

    // province id reference is in tbl city table
    @OneToMany(mappedBy = "province")
    private Set<City> cities;

    @Builder
    public Province(int id, String name, Region region, Set<City> cities) {
        super(id, name);
        this.region = region;
        this.cities = cities;
    }

    public Set<Integer> getAllCityIds() {
        return this.getCities().stream()
                .map(Location::getId)
                .collect(Collectors.toSet());
    }
}
