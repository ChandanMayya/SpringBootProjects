package com.example.room.repository;

import com.example.room.entity.RoomUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomUserRepository extends JpaRepository<RoomUser, Integer> {
}
