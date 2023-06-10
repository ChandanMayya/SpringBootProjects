package com.example.message.service;

import com.example.message.dto.MessageDto;
import com.example.message.entity.Message;
import com.example.message.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageService {
    @Autowired
    MessageRepository repository;
    public String  messageList(){
        return "message";
    }
    public boolean saveMessage(MessageDto messageDto) {
        try {
            Message message = new Message(
                    messageDto.getId(),
                    messageDto.getRoomId(),
                    messageDto.getMessage(),
                    messageDto.getTime()
            );
            repository.save(message);

        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
