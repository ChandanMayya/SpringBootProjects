package com.example.message.repository;

import com.example.message.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

@Component
public interface UserRepository  extends JpaRepository<User, Integer> {
    @Query("select u from User u where u.id = :userId")
    User fetchUserById(@Param("userId") int userId);
}
