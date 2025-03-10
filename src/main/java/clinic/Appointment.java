package clinic;

public class Appointment {
    private Patient patient;
    private Physiotherapist physiotherapist;
    private String treatmentName;
    private String dateTime;
    private String status;  // "Booked", "Cancelled", "Attended"

    public Appointment(Patient patient, Physiotherapist physiotherapist, String treatmentName, String dateTime) {
        this.patient = patient;
        this.physiotherapist = physiotherapist;
        this.treatmentName = treatmentName;
        this.dateTime = dateTime;
        this.status = "Booked";  // Default status is "Booked"
    }

    public Patient getPatient() {
        return patient;
    }

    public Physiotherapist getPhysiotherapist() {
        return physiotherapist;
    }

    public String getTreatmentName() {
        return treatmentName;
    }

    public String getDateTime() {
        return dateTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
