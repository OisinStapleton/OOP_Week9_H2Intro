package ie.atu.oop_lab9_h2intro.respository;

import ie.atu.oop_lab9_h2intro.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepo extends JpaRepository<Reservation,Integer> {

}
