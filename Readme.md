# Boost Physio Clinic Management System

## Introduction
Boost Physio Clinic Management System is designed to streamline patient and physiotherapist interactions, ensuring efficient appointment booking, treatment scheduling, and report generation. This system provides an intuitive **Graphical User Interface (GUI)** for users to manage patient records, book and cancel appointments, and generate reports in both on-screen and **PDF formats**.

### Key Features:
- **Patient Management** – Add, retrieve, and manage patient records.
- **Physiotherapist Management** – Maintain physiotherapist records and filter by specialization.
- **Appointment Booking & Cancellation** – Patients can book, view, and cancel appointments.
- **Treatment Scheduling** – Assign treatments to physiotherapists.
- **Report Generation** – Generate detailed reports and export them as PDFs.

## System Architecture
The system follows **Object-Oriented Programming (OOP)** principles and is structured using a **UML class diagram**. The main components include:
- **Person Class** – Parent class for both **Patients** and **Physiotherapists**.
- **Clinic System** – Maintains records for both patients and physiotherapists.
- **Physiotherapists** – Hold specializations and provide treatments.
- **Appointments** – Connect patients with physiotherapists for scheduled treatments.

## Technologies Used
- **Java Swing** – For building the graphical user interface.
- **Maven** – For dependency management.
- **JUnit** – For automated testing.
- **PDF Export** – Generates reports for record-keeping.

## Installation & Setup
1. **Clone the Repository**  
   ```bash
   git clone https://github.com/your-repo/BoostPhysioClinic.git
   cd BoostPhysioClinic
