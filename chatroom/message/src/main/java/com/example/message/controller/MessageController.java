package com.example.message.controller;

import com.example.message.dto.MessageDto;
import com.example.message.entity.Message;
import com.example.message.service.MessageService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpRequest;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/message")
public class MessageController {

    @Autowired
    MessageService service;
    @GetMapping("/pull/{roomId}")
    public List<MessageDto> fetchMessages(HttpSession session, HttpServletRequest request, @PathVariable String roomId){

        // int roomId = Integer.parseInt(String.valueOf(session.getAttribute("roomId")));
        //int userId = Integer.parseInt(String.valueOf(session.getAttribute("userId")));    //This will be used after implementing authentication
        int userId = 1;
        return service.messageList( Integer.parseInt(roomId),userId);
    }
    @PostMapping("/save")
    public void saveMessage(HttpServletRequest request, HttpSession session){
        int id = Integer.parseInt(request.getParameter("userId"));
        int roomId = Integer.parseInt(request.getParameter("roomId"));
        //int userId = Integer.parseInt(String.valueOf(session.getAttribute("userId")));   //This will be used after implementing authentication
        int userId = 1;
        String messageTxt = request.getParameter("message");
        MessageDto message = new MessageDto(id,roomId,userId,messageTxt,new Date());
        if (service.saveMessage(message))
            System.out.println("Success");
        else
            System.out.println("Not saved");
    }

}
