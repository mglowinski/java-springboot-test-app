package com.mglowinski.otp.controller;

import com.mglowinski.otp.service.AppService;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AppController {

    private final AppService appService;

    @Autowired
    public AppController(AppService appService) {
        this.appService = appService;
    }

    @RequestMapping(value = "/hello-world", produces = "application/json")
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

    @RequestMapping(value = "/sum-of-squares", produces = "application/json")
    public ResponseEntity<Object> getSumOfSquares(@RequestParam(value = "n", required = false) Integer n) {
        JSONObject object = new JSONObject();
        int sumOfSquares = appService.getSumOfSquares(n);
        object.put("sumOfSquares", sumOfSquares);
        return ResponseEntity.ok(object.toString());
    }

    @RequestMapping(value = "/fib-series-recursively", produces = "application/json")
    public ResponseEntity<Object> getFibonacciSeriesRecursively(@RequestParam(value = "n", required = false) Integer n) {
        int[] sumOfSquares = appService.getFibonacciSeriesRecursively(n);
        return ResponseEntity.ok(sumOfSquares);
    }

    @RequestMapping(value = "/fib-series-iteratively", produces = "application/json")
    public ResponseEntity<Object> getFibonacciSeriesIteratively(@RequestParam(value = "n", required = false) Integer n) {
        int[] sumOfSquares = appService.getFibonacciSeriesIteratively(n);
        return ResponseEntity.ok(sumOfSquares);
    }
}
