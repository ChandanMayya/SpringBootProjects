package com.example.room.controller;

import com.example.room.dto.MessageDto;
import com.example.room.dto.RoomDto;
import com.example.room.entity.Room;
import com.example.room.service.RoomService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

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
    @GetMapping("/roomHome")
    public ModelAndView roomHome(Model model){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("roomHome");
        model.addAttribute("rooms", service.listRooms());
        return modelAndView;
    }
    @GetMapping("/view")
    public ModelAndView viewRoom(HttpServletRequest request, Model model){
        int id = Integer.parseInt(request.getParameter("roomId"));
        System.out.println(request.getParameter("roomName"));
        Room room = service.viewRoom(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("viewRoom");
        model.addAttribute("room",room);
        return modelAndView;
    }
    @GetMapping("/join")
    public void joinRoom(HttpServletRequest request, HttpSession session){
        int roomId = Integer.parseInt(request.getParameter("roomId"));
//      int userId = (int) session.getAttribute("userId");
        int userId = Integer.parseInt(request.getParameter("userId"));
        service.joinRoom(userId,roomId);
    }
    @GetMapping("/createRoom")
    public ModelAndView createRoom(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("createRoom");
        return modelAndView;
    }

    @PostMapping("/save")
    public ModelAndView createRoom(HttpServletRequest request){
        String roomName = request.getParameter("roomName");
        String roomDescription = request.getParameter("roomDescription");
        RoomDto roomDto = new RoomDto(roomName,roomDescription,new Date());
        Boolean flag = false;
        ModelAndView modelAndView = new ModelAndView();
        flag = service.saveRoom(roomDto);
        if (flag){
            modelAndView.setViewName("roomHome");
            return modelAndView;
        }
        System.out.println("Error in saving room!");
        return null;
     }
     @GetMapping("/chat")
     public ModelAndView message(HttpServletRequest request, Model model,HttpSession session){
        //session.setAttribute("roomId",request.getParameter("roomId"));
        //session.setAttribute("roomId",1001);
        int roomId = 1001;
         List<MessageDto> messages = service.getMessage(roomId);
        ModelAndView modelAndView = new ModelAndView("messages");
        model.addAttribute("messages",messages);
        return modelAndView;
     }
}
