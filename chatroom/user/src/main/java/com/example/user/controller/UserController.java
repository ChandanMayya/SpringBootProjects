package com.example.user.controller;

import com.example.user.dto.UserDto;
import com.example.user.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService service;
    @PostMapping("/create")
    public void createUser(HttpServletRequest request){
        String name = request.getParameter("userName");
        String description = request.getParameter("description");
        UserDto userDto = new UserDto(name,description,new Date());
        service.saveUser(userDto);
    }
    @GetMapping("/test")
    public String  ping(){
        return "Hi from user endpoint!";
    }
}
