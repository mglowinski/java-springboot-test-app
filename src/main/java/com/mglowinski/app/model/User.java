package com.mglowinski.app.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document(collection = "users")
public class User {

    @Id
    private String id;
    private String username;
    private String firstName;
    private String lastName;
    private List<Country> countries;
    private Address address;

    public Address getAddress() {
        return address;
    }
}