package com.example.loginservice;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("ecommerce")
public class LoginController {
    @RequestMapping(value = "/login/user",method = RequestMethod.GET)
    public String login(){
        return "Login Successful";
    }
}
