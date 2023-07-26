package com.example.room.repository;

import com.example.room.entity.RoomUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RoomUserRepository extends JpaRepository<RoomUser, Integer> {
    @Query("SELECT r.roomId from RoomUser r where r.userId = :userId")
    List<RoomUser> getRoomsCreatedByUser(@Param("userId")int id);
}
