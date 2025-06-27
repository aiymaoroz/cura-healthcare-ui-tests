package models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents the details of an appointment.
 * <p>
 * This model holds information about the facility, readmission status,
 * healthcare program, appointment date, and any additional comments.
 * <p>
 * Lombok annotations are used to generate boilerplate code such as
 * getters, setters, constructors.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentDetails {
    private String facility;
    private String readmission;
    private String program;
    private String date;
    private String comment;
}
