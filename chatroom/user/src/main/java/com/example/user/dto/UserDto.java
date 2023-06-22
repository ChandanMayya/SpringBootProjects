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
    private Date joinedDate;

    public UserDto(String userName, String userDescription, Date joinedDate){
        this.userName = userName;
        this.userDescription = userDescription;
        this.joinedDate = joinedDate;
    }
}
