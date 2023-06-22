package com.example.room.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Data
@Table(name = "ROOM_USER")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RoomUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;
    @Column(name = "ROOM_ID")
    private int roomId;
    @Column(name = "USER_ID")
    private int userId;
    @Column(name = "JOINED_DATE")
    private Date joinedDate;

    public  RoomUser(int roomId, int userId, Date joinedDate){
        this.roomId = roomId;
        this.userId = userId;
        this.joinedDate = joinedDate;
    }
}
