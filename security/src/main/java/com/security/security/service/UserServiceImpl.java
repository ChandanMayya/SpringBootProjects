package com.security.security.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService{
    public List<String> fetchUsers(){
        List<String> users = new ArrayList<>();
        users.add("Keshava");
        users.add("Shankara");
        users.add("Venkata");
        users.add("Durga");
        return users;
    }
}
