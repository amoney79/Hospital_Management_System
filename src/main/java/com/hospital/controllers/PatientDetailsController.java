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
            wardLabel.setText(patient.getWard() != null ? patient.getWard() : "N/A");
            bedLabel.setText(patient.getBedNumber() != null ? patient.getBedNumber() : "N/A");
            admissionDateLabel.setText(patient.getAdmissionDate() != null ? patient.getAdmissionDate().toString() : "N/A");
            doctorLabel.setText(patient.getDoctorAssigned() != null ? patient.getDoctorAssigned() : "N/A");
            break;

        case OUTPATIENT:
            outpatientSection.setVisible(true);
            outpatientSection.setManaged(true);
            visitDateLabel.setText(patient.getVisitDate() != null ? patient.getVisitDate().toString() : "N/A");
            consultationTypeLabel.setText(patient.getConsultationType() != null ? patient.getConsultationType() : "N/A");
            outDoctorLabel.setText(patient.getDoctorAssigned() != null ? patient.getDoctorAssigned() : "N/A");
            nextAppointmentLabel.setText(patient.getNextAppointment() != null ? patient.getNextAppointment().toString() : "N/A");
            break;

        case MATERNITY:
            maternitySection.setVisible(true);
            maternitySection.setManaged(true);
            deliveryDateLabel.setText(patient.getDeliveryDate() != null ? patient.getDeliveryDate().toString() : "N/A");
            obstetricianLabel.setText(patient.getObstetrician() != null ? patient.getObstetrician() : "N/A");
            pregnancyStageLabel.setText(patient.getPregnancyStage() != null ? patient.getPregnancyStage() : "N/A");
            notesArea.setText(patient.getNotes() != null ? patient.getNotes() : "N/A");
            break;
    }
    }

    public void setMainController(MainController mainController) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setMainController'");
    }
}
