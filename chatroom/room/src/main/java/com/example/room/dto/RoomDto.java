package com.example.room.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomDto {
    private int roomId;
    private String roomName;
    private String roomDescription;
    private Date createdDate;
}
