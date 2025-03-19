package clinic;

import java.util.ArrayList;

public class Treatment {
    private String name;
    private Physiotherapist physiotherapist;
    private ArrayList<Appointment> schedule;

    public Treatment(String name, Physiotherapist physiotherapist) {
        this.name = name;
        this.physiotherapist = physiotherapist;
        this.schedule = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public Physiotherapist getPhysiotherapist() {
        return physiotherapist;
    }

    public void addAppointment(Appointment appointment) {
        schedule.add(appointment);
    }

    public ArrayList<Appointment> getSchedule() {
        return schedule;
    }
}
