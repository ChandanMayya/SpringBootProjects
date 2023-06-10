package com.example.room.service;

import com.example.room.dto.RoomDto;
import com.example.room.entity.Room;
import com.example.room.entity.RoomUser;
import com.example.room.repository.RoomRepository;
import com.example.room.repository.RoomUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class RoomService {
    @Autowired
    RoomRepository roomRepository;
    @Autowired
    RoomUserRepository roomUserRepository;

    public List<Room> listRooms(){
        List<Room> rooms = roomRepository.findAll();
        return rooms;
    }
    public Room viewRoom(int id){
        return roomRepository.findById(id).orElse(null);
    }
    public Boolean saveRoom(RoomDto roomDto){
        try {
            Room room = new Room(
                    roomDto.getRoomId(),
                    roomDto.getRoomName(),
                    roomDto.getRoomDescription(),
                    roomDto.getCreatedDate()
            );
            roomRepository.save(room);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }
    public void joinRoom(int userId, int roomId){
        RoomUser roomUser = new RoomUser(roomId,userId,new Date());
        try {
            roomUserRepository.save(roomUser);
        }catch (Exception e){
            return;
        }
    }
}
