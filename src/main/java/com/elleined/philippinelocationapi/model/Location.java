package com.elleined.philippinelocationapi.model;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@MappedSuperclass
@NoArgsConstructor
@Getter
@Setter
public abstract class Location extends PrimaryKeyIdentity {

    @Column(
            name = "name",
            nullable = false
    )
    private String name;

    public Location(int id, String name) {
        super(id);
        this.name = name;
    }
}
