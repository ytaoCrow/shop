package com.ytaocrow.shop.controller;

import com.ytaocrow.shop.dto.UserRegisterRequest;
import com.ytaocrow.shop.model.User;
import com.ytaocrow.shop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/users/register")
    public ResponseEntity<User> register(@RequestBody @Valid UserRegisterRequest userRegisterRequest){

        Integer userId = userService.register(userRegisterRequest);

        User user = userService.getUserById(userId);

        return  ResponseEntity.status(HttpStatus.CREATED).body(user);

    }
}