package clinic;

import java.util.ArrayList;

public class Patient extends Person {
    private ArrayList<Appointment> appointments;

    public Patient(int id, String fullName, String address, String phoneNumber) {
        super(id, fullName, address, phoneNumber);
        this.appointments = new ArrayList<>();
    }

    public void bookAppointment(Appointment appointment) {
        appointments.add(appointment);
        appointment.setStatus("Booked");
    }

    public void cancelAppointment(Appointment appointment) {
        appointments.remove(appointment);
        appointment.setStatus("Cancelled");
    }

    public ArrayList<Appointment> getAppointments() {
        return appointments;
    }
}
