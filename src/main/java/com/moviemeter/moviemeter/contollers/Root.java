package com.moviemeter.moviemeter.contollers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Root {

    @GetMapping("/")
    String sayHello() {
        return "API is running";
    }
}
