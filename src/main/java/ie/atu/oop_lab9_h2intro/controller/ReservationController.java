package ie.atu.oop_lab9_h2intro.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.ArrayList;

@RestController
@RequestMapping("/reservations")
public class ReservationService {

    private final ReservationService reservationService; // tells springboot that it is responsible for class creation

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    // create
    @PostMapping
    public ResponseEntity<Reservation> create(@Valid @RequestBody Reservation reservation) {
        Reservation saved = reservationService.addReservation(reservation);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @GetMapping
    public ResponseEntity<List<Reservation>> getAll() {
        return ResponseEntity.ok(reservationService.getReservationById(id)));
    }
}
