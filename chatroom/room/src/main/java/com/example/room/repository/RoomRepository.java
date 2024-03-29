package com.example.room.repository;

import com.example.room.dto.RoomDto;
import com.example.room.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RoomRepository extends JpaRepository<Room, Integer> {
//    @Query("SELECT  roomName,roomDescription  FROM Room ")
//    List<RoomDto> findRoomNameAndDescription();
    @Query("SELECT r FROM Room r where r.roomId = :roomId and r.deleted =:deleted")
    Room getRoomByRoomId(@Param("roomId") Integer id, @Param("deleted") boolean deleted);
}
