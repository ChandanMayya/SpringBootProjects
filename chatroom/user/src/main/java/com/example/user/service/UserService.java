package com.example.user.service;

import com.example.user.dto.UserDto;
import com.example.user.entity.User;
import com.example.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    UserRepository repository;

    public void saveUser(UserDto userDto){
        try {
            User user = new User(
                    userDto.getUserName(),
                    userDto.getPassword(),
                    userDto.getUserDescription(),
                    userDto.getJoinedDate()
            );
            repository.save(user);
        }catch (Exception e){
            e.printStackTrace();
            return;
        }
    }
    public User validateUser(String userName, String password){
        try {
            List<User> userList = repository.findAll();
            for (User user : userList)
                if (user.getUserName().equals(userName) && user.getPassword().equals(password))
                    return user;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
