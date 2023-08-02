package com.elleined.locationapi.model.address;

import com.elleined.locationapi.model.User;
import com.elleined.locationapi.model.location.Baranggay;
import com.elleined.locationapi.model.location.City;
import com.elleined.locationapi.model.location.Province;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tbl_user_address")
@NoArgsConstructor
@Getter
@Setter
public class UserAddress extends Address {

    @OneToOne(orphanRemoval = true)
    @JoinColumn(
            name = "user_id",
            referencedColumnName = "user_id"
    )
    private User user;

    @Builder(builderMethodName = "userAddressBuilder")
    public UserAddress(int id, String details, Province province, City city, Baranggay baranggay, User user) {
        super(id, details, province, city, baranggay);
        this.user = user;
    }
}