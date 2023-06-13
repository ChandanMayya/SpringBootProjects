package com.example.message.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "MESSAGE_STORE")
public class Message {
    @Id
 //   @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;
    @Column(name = "ROOM_ID")
    private int roomId;
    @Column(name = "USER_ID")
    private int userId;
    @Column(name = "MESSAGE")
    private String messageTxt;
    @Column(name = "LOGGED_TIME")
    private Date loggedTime;
}