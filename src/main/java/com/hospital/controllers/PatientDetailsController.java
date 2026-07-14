package com.hospital.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import com.hospital.models.Patient;
import java.time.LocalDate;

public class PatientDetailsController {

    // Personal Information
    @FXML private TextField nameField;
    @FXML private TextField ageField;
    @FXML private TextField genderField;
    @FXML private TextField phoneField;
    @FXML private TextField emailField;
    @FXML private TextField bloodTypeField;
    @FXML private TextField statusField;
    @FXML private TextField typeField;

    // Inpatient
    @FXML private TitledPane inpatientPane;
    @FXML private TextField wardField;
    @FXML private TextField bedField;
    @FXML private TextField admissionDateField;
    @FXML private TextField doctorField;

    // Outpatient
    @FXML private TitledPane outpatientPane;
    @FXML private TextField visitDateField;
    @FXML private TextField consultationTypeField;
    @FXML private TextField outDoctorField;
    @FXML private TextField nextAppointmentField;

    // Maternity
    @FXML private TitledPane maternityPane;
    @FXML private TextField deliveryDateField;
    @FXML private TextField obstetricianField;
    @FXML private TextField pregnancyStageField;
    @FXML private TextArea notesArea;

    private Patient patient;
    private MainController mainController;

    public void setPatient(Patient patient) {
        this.patient = patient;
        loadPatientData();
    }

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    private void loadPatientData() {
        if (patient == null) return;

        // Populate personal info
        nameField.setText(patient.getName() != null ? patient.getName() : "");
        ageField.setText(String.valueOf(patient.getAge()));
        genderField.setText(patient.getGender() != null ? patient.getGender() : "");
        phoneField.setText(patient.getPhone() != null ? patient.getPhone() : "");
        emailField.setText(patient.getEmail() != null ? patient.getEmail() : "");
        bloodTypeField.setText(patient.getBloodType() != null ? patient.getBloodType() : "");
        statusField.setText(patient.getStatus() != null ? patient.getStatus() : "");
        typeField.setText(patient.getType() != null ? patient.getType().name() : "");

        // Reset expanded states
        inpatientPane.setExpanded(false);
        outpatientPane.setExpanded(false);
        maternityPane.setExpanded(false);

        // Populate and expand appropriate pane based on patient type
        if (patient.getType() != null) {
            switch (patient.getType()) {
                case INPATIENT:
                    inpatientPane.setExpanded(true);
                    wardField.setText(patient.getWard() != null ? patient.getWard() : "");
                    bedField.setText(patient.getBedNumber() != null ? patient.getBedNumber() : "");
                    admissionDateField.setText(patient.getAdmissionDate() != null ? patient.getAdmissionDate().toString() : "");
                    doctorField.setText(patient.getDoctorAssigned() != null ? patient.getDoctorAssigned() : "");
                    break;
                case OUTPATIENT:
                    outpatientPane.setExpanded(true);
                    visitDateField.setText(patient.getVisitDate() != null ? patient.getVisitDate().toString() : "");
                    consultationTypeField.setText(patient.getConsultationType() != null ? patient.getConsultationType() : "");
                    outDoctorField.setText(patient.getDoctorAssigned() != null ? patient.getDoctorAssigned() : "");
                    nextAppointmentField.setText(patient.getNextAppointment() != null ? patient.getNextAppointment().toString() : "");
                    break;
                case MATERNITY:
                    maternityPane.setExpanded(true);
                    deliveryDateField.setText(patient.getDeliveryDate() != null ? patient.getDeliveryDate().toString() : "");
                    obstetricianField.setText(patient.getObstetrician() != null ? patient.getObstetrician() : "");
                    pregnancyStageField.setText(patient.getPregnancyStage() != null ? patient.getPregnancyStage() : "");
                    notesArea.setText(patient.getNotes() != null ? patient.getNotes() : "");
                    break;
            }
        }
    }

    @FXML
    private void handleBack() {
        if (mainController != null) {
            mainController.loadView("Patients");
        }
    }

    @FXML
    private void handleSave() {
        if (patient == null) return;
        
        try {
            patient.setName(nameField.getText());
            patient.setAge(Integer.parseInt(ageField.getText()));
            patient.setGender(genderField.getText());
            patient.setPhone(phoneField.getText());
            patient.setEmail(emailField.getText());
            patient.setBloodType(bloodTypeField.getText());
            patient.setStatus(statusField.getText());
            
            if (patient.getType() != null) {
                switch (patient.getType()) {
                    case INPATIENT:
                        patient.setWard(wardField.getText());
                        patient.setBedNumber(bedField.getText());
                        if (!admissionDateField.getText().isEmpty()) {
                            patient.setAdmissionDate(LocalDate.parse(admissionDateField.getText()));
                        }
                        patient.setDoctorAssigned(doctorField.getText());
                        break;
                    case OUTPATIENT:
                        if (!visitDateField.getText().isEmpty()) {
                            patient.setVisitDate(LocalDate.parse(visitDateField.getText()));
                        }
                        patient.setConsultationType(consultationTypeField.getText());
                        patient.setDoctorAssigned(outDoctorField.getText());
                        if (!nextAppointmentField.getText().isEmpty()) {
                            patient.setNextAppointment(LocalDate.parse(nextAppointmentField.getText()));
                        }
                        break;
                    case MATERNITY:
                        if (!deliveryDateField.getText().isEmpty()) {
                            patient.setDeliveryDate(LocalDate.parse(deliveryDateField.getText()));
                        }
                        patient.setObstetrician(obstetricianField.getText());
                        patient.setPregnancyStage(pregnancyStageField.getText());
                        patient.setNotes(notesArea.getText());
                        break;
                }
            }
            System.out.println("Patient Details Saved successfully.");
            handleBack();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleCancel() {
        loadPatientData();
        handleBack();
    }
}
