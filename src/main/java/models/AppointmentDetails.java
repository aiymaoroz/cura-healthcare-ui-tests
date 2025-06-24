package models;

/**
 * Simple POJO class for holding appointment details.
 * Used to store and transfer data such as facility, readmission status,
 * program, date, and comments, when reading from a datatable.
 */
public class AppointmentDetails {
    private String facility;
    private String readmission;
    private String program;
    private String date;
    private String comment;

    public AppointmentDetails(String facility, String readmission, String program, String date, String comment) {
        this.facility = facility;
        this.readmission = readmission;
        this.program = program;
        this.date = date;
        this.comment = comment;
    }

    public String getFacility() {
        return facility;
    }

    public void setFacility(String facility) {
        this.facility = facility;
    }

    public String getReadmission() {
        return readmission;
    }

    public void setReadmission(String readmission) {
        this.readmission = readmission;
    }

    public String getProgram() {
        return program;
    }

    public void setProgram(String program) {
        this.program = program;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
