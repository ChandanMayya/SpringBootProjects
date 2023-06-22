package com.security.security.controller;

import com.security.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@RequestMapping("/user")
public class ControllerClass {
    @Autowired
    UserService userService;
    @GetMapping("/greet")
    public String displayGreetings(){
        return "Namaste";
    }
    @GetMapping("/fetch")
    public List<String> listUsers(){
        return userService.fetchUsers();
    }
}
