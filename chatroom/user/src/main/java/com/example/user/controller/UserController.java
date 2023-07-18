package com.example.user.controller;

import com.example.user.dto.UserDto;
import com.example.user.entity.User;
import com.example.user.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

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
        String password = request.getParameter("password");
        UserDto userDto = new UserDto(name,description,password,new Date());
        service.saveUser(userDto);
    }
    @GetMapping("/login")
    public ModelAndView showLogin(){
        return new ModelAndView("login");
    }
    @PostMapping("/login")
    public ModelAndView validateUser(HttpServletRequest request){
        User user = service.checkUser(request.getParameter("userName"),request.getParameter("password"));
        if (user != null){
            ModelAndView modelAndView = new ModelAndView("tempView");
            modelAndView.addObject(user);
            return modelAndView;
        }
        System.out.println("Error");
        return null;
    }
}