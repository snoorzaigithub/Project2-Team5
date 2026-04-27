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
        return "index";
    }

    @GetMapping("/home")
    public String getAllRooms(Model model) {
        model.addAttribute("rooms", roomsService.getAllRooms());
        model.addAttribute("features", featuresService.getAllFeatures());
        return "home";
    }

    @GetMapping("/rooms/{id}")
    public String getRoomById(@PathVariable Long id, Model model) {
        Rooms room = roomsService.getRoomById(id);

        if (room == null) {
            return "redirect:/home";
        }

        model.addAttribute("room", room);
        model.addAttribute("features", featuresService.getFeaturesByRoomId(id));
        return "view-room";
    }

    @GetMapping("/admin/home")
    public String getAdminHome(Model model) {
        model.addAttribute("rooms", roomsService.getAllRooms());
        model.addAttribute("features", featuresService.getAllFeatures());
        return "admin-home";
    }

    @GetMapping("/admin/home/create")
    public String showCreateForm(Model model) {
        model.addAttribute("room", new Rooms());
        model.addAttribute("feature", new Features());
        return "create-rooms";
    }

    @PostMapping("/admin/home/create")
    public String createRoom(@ModelAttribute("room") Rooms room,
                             @ModelAttribute("feature") Features feature) {
        Rooms createdRoom = roomsService.createRoom(room);

        feature.setRoom(createdRoom);
        featuresService.updateFeatures(feature);

        return "redirect:/admin/home";
    }

    @GetMapping("/admin/rooms/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {
        Rooms room = roomsService.getRoomById(id);

        if (room == null) {
            return "redirect:/admin/home";
        }

        model.addAttribute("room", room);

        Features feature = new Features();
        if (!featuresService.getFeaturesByRoomId(id).isEmpty()) {
            feature = featuresService.getFeaturesByRoomId(id).get(0);
        }

        model.addAttribute("feature", feature);
        return "edit-rooms";
    }

    @PostMapping("/admin/rooms/{id}/edit")
    public String updateRoom(@PathVariable Long id,
                             @ModelAttribute("room") Rooms room,
                             @ModelAttribute("feature") Features feature) {
        room.setId(id);

        Rooms updatedRoom = roomsService.updateRoom(room);

        feature.setRoom(updatedRoom);
        featuresService.updateFeatures(feature);

        return "redirect:/admin/home";
    }

    @PostMapping("/admin/rooms/{id}/delete")
    public String deleteRoom(@PathVariable Long id) {
        featuresService.deleteFeaturesByRoomId(id);
        roomsService.deleteRoom(id);
        return "redirect:/admin/home";
    }

    @PostMapping("/rooms/{id}/reserved")
    public ResponseEntity<Void> updateRoomReservation(@PathVariable Long id) {
        Rooms room = roomsService.getRoomById(id);

        if (room == null) {
            return ResponseEntity.notFound().build();
        }

        if (room.getReserved() == null) {
            room.setReserved(new ArrayList<LocalDateTime>());
        }

        room.getReserved().add(LocalDateTime.now());
        roomsService.updateRoom(room);

        return ResponseEntity.ok().build();
    }
}