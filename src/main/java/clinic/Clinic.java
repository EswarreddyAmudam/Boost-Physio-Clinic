package clinic;

import java.util.ArrayList;

public class Clinic {
    private ArrayList<Patient> patients;
    private ArrayList<Physiotherapist> physiotherapists;

    public Clinic() {
        this.patients = new ArrayList<>();
        this.physiotherapists = new ArrayList<>();
    }

    public void addPatient(Patient patient) {
        patients.add(patient);
    }

    public void addPhysiotherapist(Physiotherapist physio) {
        physiotherapists.add(physio);
    }
    
    public void removePatient(Patient patient) {
        patients.remove(patient);
    }

    public ArrayList<Patient> getPatients() {
        return patients;
    }

    public ArrayList<Physiotherapist> getPhysiotherapists() {
        return physiotherapists;
    }

    public Patient getPatientByName(String name) {
        for (Patient patient : patients) {
            if (patient.getFullName().equalsIgnoreCase(name.trim())) {  // Case-insensitive check
                return patient;
            }
        }
        return null;
    }

    public ArrayList<Physiotherapist> searchByExpertise(String expertise) {
        ArrayList<Physiotherapist> result = new ArrayList<>();
        for (Physiotherapist physio : physiotherapists) {
            if (physio.getExpertise().contains(expertise.trim())) {
                result.add(physio);
            }
        }
        return result;
    }
    public Physiotherapist searchByName(String name) {
        for (Physiotherapist physio : physiotherapists) {
            if (physio.getFullName().equalsIgnoreCase(name)) {
                return physio;
            }
        }
        return null;
    }
}



