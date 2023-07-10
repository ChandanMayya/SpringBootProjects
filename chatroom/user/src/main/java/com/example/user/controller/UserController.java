package com.example.user.controller;

import com.example.user.dto.UserDto;
import com.example.user.entity.User;
import com.example.user.service.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.io.IOException;
import java.net.http.HttpHeaders;
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
        UserDto userDto = new UserDto(name,password,description,new Date());
        service.saveUser(userDto);
    }
    @PostMapping("/loginValidate")
    public ModelAndView validateUser(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException {
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        User user = service.validateUser(userName, password);
        if (user != null) {
            Cookie userNameCookie = new Cookie("userName", userName);
            response.addCookie(userNameCookie);
            return new ModelAndView("redirect:http://localhost:9000/room/roomHome");
        }
//        else
//           return new RedirectView("localhost:9000/user/");
        return null;
    }
    @GetMapping("/")
    public ModelAndView redirectToLoginPage(){
        return new ModelAndView("login.html");
    }
}
