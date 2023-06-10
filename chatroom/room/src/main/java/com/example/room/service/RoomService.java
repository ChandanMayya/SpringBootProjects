package com.example.room.service;

import com.example.room.dto.RoomDto;
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
    public Room viewRoom(int id){
        return repository.findById(id).orElse(null);
    }
    public Boolean saveRoom(RoomDto roomDto){
        try {
            Room room = new Room(
                    roomDto.getRoomId(),
                    roomDto.getRoomName(),
                    roomDto.getRoomDescription(),
                    roomDto.getCreatedDate()
            );
            repository.save(room);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
