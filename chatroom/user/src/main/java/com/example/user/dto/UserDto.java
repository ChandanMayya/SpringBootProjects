package com.example.user.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class UserDto {
    private int id;
    private String userName;
    private String userDescription;
    private String password;
    private Date joinedDate;

    public UserDto(String userName, String userDescription, String password, Date joinedDate){
        this.userName = userName;
        this.userDescription = userDescription;
        this.password = password;
        this.joinedDate = joinedDate;
    }
}