package com.revature.dtos;

import com.revature.models.Address;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class AddressDTO { // used as Response and Request

    @NotNull
    private int addressId;

    @NotNull
    @NotBlank
    private String street;

    @NotNull
    private String street2;

    @NotNull
    @Length(max=50)
    private String city;

    @NotNull
    @Length(min = 2, max = 2)
    private String state;

    @NotNull
    @Length(max = 10)
    private String postalCode;

    public AddressDTO(Address address) {
        this.addressId = address.getAddressId();
        this.street = address.getStreet();
        this.street2 = address.getStreet2();
        this.city = address.getCity();
        this.state = address.getState();
        this.postalCode = address.getPostalCode();
    }
}
