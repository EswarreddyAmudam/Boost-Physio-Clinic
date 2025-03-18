package clinic;

import java.util.ArrayList;

public class Physiotherapist extends Person {
    private final ArrayList<String> expertise;
    private final ArrayList<Treatment> treatments;

    public Physiotherapist(int id, String fullName, String address, String phoneNumber, ArrayList<String> expertise) {
        super(id, fullName, address, phoneNumber);
        this.expertise = expertise;
        this.treatments = new ArrayList<>();
    }

    public void addTreatment(Treatment treatment) {
        treatments.add(treatment);
    }

    public ArrayList<Treatment> getTreatments() {
        return treatments;
    }

    public boolean hasExpertise(String area) {
        return expertise.contains(area);
    }
    public ArrayList<String> getExpertise() {
        return expertise;
    }
}
