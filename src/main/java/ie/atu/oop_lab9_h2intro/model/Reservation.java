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
    private int reservationId;

    @NotBlank(message = "equipmentTag is required")
    private String equipmentTag;

    @NotBlank(message = "studentEmail is required")
    @Email(message = "studentEmail must be a valid email address")
    private String studentEmail;

    @NotNull(message = "reservationDate is required")
    private LocalDate reservationDate;

    @Min(value = 0, message = "startHour must be between 0 & 23")
    @Max(value = 23, message = "startHour must be between 0 & 23")
    private int startHour;

    @Min(value = 1, message = "durationHours must be between 1 & 24")
    @Max(value = 24, message = "durationHours must be between 1 & 24")
    private int durationHours;

}
