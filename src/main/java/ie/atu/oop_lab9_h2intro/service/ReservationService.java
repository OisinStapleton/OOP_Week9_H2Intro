package ie.atu.oop_lab9_h2intro.service;


import ie.atu.oop_lab9_h2intro.exception.ReservationConflictException;
import ie.atu.oop_lab9_h2intro.exception.ReservationNotFoundException;
import ie.atu.oop_lab9_h2intro.model.Reservation;
import jakarta.annotation.Nullable;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.FrameworkServlet;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class ReservationService {

    private List<Reservation> reservations = new ArrayList<>();
    private int nextId = 1;

    public ReservationService(FrameworkServlet frameworkServlet) {
    }

    //Create
    public Reservation addReservation(Reservation reservation) {
        //Assign ID
        reservation.setReservationId(nextId++);

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
                reservation.setStartHour(nextId--);
                throw new ReservationConflictException("Time slot already booked");
            }
        }
        reservations.add(reservation);
        return reservation;
    }

    public List<Reservation> getAllReservations()
    {
        return reservations;
    }

    //GET BY ID
    public Reservation getReservationById(int id){
        for (Reservation reservation : reservations) {
            if(Objects.equals(reservation.getReservationId(), id)){ //this line is different from tutorial (long to int issue)
                return reservation;
            }
        }

        throw new ReservationNotFoundException("Reservation not found");
    }
}
