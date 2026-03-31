package ie.atu.oop_lab9_h2intro.exception;

public class ReservationConflictException extends RuntimeException {
    public ReservationConflictException(String timeSlotAlreadyBooked) {
        super(timeSlotAlreadyBooked);
    }

}
