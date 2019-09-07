package com.mglowinski.app.model;

import lombok.Data;

@Data
public class UserAddressDto {

    private String userId;
    private Address address;

    public UserAddressDto(String userId, Address address) {
        this.userId = userId;
        this.address = address;
    }
}
