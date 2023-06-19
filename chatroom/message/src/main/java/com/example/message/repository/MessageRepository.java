package com.example.message.repository;

import com.example.message.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message,Integer> {
    @Query("SELECT m FROM Message m WHERE m.roomId = :roomId AND m.userId = :userId ")
    List<Message> findAllByRoomIdAndUserId(@Param("roomId") int roomId, @Param("userId") int userId);
}
