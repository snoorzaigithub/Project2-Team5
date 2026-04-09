package com.example.project2.demo.controller;

import java.time.LocalDateTime;

import com.example.project2.demo.model.Features;
import com.example.project2.demo.model.Rooms;
import com.example.project2.demo.service.FeaturesService;
import com.example.project2.demo.service.RoomsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class RoomsController {

    @Autowired
    private RoomsService roomsService;

    @Autowired
    private FeaturesService featuresService;

    @GetMapping("/")
    public String index() {
        return "redirect:/home";
    }

    @GetMapping("/home")
    public String getAllRooms(Model model) {
        model.addAttribute("rooms", roomsService.getAllRooms());
        model.addAttribute("features", featuresService.getAllFeatures());
        return "home";
    }

    // Detail page for a single room, all users can see it but only admins can edit it.
    @GetMapping("/rooms/{id}")
    public String getRoomById(@PathVariable Long id, Model model) {
        model.addAttribute("rooms", roomsService.getRoomById(id));
        model.addAttribute("features", featuresService.getFeaturesByRoomId(id));
        return "view-room";
    }

    //Separate page for admins that create and edit redirect to.
    @GetMapping("/admin/home")
    public String getAdminHome(Model model) {
        model.addAttribute("rooms", roomsService.getAllRooms());
        model.addAttribute("features", featuresService.getAllFeatures());
        return "admin-home";
    }

    @GetMapping("/admin/home/create")
    public String showCreateForm(Model model) {
        model.addAttribute("rooms", new Rooms());
        return "create-rooms";
    }

    @PostMapping("/admin/home/create")
    public String createRoom(Rooms rooms) {
        roomsService.createRoom(rooms);
        return "redirect:/admin/home";
    }

    @GetMapping("/admin/rooms/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {
        model.addAttribute("rooms", roomsService.getRoomById(id));
        model.addAttribute("features", featuresService.getFeaturesByRoomId(id));
        return "edit-rooms";
    }

    @PostMapping("/admin/rooms/{id}/edit")
    public String updateRoom(Rooms rooms, Features features) {
        roomsService.updateRoom(rooms);
        featuresService.updateFeatures(features);
        return "redirect:/admin/home";
    }

    @PostMapping("/admin/rooms/{id}/delete")
    public String deleteRoom(@PathVariable Long id) {
        roomsService.deleteRoom(id);
        return "redirect:/admin/home";
    }

    @PostMapping("/rooms/{id}/reserved")
    public ResponseEntity<Void> updateRoomReservation(@PathVariable Long id) {
        Rooms rooms = roomsService.getRoomById(id);
        rooms.getReserved().add(LocalDateTime.now());
        roomsService.updateRoom(rooms);
        return ResponseEntity.ok().build();
    }
}


