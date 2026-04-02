package ie.atu.oop_lab9_h2intro.respository;

import ie.atu.oop_lab9_h2intro.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ReservationRepo extends JpaRepository<Reservation,Integer> {

    List<Reservation> findByReservationDate(LocalDate reservationDate);
}
