package com.mglowinski.otp.controller;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AppController {

    @GetMapping("/hello-world")
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
}
