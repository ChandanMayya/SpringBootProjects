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
import java.util.*;

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
        return roomRepository.findAll();
    }
    public Room viewRoom(int id) throws ParseException {
        Room room = roomRepository.findById(id).orElse(null);
        String pattern = "dd-MM-yyyy HH:mm:ss";
        SimpleDateFormat format = new SimpleDateFormat(pattern, new Locale("en", "IN"));
        assert room != null;
        String  dateStr = format.format(room.getCreatedDate());
        room.setCreatedDate(format.parse(dateStr));
        System.out.println(room.getCreatedDate());
        return room;
    }
    public int saveRoom(RoomDto roomDto){
        Room room;
        try {
            room = new Room(
                    roomDto.getRoomName(),
                    roomDto.getRoomDescription(),
                    roomDto.getCreatedDate()
            );
            room = roomRepository.save(room);
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }
        return room.getRoomId();
    }
    public Boolean saveRoomUser(RoomUser roomUser){
        try{
            roomUserRepository.save(roomUser);
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

        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        SimpleDateFormat outputFormat = new SimpleDateFormat("dd-MM-yyyy @ hh-mm a");

        Date date;

        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> requestEntity = new HttpEntity<>(headers);
        ResponseEntity<String > responseEntity = restTemplate.exchange("http://localhost:9000/message/pull/{roomId}", HttpMethod.GET,requestEntity, String.class,roomId);
        String responseBody = responseEntity.getBody();
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
                date = inputFormat.parse(jsonObject.get("time").asText());
                message.setMessage(jsonObject.get("message").asText());
                message.setTime(outputFormat.format(date));
                messagesDto.add(message);
            }
        } catch (ParseException | JsonProcessingException e) {
            e.printStackTrace();
        }

        return messagesDto;
    }
    public List<Room> getRoomsCreatedByUser(int userId){
        List<Integer> rooms = roomUserRepository.getRoomsCreatedByUser(userId);
        List<Room> roomList = new ArrayList<>();
        for (Integer roomId : rooms){
            Room tempRoom = roomRepository.getRoomByRoomId(roomId,false);
            if (tempRoom != null)
                roomList.add(tempRoom);
        }
        return roomList;
    }

    public boolean checkRoomForUser(int userId, int roomId){
        List<Integer> rooms = roomUserRepository.getRoomsCreatedByUser(userId);
        for (Integer roomID : rooms){
            if (roomID == roomId)
                return true;
        }
        return false;
    }

    public boolean deleteRoomById(int roomId){
        try {
            Room room = roomRepository.getRoomByRoomId(roomId, false);
            room.setDeleted(true);
            roomRepository.save(room);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
