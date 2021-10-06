package com.bci.api.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

    @GetMapping (path = "/")
    public String status(){
        return "Ok";
    }
}
