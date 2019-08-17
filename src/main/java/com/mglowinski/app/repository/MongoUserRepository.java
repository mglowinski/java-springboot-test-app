package com.mglowinski.app.repository;

import com.mglowinski.app.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MongoUserRepository extends MongoRepository<User, String> {

    List<User> findAllByAddress_PostCode(String postCode);

    @Query("{'countries.name' : ?0 }")
    List<User> findAllByCountriesName(String name);
}
