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
                    userDto.getJoinedDate()
            );
            repository.save(user);
        }catch (Exception e){
            e.printStackTrace();
            return;
        }
    }
}
