package com.mglowinski.otp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/api")
public class AppController {

    @GetMapping("/fibonacci-series")
    public Principal user(Principal principal) {
        return principal;
    }
}
