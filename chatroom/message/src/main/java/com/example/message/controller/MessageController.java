package com.example.message.controller;

import com.example.message.dto.MessageDto;
import com.example.message.entity.Message;
import com.example.message.service.MessageService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.http.HttpRequest;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/message")
public class MessageController {

    @Autowired
    MessageService service;
    @GetMapping("/pull")
    public List<Message> fetchMessages(){
        return service.messageList();
    }
    @PostMapping("/save")
    public void saveMessage(HttpServletRequest request){
        int id = Integer.parseInt(request.getParameter("userId"));
        int roomId = Integer.parseInt(request.getParameter("roomId"));
        String messageTxt = request.getParameter("message");
        MessageDto message = new MessageDto(id,roomId,messageTxt,new Date());
        if (service.saveMessage(message))
            System.out.println("Success");
        else
            System.out.println("Not saved");
    }

}
