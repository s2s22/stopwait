package com.example.stopwait.Reservation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservations")
public class ReservationController {

    private final ReservationService reservationService;

    @Autowired

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @PostMapping
    public void cerateReservstion(@RequestBody Reservation reservation) {
        reservationService.reserve(reservation);
    }

    @GetMapping("/{reservation_id}")
    public Reservation getReservation(@PathVariable int restaurant_id) {
        return reservationService.findOne(restaurant_id);
    }

}
