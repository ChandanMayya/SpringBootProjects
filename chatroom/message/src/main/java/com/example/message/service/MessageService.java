package com.example.message.service;

import com.example.message.dto.MessageDto;
import com.example.message.entity.Message;
import com.example.message.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MessageService {
    @Autowired
    MessageRepository repository;
    public List<MessageDto> messageList(int roomId, int userId){
        List<Message> messagesList = repository.findAllByRoomIdAndUserId(roomId,userId);
        List<MessageDto> messageDto = new ArrayList<>();
        for (Message message: messagesList) {
            MessageDto messageDto1 = new MessageDto();
            messageDto1.setId(message.getId());
            messageDto1.setRoomId(message.getRoomId());
            messageDto1.setUserId(message.getUserId());
            messageDto1.setMessage(message.getMessageTxt());
            messageDto1.setTime(message.getLoggedTime());
            messageDto1.setUserName("Keshava");
            messageDto.add(messageDto1);
        }
        return messageDto;
    }
    public boolean saveMessage(MessageDto messageDto) {
        try {
            Message message = new Message(
                    messageDto.getId(),
                    messageDto.getRoomId(),
                    messageDto.getUserId(),
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
