package com.bci.api.controllers;

import com.bci.api.UserService;
import com.bci.api.dtos.UserCreateRequest;
import com.bci.api.dtos.UserCreateResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/users")
    UserCreateResponse save(@RequestBody UserCreateRequest newUser, HttpServletResponse response) {
        UserCreateResponse userResponse;
        if(newUser.validate()) {
            userResponse = userService.create(newUser);
        }else {
           userResponse = new UserCreateResponse(newUser.getErrors());
        }
        if(userResponse.getMensaje() != null){
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
        return userResponse;
    }
}
