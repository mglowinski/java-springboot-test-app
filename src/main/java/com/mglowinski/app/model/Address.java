package com.mglowinski.app.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Address {

    private String id;
    private String street;
    private String postCode;
}
