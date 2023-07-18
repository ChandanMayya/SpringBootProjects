package com.example.user.service;

import com.example.user.dto.UserDto;
import com.example.user.entity.User;
import com.example.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepository repository;

    public void saveUser(UserDto userDto){
        try {
            User user = new User(
                    userDto.getUserName(),
                    userDto.getUserDescription(),
                    userDto.getPassword(),
                    userDto.getJoinedDate()
            );
            repository.save(user);
        }catch (Exception e){
            e.printStackTrace();
            return;
        }
    }
    public User checkUser(String userName, String password){
        User user = repository.findByUserName(userName);
        if (user.getPassword().equals(password))
            return user;
        return  null;
    }

    public boolean isUserNameTaken(String userName) {
        User existingUser = repository.findByUserName(userName);
        return existingUser != null;
    }
}