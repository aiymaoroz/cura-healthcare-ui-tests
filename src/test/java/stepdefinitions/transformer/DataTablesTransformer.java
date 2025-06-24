package stepdefinitions.transformer;

import io.cucumber.java.DataTableType;
import models.AppointmentDetails;

import java.util.Map;

/**
 * Cucumber data table transformer for converting table rows into {@link AppointmentDetails} objects.
 * <p>
 * Registers a custom transformer method to map each row of a Cucumber data table
 * to an instance of {@link AppointmentDetails} using the @DataTableType annotation.
 */
public class DataTablesTransformer {
    @DataTableType
    public AppointmentDetails appointmentDetails(Map<String, String> entry) {
        String facility = entry.get("facility");
        String readmission = entry.get("readmission");
        String program = entry.get("program");
        String date = entry.get("date");
        String comment = entry.get("comment");
        return new AppointmentDetails(facility, readmission, program, date, comment);
    }
}
