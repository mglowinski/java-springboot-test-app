package com.mglowinski.app.service;

import com.mglowinski.app.model.Address;
import com.mglowinski.app.model.User;
import com.mglowinski.app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AppService {

    private final UserRepository userRepository;

    @Autowired
    public AppService(UserRepository userRepository) {
        this.userRepository = userRepository;
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

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public List<User> getUsersByPostCode(String postCode) {
        return userRepository.findAllByAddress_PostCode(postCode);
    }

    public List<User> getUsersByCountry(String countryName) {
        return userRepository.findAllByCountriesName(countryName);
    }

    public List<Address> getUsersAddresses() {
        List<User> users = userRepository.findAll();
        return users.stream().map(User::getAddress).collect(Collectors.toList());
    }

    private int fib(int n) {
        if (n > 1) {
            return fib(n - 1) + fib(n - 2);
        } else {
            return n;
        }
    }
}
