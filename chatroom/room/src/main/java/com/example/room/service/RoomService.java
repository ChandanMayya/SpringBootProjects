package com.example.room.service;

import com.example.room.dto.MessageDto;
import com.example.room.dto.RoomDto;
import com.example.room.entity.Room;
import com.example.room.entity.RoomUser;
import com.example.room.repository.RoomRepository;
import com.example.room.repository.RoomUserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
@Component
public class RoomService {
    @Autowired
    RoomRepository roomRepository;
    @Autowired
    RoomUserRepository roomUserRepository;

    private final RestTemplate restTemplate;

    public RoomService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<Room> listRooms(){
        List<Room> rooms = roomRepository.findAll();
        return rooms;
    }
    public Room viewRoom(int id){
        return roomRepository.findById(id).orElse(null);
    }
    public Boolean saveRoom(RoomDto roomDto){
        try {
            Room room = new Room(
                    roomDto.getRoomName(),
                    roomDto.getRoomDescription(),
                    roomDto.getCreatedDate()
            );
            roomRepository.save(room);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }
    public void joinRoom(int userId, int roomId){
        RoomUser roomUser = new RoomUser(roomId,userId,new Date());
        try {
            roomUserRepository.save(roomUser);
        }catch (Exception e){
            return;
        }
    }
    public List<MessageDto> getMessage(int roomId){
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> requestEntity = new HttpEntity<>(headers);
        ResponseEntity<String > responseEntity = restTemplate.exchange("http://localhost:9000/message/pull/{roomId}", HttpMethod.GET,requestEntity, String.class,roomId);
        String responseBody = responseEntity.getBody();
//        assert responseBody != null;
//        System.out.println(Arrays.toString(responseBody.split(",")));
        List<MessageDto> messagesDto = new ArrayList<>();

        try{
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonArray = objectMapper.readTree(responseBody);
            for (JsonNode jsonObject : jsonArray) {
                MessageDto message = new MessageDto();
                // Access individual properties
                message.setId(jsonObject.get("id").asInt());
                message.setRoomId(jsonObject.get("roomId").asInt());
                message.setUserName(jsonObject.get("userName").asText());
                message.setMessage(jsonObject.get("message").asText());
                message.setTime(jsonObject.get("time").asText());
                messagesDto.add(message);
            }
        } catch (JsonMappingException e) {
            throw new RuntimeException(e);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
//       } catch (ParseException e) {
//            throw new RuntimeException(e);
       }

        return messagesDto;
    }
}
