package com.example.room.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MessageDto {
    private int id;
    private int roomId;
    private String userName;
    private String message;
    private String time;
}