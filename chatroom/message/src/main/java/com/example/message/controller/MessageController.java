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
import java.text.ParseException;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/message")
public class MessageController {

    @Autowired
    MessageService service;
    @GetMapping("/pull/{roomId}")
    public List<MessageDto> fetchMessages(HttpSession session, HttpServletRequest request, @PathVariable String roomId) throws ParseException {
        List<MessageDto> messages =  service.messageList( Integer.parseInt(roomId));
        return  messages;
    }
    @PostMapping("/save")
    public void saveMessage(@RequestParam("roomId") int rId, @RequestParam("userId") int userId, @RequestBody TextRequest textRequest , HttpServletRequest request, HttpSession session){
        String messageTxt = textRequest.getText();
        MessageDto message = new MessageDto(rId,userId,"Dummy",messageTxt,String.valueOf(new Date()));
        if (service.saveMessage(message))
            System.out.println("Success");
        else
            System.out.println("Not saved");
    }

}
