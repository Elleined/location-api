package com.elleined.locationapi.model;

import com.elleined.locationapi.model.address.DeliveryAddress;
import com.elleined.locationapi.model.address.UserAddress;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Table(name = "tbl_user", indexes = @Index(name = "uuid_idx", columnList = "uuid"))
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(
            name = "user_id",
            updatable = false,
            nullable = false,
            unique = true
    )
    private int id;

    @Column(
            name = "UUID",
            unique = true,
            nullable = false,
            updatable = false
    )
    private String UUID;


    // user id reference is in tbl user address
    @OneToOne(
            mappedBy = "user",
            cascade = CascadeType.REMOVE,
            orphanRemoval = true
    )
    private UserAddress userAddress;

    // user id reference is in tbl delivery address
    @OneToMany(
            mappedBy = "user",
            cascade = CascadeType.REMOVE,
            orphanRemoval = true
    )
    @Setter(AccessLevel.NONE)
    private Set<DeliveryAddress> deliveryAddress;


}
