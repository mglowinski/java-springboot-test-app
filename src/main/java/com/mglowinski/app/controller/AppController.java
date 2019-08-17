package com.mglowinski.app.controller;

import com.mglowinski.app.model.Address;
import com.mglowinski.app.model.User;
import com.mglowinski.app.service.AppService;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AppController {

    private final AppService appService;

    @Autowired
    public AppController(AppService appService) {
        this.appService = appService;
    }

    @GetMapping(value = "/hello-world", produces = "application/json")
    public ResponseEntity<Object> hello() throws JSONException {
        JSONObject object = new JSONObject();
        object.put("hello1", "world1");
        object.put("hello2", "world2");
        object.put("hello3", "world3");
        object.put("hello4", "world4");
        object.put("hello5", "world5");
        object.put("hello6", "world6");
        return ResponseEntity.ok(object.toString());
    }

    @GetMapping(value = "/sum-of-squares", produces = "application/json")
    public ResponseEntity<Object> getSumOfSquares(@RequestParam(value = "n", required = false) Integer n) {
        JSONObject object = new JSONObject();
        int sumOfSquares = appService.getSumOfSquares(n);
        object.put("sumOfSquares", sumOfSquares);
        return ResponseEntity.ok(object.toString());
    }

    @GetMapping(value = "/fib-series-recursively", produces = "application/json")
    public ResponseEntity<Object> getFibonacciSeriesRecursively(@RequestParam(value = "n", required = false) Integer n) {
        int[] sumOfSquares = appService.getFibonacciSeriesRecursively(n);
        return ResponseEntity.ok(sumOfSquares);
    }

    @GetMapping(value = "/fib-value-iteratively", produces = "application/json")
    public ResponseEntity<Object> getFibonacciValueIteratively(@RequestParam(value = "n", required = false) Integer n) {
        JSONObject object = new JSONObject();
        int fibValue = appService.getFibonacciValueIteratively(n);
        object.put("fibValue", fibValue);
        return ResponseEntity.ok(object.toString());
    }

    @GetMapping(value = "/mongo/users")
    public ResponseEntity<List<User>> getUsersFromMongo(@RequestParam(value = "postCode", required = false)
                                                                String postCode,
                                                        @RequestParam(value = "country", required = false)
                                                                String country) {
        List<User> users;
        if (postCode != null) {
            users = appService.getUsersByPostCodeFromMongo(postCode);
        } else if (country != null) {
            users = appService.getUsersByCountryFromMongo(country);
        } else {
            users = appService.getUsersFromMongo();
        }
        return ResponseEntity.ok(users);
    }

    @GetMapping(value = "/mongo/users/addresses")
    public ResponseEntity<List<Address>> getUsersAddressesFromMongo() {
        return ResponseEntity.ok(appService.getUsersAddressesFromMongo());
    }

    @GetMapping(value = "/mysql/users")
    public ResponseEntity<List<User>> getUsersFromMySql() {
        return ResponseEntity.ok(appService.getUsersFromMySql());
    }

}
