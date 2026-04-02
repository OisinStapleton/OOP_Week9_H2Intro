package ie.atu.oop_lab9_h2intro.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@Entity // JPA creates table of database

public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
