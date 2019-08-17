package com.mglowinski.app.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@Document(collection = "users")
public class User {

    @Id
    private String id;
    private String username;
    private String firstName;
    private String lastName;
    private Country country;
    private Address address;

    public Address getAddress() {
        return address;
    }
}