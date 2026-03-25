package ie.atu.oop_lab9_h2intro.model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class Reservation {
    private Long reservationId;

    @NotBlank(message = "EquipmentTag is required")
    private String equipmentTag;

    @NotBlank(message = "studentEmail is required")
    @Email(message = "studentEmail must be a valid email address")
    private String studentEmail;

    @NotNull(message = "ReservationDate is required")
    private LocalDate reservationDate;

    @Min(value = 0, message = "Start hour must be between 0 & 23")
    @Max(value = 23, message = "Start hour must be between 0 & 23")
    private int startHour;

    public void setReservationId(long l) {
    }

    public int getDurationHours() {
    }
}
