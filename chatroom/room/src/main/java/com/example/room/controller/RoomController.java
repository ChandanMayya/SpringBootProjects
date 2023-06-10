package com.example.room.controller;

import com.example.room.dto.RoomDto;
import com.example.room.entity.Room;
import com.example.room.service.RoomService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/room")
public class RoomController {

    @Autowired
    RoomService service;
    @GetMapping("/list")
    public List<Room> listRooms(){
        return service.listRooms();
    }
//    @GetMapping("/listRooms/{userId}")
//    public String viewRooms(){
//        return null;
//    }
    @GetMapping("/view/{id}")
    public Room viewRoom(int id){
        return service.viewRoom(id);
    }
    @GetMapping("/enter/{id}")
    public String enterRoom(){
        return null;        //not yet implemented roomUsers..
    }
    @PostMapping("/save")
    public void createRoom(HttpServletRequest request){
        int roomId = Integer.parseInt(request.getParameter("roomId"));
        String roomName = request.getParameter("roomName");
        String roomDescription = request.getParameter("roomDescription");
        RoomDto roomDto = new RoomDto(roomId,roomName,roomDescription,new Date());
        service.saveRoom(roomDto);
     }
}
