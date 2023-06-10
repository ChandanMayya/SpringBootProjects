package com.example.room.service;

import com.example.room.entity.Room;
import com.example.room.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomService {
    @Autowired
    RoomRepository repository;

    public List<Room> listRooms(){
        List<Room> rooms = repository.findAll();
        return rooms;
    }
}
