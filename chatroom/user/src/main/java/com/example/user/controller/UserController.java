package com.example.user.controller;

import com.example.user.dto.UserDto;
import com.example.user.entity.User;
import com.example.user.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.bouncycastle.math.raw.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService service;
    @GetMapping("/createAccount")
    public ModelAndView createFrom(){
        return new ModelAndView("createAccount");
    }
    @PostMapping("/create")
    public ModelAndView createUser(HttpServletRequest request){
        String name = request.getParameter("userName");
        String description = request.getParameter("userDescription");
        String password = request.getParameter("password");
        UserDto userDto = new UserDto(name,description,password,new Date());
        try {
            service.saveUser(userDto);
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return  new ModelAndView("login");
    }

    @GetMapping("/checkUserNameAvailability")
    public boolean checkUserNameAvailability(@RequestParam("userName") String userName) {
        // Perform the check using your service method or repository
        return service.isUserNameTaken(userName);
    }

    @GetMapping("/login")
    public ModelAndView showLogin(){
        return new ModelAndView("login");
    }
    @GetMapping("/login/{errorCode}")
    public ModelAndView showLoginWithError(@PathVariable("errorCode")int errorCode ){
        ModelAndView modelAndView = new ModelAndView("login");
        modelAndView.addObject("errorCode", errorCode);
        return modelAndView;
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
        ModelAndView modelAndView = new  ModelAndView("login");
        modelAndView.addObject("errorCode", 1);
        return modelAndView;
    }
}
