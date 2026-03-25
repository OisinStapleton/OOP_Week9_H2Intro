package ie.atu.oop_lab9_h2intro.service;

import ie.atu.oop_lab9_h2intro.model.Reservation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/reservations")
public class ReservationService {
    private List<Reservation> reservations = new ArrayList<>();
    private long nextId = 1;

    public Reservation addReservation(Reservation reservation) {

        //Assign ID
        reservation.setReservationId(nextId++);

        //Check for time conflicts
        for(Reservation existing :  reservations){

            // Same Equipment + same date
            if(existing.getEquipmentTag().equals(reservation.getEquipmentTag())) &&
            existing.getReservationDate().equals(reservation.getReservationDate()){

                int existingStart = existing.getStartHour();
                int existingEnd = existingStart + existing.getDurationHours();

                int newStart = reservation.getStartHour();
                int newEnd = newStart + reservation.getDurationHours();

                //Overlap Check
            if(existingStart < newEnd && newStart < existingEnd){
                //RemoveID
                reservation.setReservationId(nextId--);
                throw new ReservationConflictException("Time slot already booked");
            }
            }
        }
        reservations.add(reservation);
        return reservation;
    }
    //GetAll
    public List<Reservation> getAllReservations() {
        return reservations;
    }
    //GetByID
    public Reservation getReservationById(long id) {
        for(Reservation reservation : reservations){
            if(reservation.getReservationById().equals(id)){
                return reservation;
            }
        }
        throw new ReservationNotFoundException("Reservation not found");
    }
}
