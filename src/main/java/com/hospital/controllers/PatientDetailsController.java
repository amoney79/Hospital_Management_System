package com.hospital.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import com.hospital.models.Patient;

public class PatientDetailsController {

    // Sections
    @FXML private VBox inpatientSection;
    @FXML private VBox outpatientSection;
    @FXML private VBox maternitySection;

    // Inpatient fields
    @FXML private Label wardLabel;
    @FXML private Label bedLabel;
    @FXML private Label admissionDateLabel;
    @FXML private Label doctorLabel;

    // Outpatient fields
    @FXML private Label visitDateLabel;
    @FXML private Label consultationTypeLabel;
    @FXML private Label outDoctorLabel;
    @FXML private Label nextAppointmentLabel;

    // Maternity fields
    @FXML private Label deliveryDateLabel;
    @FXML private Label obstetricianLabel;
    @FXML private Label pregnancyStageLabel;
    @FXML private TextArea notesArea;

    private Patient patient;

    public void setPatient(Patient patient) {
        this.patient = patient;
        loadPatientData();
    }

    private void loadPatientData() {
        if (patient == null) return;

        // Hide all sections first
        inpatientSection.setVisible(false);
        inpatientSection.setManaged(false);
        outpatientSection.setVisible(false);
        outpatientSection.setManaged(false);
        maternitySection.setVisible(false);
        maternitySection.setManaged(false);

        switch (patient.getType()) {
            case INPATIENT:
                inpatientSection.setVisible(true);
                inpatientSection.setManaged(true);
                wardLabel.setText(patient.getWard());
                bedLabel.setText(patient.getBedNumber());
                admissionDateLabel.setText(patient.getAdmissionDate().toString());
                doctorLabel.setText(patient.getDoctorAssigned());
                break;

            case OUTPATIENT:
                outpatientSection.setVisible(true);
                outpatientSection.setManaged(true);
                visitDateLabel.setText(patient.getVisitDate().toString());
                consultationTypeLabel.setText(patient.getConsultationType());
                outDoctorLabel.setText(patient.getDoctorAssigned());
                nextAppointmentLabel.setText(patient.getNextAppointment().toString());
                break;

            case MATERNITY:
                maternitySection.setVisible(true);
                maternitySection.setManaged(true);
                deliveryDateLabel.setText(patient.getDeliveryDate().toString());
                obstetricianLabel.setText(patient.getObstetrician());
                pregnancyStageLabel.setText(patient.getPregnancyStage());
                notesArea.setText(patient.getNotes());
                break;
        }
    }
}
