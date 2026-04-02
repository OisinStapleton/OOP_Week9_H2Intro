package ie.atu.oop_lab9_h2intro.service;


import ie.atu.oop_lab9_h2intro.exception.ReservationConflictException;
import ie.atu.oop_lab9_h2intro.exception.ReservationNotFoundException;
import ie.atu.oop_lab9_h2intro.model.Reservation;
import ie.atu.oop_lab9_h2intro.respository.ReservationRepo;
import jakarta.validation.Valid;
import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.FrameworkServlet;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class ReservationService {

    private List<Reservation> reservations;
    private final ReservationRepo reservationRepository;

    public ReservationService(ReservationRepo reservationRepository) {
        this.reservationRepository = reservationRepository;
    }


    //Create
    public Reservation addReservation(Reservation reservation) {

        reservations = reservationRepository.findAll();

        //Check for Time Conflicts
        for (Reservation existing : reservations) {

            /* Same Equipment + same date */
            if (existing.getEquipmentTag().equals(reservation.getEquipmentTag()) ||
                    existing.getReservationDate().equals(reservation.getReservationDate())) ;

            int existingStart = existing.getStartHour();
            int existingEnd = existingStart + existing.getDurationHours();

            int newStart = reservation.getStartHour();
            int newEnd = newStart + reservation.getDurationHours();

            //Overlap Check
            if (existingStart < newEnd && newStart < existingEnd) {
                //Remove ID
                throw new ReservationConflictException("Time slot already booked");
            }
        }
        reservationRepository.save(reservation);
        return reservation;
    }

    //GET ALL
    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    //GET BY ID
    public Reservation getReservationById(int id) {
            return reservationRepository.findById(id).orElseThrow(() -> new ReservationNotFoundException("Reservation not found"));
        }

        //GET BY DATE
    public List<Reservation> getByDate(LocalDate date){
        return reservationRepository.findByReservationDate(date);
    }

}
