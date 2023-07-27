package com.example.room.controller;

import com.example.room.dto.MessageDto;
import com.example.room.dto.RoomDto;
import com.example.room.entity.Room;
import com.example.room.entity.RoomUser;
import com.example.room.service.RoomService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.text.ParseException;
import java.util.*;

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
    @GetMapping("/roomHome")
    public ModelAndView roomHome(Model model,HttpServletRequest request, HttpSession session){
        int userId = -1;
        try {
            if (Integer.parseInt( (String) session.getAttribute("userId")) > 0)
                userId = Integer.parseInt( (String) session.getAttribute("userId"));

        }catch (NumberFormatException ex){
            return new ModelAndView("redirect:http://localhost:9000/user/login?formRoom=" + true);
        }

        if (userId == -1){
            return new ModelAndView("redirect:http://localhost:9000/user/login?formRoom=" + true);
        }
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("roomHome");
        model.addAttribute("rooms", service.listRooms());
        return modelAndView;
    }
    @GetMapping("/view/{roomId}")
    public ModelAndView viewRoom(@PathVariable("roomId") int id, HttpServletRequest request, Model model, HttpSession session) throws ParseException {
        int userId = -1;
        try {
            if (Integer.parseInt( (String) session.getAttribute("userId")) > 0)
                userId = Integer.parseInt( (String) session.getAttribute("userId"));

        }catch (NumberFormatException ex){
            return new ModelAndView("redirect:http://localhost:9000/user/login?formRoom=" + true);
        }

        if (userId == -1){
            return new ModelAndView("redirect:http://localhost:9000/user/login?formRoom=" + true);
        }
        if (session.getAttribute("userName") != null)
            System.out.println(session.getAttribute("userName"));
        System.out.println(request.getParameter("roomName"));
        Room room = service.viewRoom(id);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("viewRoom");

        if (service.checkRoomForUser(userId, id))
            modelAndView.addObject("owner", true);
        else
            modelAndView.addObject("owner", false);

        model.addAttribute("room",room);
        return modelAndView;
    }
    @GetMapping("/join")
    public void joinRoom(HttpServletRequest request, HttpSession session){
        int userId = -1;
        try {
            if (Integer.parseInt( (String) session.getAttribute("userId")) > 0)
                userId = Integer.parseInt( (String) session.getAttribute("userId"));

        }catch (NumberFormatException ex){
           return;
        }

        if (userId == -1){
           return;
        }
        int roomId = Integer.parseInt(request.getParameter("roomId"));
//      int userId = (int) session.getAttribute("userId");
        service.joinRoom(userId,roomId);
    }
    @GetMapping("/createRoom")
    public ModelAndView createRoom(HttpSession session){
        int userId = -1;
        try {
            if (Integer.parseInt( (String) session.getAttribute("userId")) > 0)
                userId = Integer.parseInt( (String) session.getAttribute("userId"));

        }catch (NumberFormatException ex){
            return new ModelAndView("redirect:http://localhost:9000/user/login?formRoom=" + true);
        }

        if (userId == -1){
            return new ModelAndView("redirect:http://localhost:9000/user/login?formRoom=" + true);
        }
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("createRoom");
        return modelAndView;
    }

    @PostMapping("/save")
    public ModelAndView createRoom(HttpServletRequest request, HttpSession session){
        int userId = -1;
        try {
            if (Integer.parseInt( (String) session.getAttribute("userId")) > 0)
                userId = Integer.parseInt( (String) session.getAttribute("userId"));

        }catch (NumberFormatException ex){
            return new ModelAndView("redirect:http://localhost:9000/user/login?formRoom=" + true);
        }

        if (userId == -1){
            return new ModelAndView("redirect:http://localhost:9000/user/login?formRoom=" + true);
        }
        String roomName = request.getParameter("roomName");
        String roomDescription = request.getParameter("roomDescription");
        RoomDto roomDto = new RoomDto(roomName,roomDescription,new Date());
        Boolean flag = false;
        int roomId = -1;
        ModelAndView modelAndView = new ModelAndView();
        roomId = service.saveRoom(roomDto);
        if (roomId != -1){
            RoomUser roomUser = new RoomUser(roomId, userId, new Date());
            flag = service.saveRoomUser(roomUser);
            if (flag) {
                modelAndView.setViewName("roomHome");
                return modelAndView;
            }
        }
        System.out.println("Error in saving room!");
        return null;
     }
     @GetMapping("/chat/{roomId}/{roomName}")
     public ModelAndView message(@PathVariable("roomId") int roomId, @PathVariable("roomName") String roomName, HttpServletRequest request, Model model,HttpSession session){
         int userId = -1;
         try {
             if (Integer.parseInt( (String) session.getAttribute("userId")) > 0)
                 userId = Integer.parseInt( (String) session.getAttribute("userId"));

         }catch (NumberFormatException ex){
             return new ModelAndView("redirect:http://localhost:9000/user/login?formRoom=" + true);
         }

         if (userId == -1){
             return new ModelAndView("redirect:http://localhost:9000/user/login?formRoom=" + true);
         }
         List<MessageDto> messages = service.getMessage(roomId);
        ModelAndView modelAndView = new ModelAndView("messages");
        model.addAttribute("messages", messages);
        model.addAttribute("roomName", roomName);
        model.addAttribute("roomId", roomId);
        model.addAttribute("userId", userId);
        model.addAttribute("userName",session.getAttribute("userName"));
        return modelAndView;
     }
     @GetMapping("/landing/{userId}/{userName}")
     public RedirectView landing(@PathVariable("userId") String  userId, @PathVariable("userName") String  userName, HttpSession session){
        session.setAttribute("userId",userId);
        session.setAttribute("userName",userName);
        return new RedirectView("http://localhost:9000/room/roomHome");
     }
     @GetMapping("/logout")
    public RedirectView logout(HttpSession session){
        session.removeAttribute("userId");
        return new RedirectView("http://localhost:9000/user/login");
     }
     @GetMapping("/create")
    public ModelAndView createRoom(){
        return new ModelAndView("createRoom");
     }
     @GetMapping("/userRooms")
    public ModelAndView userRooms(HttpSession session){
         int userId = -1;
         try {
             if (Integer.parseInt( (String) session.getAttribute("userId")) > 0)
                 userId = Integer.parseInt( (String) session.getAttribute("userId"));

         }catch (NumberFormatException ex){
             return new ModelAndView("redirect:http://localhost:9000/user/login?formRoom=" + true);
         }

         if (userId == -1){
             return new ModelAndView("redirect:http://localhost:9000/user/login?formRoom=" + true);
         }
         ModelAndView modelAndView = new ModelAndView("userRooms");
         List<Room> rooms = service.getRoomsCreatedByUser(userId);
         modelAndView.addObject("rooms", rooms);
         return modelAndView;
     }
     @GetMapping("/delete/{roomId}")
    public ModelAndView deleteRoom(HttpSession session, @PathVariable("roomId") int roomId){
         int userId = -1;
         try {
             if (Integer.parseInt( (String) session.getAttribute("userId")) > 0)
                 userId = Integer.parseInt( (String) session.getAttribute("userId"));

         }catch (NumberFormatException ex){
             return new ModelAndView("redirect:http://localhost:9000/user/login?formRoom=" + true);
         }

         if (userId == -1){
             return new ModelAndView("redirect:http://localhost:9000/user/login?formRoom=" + true);
         }
         if (service.checkRoomForUser(userId, roomId))
             if (service.deleteRoomById(roomId)){
                 return new ModelAndView("roomHome");
             }
         return null;
     }
}
