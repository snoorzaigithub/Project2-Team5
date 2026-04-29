package com.example.project2.demo.controller;

import com.example.project2.demo.model.Rooms;
import com.example.project2.demo.service.RoomsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class RoomsController {

    private final RoomsService roomsService;

    public RoomsController(RoomsService roomsService) {
        this.roomsService = roomsService;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("rooms", roomsService.getAllRooms());
        return "index";
    }

    @GetMapping("/signup")
    public String signup() {
        return "signup";
    }

    @GetMapping("/admin")
    public String admin(Model model) {
        model.addAttribute("rooms", roomsService.getAllRooms());
        model.addAttribute("room", new Rooms());
        return "admin";
    }

    @PostMapping("/admin/add")
    public String addRoom(@ModelAttribute Rooms room) {
        room.setBooked(false);
        roomsService.saveRoom(room);
        return "redirect:/admin";
    }

    @PostMapping("/admin/delete/{id}")
    public String deleteRoom(@PathVariable Long id) {
        roomsService.deleteRoom(id);
        return "redirect:/admin";
    }

    @PostMapping("/book/{id}")
    public String bookRoom(@PathVariable Long id) {
        roomsService.bookRoom(id);
        return "redirect:/";
    }
}