package com.elleined.philippinelocationapi.model;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
public abstract class Location extends PrimaryKeyIdentity {

    @Column(
            name = "name",
            nullable = false
    )
    private String name;
}
