package com.example.message.repository;

import com.example.message.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public interface MessageRepository extends JpaRepository<Message,Integer> {
    @Query("SELECT m FROM Message m WHERE m.roomId = :roomId ")
    List<Message> findAllByRoomIdAndUserId(@Param("roomId") int roomId);
}
