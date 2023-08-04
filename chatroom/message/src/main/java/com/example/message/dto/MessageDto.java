package com.example.message.dto;

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
    private int userId;
    private String userName;
    private String message;
    private String time;

    public MessageDto(int id, int roomId, int userId, String messageTxt, Date date) {
    }
    public MessageDto(int roomId,int userId, String userName, String message, String time){
        this.roomId = roomId;
        this.userId = userId;
        this.userName = userName;
        this.message = message;
        this.time = time;
    }
}
