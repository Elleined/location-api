package com.elleined.philippinelocationapi.model.city;

import com.elleined.philippinelocationapi.model.Location;
import com.elleined.philippinelocationapi.model.baranggay.Baranggay;
import com.elleined.philippinelocationapi.model.province.Province;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(
        name = "tbl_city",
        indexes = @Index(name = "name_idx", columnList = "name")
)
@NoArgsConstructor
@Getter
@Setter
public class City extends Location {

    @ManyToOne(optional = false)
    @JoinColumn(
            name = "province_id",
            referencedColumnName = "id",
            nullable = false
    )
    private Province province;

    // city id reference is in tbl baraggay table
    @OneToMany(mappedBy = "city")
    private Set<Baranggay> baranggays;

    @Builder
    public City(int id, String name, Province province, Set<Baranggay> baranggays) {
        super(id, name);
        this.province = province;
        this.baranggays = baranggays;
    }

    public Set<Integer> getAllBaranggayIds() {
        return this.getBaranggays().stream()
                .map(Location::getId)
                .collect(Collectors.toSet());
    }
}
