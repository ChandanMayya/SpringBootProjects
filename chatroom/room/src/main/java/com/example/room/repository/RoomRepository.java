package com.example.room.repository;

import com.example.room.dto.RoomDto;
import com.example.room.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RoomRepository extends JpaRepository<Room, Integer> {
//    @Query("SELECT  roomName,roomDescription  FROM Room ")
//    List<RoomDto> findRoomNameAndDescription();
}
