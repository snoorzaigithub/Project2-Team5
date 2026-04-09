package com.example.project2.demo.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;

import com.example.project2.demo.model.Features;
import com.example.project2.demo.model.Rooms;
import com.example.project2.demo.service.FeaturesService;
import com.example.project2.demo.service.RoomsService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class RoomsController {
    private final RoomsService roomsService;
    private final FeaturesService featuresService;

    public RoomsController(RoomsService roomsService, FeaturesService featuresService) {
        this.roomsService = roomsService;
        this.featuresService = featuresService;
    }

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
        model.addAttribute("features", new Features());
        return "create-rooms";
    }

    @PostMapping("/admin/home/create")
    public String createRoom(Rooms room, Features feature) {
        Rooms createdRoom = roomsService.createRoom(room);

        if (feature != null) {
            feature.setRoom(createdRoom);
            featuresService.updateFeatures(feature);
        }

        return "redirect:/admin/home";
    }

    @GetMapping("/admin/rooms/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {
        model.addAttribute("rooms", roomsService.getRoomById(id));
        model.addAttribute("features", featuresService.getFeaturesByRoomId(id));
        return "edit-rooms";
    }

    @PostMapping("/admin/rooms/{id}/edit")
    public String updateRoom(Rooms room, Features feature) {
        Rooms updatedRoom = roomsService.updateRoom(room);
        if (feature != null) {
            feature.setRoom(updatedRoom);
            featuresService.updateFeatures(feature);
        }
        return "redirect:/admin/home";
    }

    @PostMapping("/admin/rooms/{id}/delete")
    public String deleteRoom(@PathVariable Long id) {
        roomsService.deleteRoom(id);
        featuresService.deleteFeaturesByRoomId(id);
        return "redirect:/admin/home";
    }

    @PostMapping("/rooms/{id}/reserved")
    public ResponseEntity<Void> updateRoomReservation(@PathVariable Long id) {
        Rooms room = roomsService.getRoomById(id);
        if (room == null) {
            return ResponseEntity.notFound().build();
        }
        if (room.getReserved() == null) {
            room.setReserved(new ArrayList<>());
        }
        room.getReserved().add(LocalDateTime.now());
        roomsService.updateRoom(room);
        return ResponseEntity.ok().build();
    }
}


