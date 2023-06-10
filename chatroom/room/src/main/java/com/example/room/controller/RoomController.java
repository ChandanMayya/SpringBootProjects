package com.example.room.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/room")
public class RoomController {

    @GetMapping("/list")
    public String listRooms(){
        return null;
    }
    @GetMapping("/view/{id}")
    public String viewRoom(){
        return null;
    }
    @GetMapping("/enter/{id}")
    public String enterRoom(){
        return null;
    }
}
