package com.example.reservation_manager.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.reservation_manager.Service.ReservationService;



@RestController
@RequestMapping("/Reservation")
public class ReservationManagerController {
    private final ReservationService service;

    public ReservationManagerController(ReservationService service) {
        this.service = service;
    }

    //@GetMapping("/Reservations")
   // public String addReservation(@RequestBody ReservationRequest request) {
        
   //     return "";
   // }
    

    
}
