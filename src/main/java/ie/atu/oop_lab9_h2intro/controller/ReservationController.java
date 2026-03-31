package ie.atu.oop_lab9_h2intro.controller;


import ie.atu.oop_lab9_h2intro.model.Reservation;
import ie.atu.oop_lab9_h2intro.service.ReservationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservations")
public class ReservationController {

    private final ReservationService reservationService; // tells springboot that it is responsible for class creation

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    // Create
    @PostMapping
    public ResponseEntity<Reservation> create(@Valid @RequestBody Reservation reservation) {
        Reservation saved = reservationService.addReservation(reservation);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    //GetAll
    @GetMapping
    public ResponseEntity<List<Reservation>> getAll() {
        return ResponseEntity.ok(reservationService.getAllReservations());
    }

    //Get one
    @GetMapping("{id}")
    public ResponseEntity<Reservation> getById(int id) {
        return ResponseEntity.ok(reservationService.getReservationById(id));
    }
}
