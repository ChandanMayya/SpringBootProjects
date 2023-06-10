package com.example.user.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@Table(name = "USERS")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private int id;
    @Column(name = "USER_NAME")
    private String userName;
    @Column(name = "USER_DESCRIPTION")
    private String userDescription;
    @Column(name = "JOINED_ON")
    private Date joinedDate;

    public User(String userName, String userDescription, Date joinedDate){
        this.userName = userName;
        this.userDescription = userDescription;
        this.joinedDate = joinedDate;
    }
}
