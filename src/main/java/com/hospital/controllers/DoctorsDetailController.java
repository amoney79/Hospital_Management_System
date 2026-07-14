package com.hospital.controllers;

// JavaFX imports
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;


import javafx.scene.control.Label;

/**
 * Controller for DoctorsDetailView.fxml
 */
public class DoctorsDetailController {

    private MainController mainController;

    @FXML private Label doctorNameLabel;
    @FXML private Label doctorSpecialtyLabel;
    @FXML private Label doctorDetailsLabel;

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    @FXML
    private void handleBack() {
        if (mainController != null) {
            mainController.loadView("Doctors");
        }
    }

    /** Pre-fill fields from the clicked doctor card */
    public void populateDoctor(String name, String specialty, String dept, String exp, String email, String avail) {
        if (doctorNameLabel != null) doctorNameLabel.setText(name);
        if (doctorSpecialtyLabel != null) doctorSpecialtyLabel.setText(specialty);
        if (doctorDetailsLabel != null) {
            doctorDetailsLabel.setText("Experience: " + exp + " years | Dept: " + dept + " | Email: " + email);
        }
        if (medicalDegreeField != null) medicalDegreeField.setPromptText("Medical Degree / Transcripts — " + name);
        if (contactDetailsField != null) contactDetailsField.setText(email);
        if (availabilityField   != null) availabilityField.setText(avail);
    }

    // Professional & Educational Background
    @FXML
    private TextField medicalDegreeField;
    @FXML
    private TextArea residencyField;
    @FXML
    private TextArea boardCertificationsField;
    @FXML
    private TextArea licensingInfoField;

    // Work Experience
    @FXML
    private TextArea previousHospitalsField;
    @FXML
    private TextArea proceduresExpertiseField;
    @FXML
    private TextArea researchTeachingField;

    // Compliance & Legal Requirements
    @FXML
    private TextArea malpracticeInsuranceField;
    @FXML
    private TextArea backgroundChecksField;
    @FXML
    private TextArea referencesField;
    @FXML
    private TextArea immunizationRecordsField;

    // Personal & Administrative Information
    @FXML
    private TextArea cvField;
    @FXML
    private TextArea idDocsField;
    @FXML
    private TextField contactDetailsField;
    @FXML
    private TextField availabilityField;

    // Optional but Valuable
    @FXML
    private TextArea sopField;
    @FXML
    private TextArea membershipsField;
    @FXML
    private TextArea continuingEducationField;

    /**
     * Handle Save button click
     */
    @FXML
    private void handleSave() {
        // Collect values and log them (replace with DB persistence)
        System.out.println("Doctor Details Saved:");
        System.out.println("Medical Degree: " + medicalDegreeField.getText());
        System.out.println("Residency: " + residencyField.getText());
        System.out.println("Board Certifications: " + boardCertificationsField.getText());
        System.out.println("Licensing Info: " + licensingInfoField.getText());

        System.out.println("Previous Hospitals: " + previousHospitalsField.getText());
        System.out.println("Procedures Expertise: " + proceduresExpertiseField.getText());
        System.out.println("Research/Teaching: " + researchTeachingField.getText());

        System.out.println("Malpractice Insurance: " + malpracticeInsuranceField.getText());
        System.out.println("Background Checks: " + backgroundChecksField.getText());
        System.out.println("References: " + referencesField.getText());
        System.out.println("Immunization Records: " + immunizationRecordsField.getText());

        System.out.println("CV: " + cvField.getText());
        System.out.println("ID Docs: " + idDocsField.getText());
        System.out.println("Contact Details: " + contactDetailsField.getText());
        System.out.println("Availability: " + availabilityField.getText());

        System.out.println("Statement of Purpose: " + sopField.getText());
        System.out.println("Memberships: " + membershipsField.getText());
        System.out.println("Continuing Education: " + continuingEducationField.getText());

        
    }

    /**
     * Handle Cancel button click
     */
    @FXML
    private void handleCancel() {
        // Clear all fields
        medicalDegreeField.clear();
        residencyField.clear();
        boardCertificationsField.clear();
        licensingInfoField.clear();

        previousHospitalsField.clear();
        proceduresExpertiseField.clear();
        researchTeachingField.clear();

        malpracticeInsuranceField.clear();
        backgroundChecksField.clear();
        referencesField.clear();
        immunizationRecordsField.clear();

        cvField.clear();
        idDocsField.clear();
        contactDetailsField.clear();
        availabilityField.clear();

        sopField.clear();
        membershipsField.clear();
        continuingEducationField.clear();

        System.out.println("Doctor Details form cleared.");
    }
}
