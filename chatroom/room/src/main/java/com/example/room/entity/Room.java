package com.example.room.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "ROOMS")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private int roomId;
    @Column(name = "ROOM_NAME")
    private String roomName;
    @Column(name = "ROOM_DESCRIPTION")
    private String roomDescription;
    @Column(name = "CREATED_DATE")
    private Date createdDate;
    public Room(String roomName, String roomDescription, Date createdDate){
        this.createdDate = createdDate;
        this.roomDescription = roomDescription;
        this.roomName = roomName;
    }
}
