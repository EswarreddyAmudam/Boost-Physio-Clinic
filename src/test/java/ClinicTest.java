import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;
import clinic.*;

class ClinicTest {
    private Clinic clinic;
    private Patient patient;
    private Physiotherapist physio;

    @BeforeEach
    void setUp() {
        clinic = new Clinic();
        patient = new Patient(1, "Test user", "123 Street", "1234567890");
        ArrayList<String> expertise = new ArrayList<>();
        expertise.add("Physiotherapy");
        physio = new Physiotherapist(2, "Dr. Smith", "456 Avenue", "9876543210", expertise);
    }

    @Test
    void testAddPatient() {
        clinic.addPatient(patient);
        assertEquals(1, clinic.getPatients().size());
    }

    @Test
    void testRemovePatient() {
        clinic.addPatient(patient);
        clinic.removePatient(patient);
        assertEquals(0, clinic.getPatients().size());
    }

    @Test
    void testSearchByExpertise() {
        clinic.addPhysiotherapist(physio);
        assertEquals(1, clinic.searchByExpertise("Physiotherapy").size());
    }

    @Test
    void testSearchByName() {
        clinic.addPhysiotherapist(physio);
        assertNotNull(clinic.searchByName("Dr. Smith"));
    }
    
    

    @Test
    void testBookAppointment() {
        Treatment treatment = new Treatment("Massage", physio);
        Appointment appointment = new Appointment(patient, physio, treatment.getName(), "2025-03-09 10:00 AM");
        patient.bookAppointment(appointment);
        assertEquals(1, patient.getAppointments().size());
    }

    @Test
    void testCancelAppointment() {
        Treatment treatment = new Treatment("Massage", physio);
        Appointment appointment = new Appointment(patient, physio, treatment.getName(), "2025-03-09 10:00 AM");
        patient.bookAppointment(appointment);
        patient.cancelAppointment(appointment);
        assertEquals(0, patient.getAppointments().size());
    }
}
