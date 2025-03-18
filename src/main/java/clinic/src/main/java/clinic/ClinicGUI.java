package clinic;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import com.itextpdf.kernel.pdf.*;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClinicGUI {
    private final Clinic clinic;
    private final JTextArea outputArea;

    public ClinicGUI() {
        clinic = new Clinic();
        setupDemoData();

        JFrame frame = new JFrame("Boost Physio Clinic");
        frame.setSize(600, 450);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 1));

        JButton bookButton = new JButton("Book Appointment");
        JButton cancelButton = new JButton("Cancel Appointment");
        JButton reportButton = new JButton("Generate Report");
        JButton pdfButton = new JButton("Export PDF Report");

        outputArea = new JTextArea(10, 40);
        outputArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputArea);

        panel.add(bookButton);
        panel.add(cancelButton);
        panel.add(reportButton);
        panel.add(pdfButton);
        panel.add(scrollPane);

        frame.add(panel);
        frame.setVisible(true);

        bookButton.addActionListener(e -> bookAppointment());
        cancelButton.addActionListener(e -> cancelAppointment());
        reportButton.addActionListener(e -> generateReport());
        pdfButton.addActionListener(e -> {
            try {
                exportReportToPDF();
            } catch (IOException ex) {
                Logger.getLogger(ClinicGUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    private void setupDemoData() {
        // Creating Patients
        Patient patient1 = new Patient(1, "Test user", "123 Street", "1234567890");
        Patient patient2 = new Patient(2, "Jane smith", "456 Avenue", "9876543210");
        clinic.addPatient(patient1);
        clinic.addPatient(patient2);

        // Creating Physiotherapists with expertise
        ArrayList<String> expertise1 = new ArrayList<>(Arrays.asList("Massage"));
        ArrayList<String> expertise2 = new ArrayList<>(Arrays.asList("Rehabilitation"));

        Physiotherapist physio1 = new Physiotherapist(3, "Dr. Brown", "789 Lane", "5554443333", expertise1);
        Physiotherapist physio2 = new Physiotherapist(4, "Dr. Green", "321 Road", "6665554444", expertise2);

        clinic.addPhysiotherapist(physio1);
        clinic.addPhysiotherapist(physio2);

        // Creating Treatments
        Treatment massage = new Treatment("Massage", physio1);
        Treatment rehab = new Treatment("Rehabilitation", physio2);

        // Booking Demo Appointments
        Appointment appointment1 = new Appointment(patient1, physio1, massage.getName(), new Date().toString());
        Appointment appointment2 = new Appointment(patient2, physio2, rehab.getName(), new Date().toString());

        patient1.bookAppointment(appointment1);
        patient2.bookAppointment(appointment2);
    }

    private void bookAppointment() {
        if (clinic.getPatients().isEmpty() || clinic.getPhysiotherapists().isEmpty()) {
            outputArea.setText("No patients or physiotherapists registered.");
            return;
        }

        // Dropdown for Patients
        String[] patientNames = clinic.getPatients().stream().map(Patient::getFullName).toArray(String[]::new);
        String selectedPatient = (String) JOptionPane.showInputDialog(null, "Select Patient:", 
            "Book Appointment", JOptionPane.QUESTION_MESSAGE, null, patientNames, patientNames[0]);

        if (selectedPatient == null) return; // User canceled selection

        // Dropdown for Expertise
        String[] expertiseOptions = {"Massage", "Rehabilitation"};
        String selectedExpertise = (String) JOptionPane.showInputDialog(null, "Select Expertise:", 
            "Book Appointment", JOptionPane.QUESTION_MESSAGE, null, expertiseOptions, expertiseOptions[0]);

        if (selectedExpertise == null) return; // User canceled selection

        Patient patient = clinic.getPatientByName(selectedPatient);
        ArrayList<Physiotherapist> physios = clinic.searchByExpertise(selectedExpertise);

        if (physios.isEmpty()) {
            outputArea.setText("No physiotherapist found with that expertise.");
            return;
        }

        Physiotherapist physio = physios.get(0);
        if (physio.getTreatments().isEmpty()) {
            outputArea.setText("No available treatments for this physiotherapist.");
            return;
        }

        Treatment treatment = physio.getTreatments().get(0);
        Appointment appointment = new Appointment(patient, physio, treatment.getName(), new Date().toString());
        patient.bookAppointment(appointment);
        outputArea.setText("Appointment booked: " + treatment.getName() + " with " + physio.getFullName());
    }

    private void cancelAppointment() {
        if (clinic.getPatients().isEmpty()) {
            outputArea.setText("No registered patients.");
            return;
        }

        // Dropdown for Patients
        String[] patientNames = clinic.getPatients().stream().map(Patient::getFullName).toArray(String[]::new);
        String selectedPatient = (String) JOptionPane.showInputDialog(null, "Select Patient:", 
            "Cancel Appointment", JOptionPane.QUESTION_MESSAGE, null, patientNames, patientNames[0]);

        if (selectedPatient == null) return; // User canceled selection

        Patient patient = clinic.getPatientByName(selectedPatient);
        if (patient.getAppointments().isEmpty()) {
            outputArea.setText("No appointments to cancel.");
            return;
        }

        patient.cancelAppointment(patient.getAppointments().get(0));
        outputArea.setText("Appointment cancelled for " + patient.getFullName());
    }

    private void generateReport() {
        StringBuilder report = new StringBuilder();
        for (Physiotherapist physio : clinic.getPhysiotherapists()) {
            report.append("Physiotherapist: ").append(physio.getFullName()).append("\n");
            for (Treatment treatment : physio.getTreatments()) {
                report.append("- Treatment: ").append(treatment.getName()).append("\n");
                for (Appointment appointment : treatment.getSchedule()) {
                    report.append("   - ").append(appointment.getStatus())
                          .append(" by ").append(appointment.getPatient().getFullName()).append("\n");
                }
            }
        }
        outputArea.setText(report.toString());
    }

    private void exportReportToPDF() throws IOException {
        String pdfPath = "Clinic_Report.pdf";
        try {
            PdfWriter writer = new PdfWriter(pdfPath);
            PdfDocument pdfDoc = new PdfDocument(writer);
            try (Document document = new Document(pdfDoc)) {
                document.add(new Paragraph("Boost Physio Clinic - Appointment Report\n\n"));
                
                for (Physiotherapist physio : clinic.getPhysiotherapists()) {
                    document.add(new Paragraph("Physiotherapist: " + physio.getFullName()));
                    for (Treatment treatment : physio.getTreatments()) {
                        document.add(new Paragraph("- Treatment: " + treatment.getName()));
                        for (Appointment appointment : treatment.getSchedule()) {
                            document.add(new Paragraph("   - " + appointment.getStatus() +
                                    " by " + appointment.getPatient().getFullName()));
                        }
                    }
                    document.add(new Paragraph("\n"));
                }
            }
            outputArea.setText("PDF Report saved to: " + pdfPath);
        } catch (FileNotFoundException e) {
            outputArea.setText("Error generating PDF: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        new ClinicGUI();
    }
}
