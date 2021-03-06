package com.mglowinski.app.service;

import com.mglowinski.app.model.User;
import com.mglowinski.app.model.UserAddressDto;
import com.mglowinski.app.repository.MongoUserRepository;
import com.mglowinski.app.repository.MySqlUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AppService {

    private final MongoUserRepository mongoUserRepository;
    private final MySqlUserRepository mySqlUserRepository;

    @Autowired
    public AppService(MongoUserRepository mongoUserRepository,
                      MySqlUserRepository mySqlUserRepository) {
        this.mongoUserRepository = mongoUserRepository;
        this.mySqlUserRepository = mySqlUserRepository;
    }

    public int getSumOfSquares(Integer n) {
        if (n == null) {
            n = 10;
        }

        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += (i * i);
        }
        return sum;
    }

    public int[] getFibonacciSeriesRecursively(Integer n) {
        if (n == null) {
            n = 10;
        }

        int[] results = new int[n];
        for (int i = 0; i < n; i++) {
            results[i] = fib(i);
        }
        return results;
    }

    public int getFibonacciValueIteratively(Integer n) {
        if (n == null) {
            n = 10;
        }

        if (n <= 1) {
            return n;
        }

        int fib = 1;
        int prevFib = 1;

        for (int i = 2; i < n; i++) {
            int temp = fib;
            fib += prevFib;
            prevFib = temp;
        }

        return fib;
    }

    public void fillData() {
        mySqlUserRepository.fillData();
    }

    public List<User> getUsersFromMongo() {
        return mongoUserRepository.findAll();
    }

    public List<User> getUsersByPostCodeFromMongo(String postCode) {
        return mongoUserRepository.findAllByAddress_PostCode(postCode);
    }

    public List<User> getUsersByCountryFromMongo(String countryName) {
        return mongoUserRepository.findAllByCountryName(countryName);
    }

    public List<User> getUsersByPostCodeAndCountryFromMongo(String postCode, String country) {
        return mongoUserRepository.findAllByAddress_PostCodeAndCountryName(postCode, country);
    }

    public List<UserAddressDto> getUsersAddressesFromMongo() {
        List<User> users = mongoUserRepository.findAll();
        return users.stream()
                .map(user -> new UserAddressDto(user.getId(), user.getAddress()))
                .collect(Collectors.toList());
    }

    public List<User> getUsersFromMySql() {
        return mySqlUserRepository.findAll();
    }

    public List<User> getUsersByPostCodeFromMySql(String postCode) {
        return mySqlUserRepository.findAllByPostCode(postCode);
    }

    public List<User> getUsersByCountryFromMySql(String country) {
        return mySqlUserRepository.findAllByCountry(country);
    }

    public List<User> getUsersByPostCodeAndCountryFromMySql(String postCode, String country) {
        return mySqlUserRepository.findAllByPostCodeAndCountry(postCode, country);
    }

    public List<UserAddressDto> getUsersAddressesFromMySql() {
        return mySqlUserRepository.findUsersAddresses();
    }

    private int fib(int n) {
        if (n > 1) {
            return fib(n - 1) + fib(n - 2);
        } else {
            return n;
        }
    }

}
