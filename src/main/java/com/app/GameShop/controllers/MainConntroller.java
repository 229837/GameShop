package com.app.GameShop.controllers;

import com.app.GameShop.services.PostgreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainConntroller {

    @Autowired
    private PostgreService postgreService;

    @GetMapping("/")
    public String mainPage() {
        return postgreService.read();
    }
}
