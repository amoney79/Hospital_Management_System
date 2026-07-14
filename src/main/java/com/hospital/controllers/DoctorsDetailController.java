package com.hospital.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;

/**
 * Controller for DoctorsDetailView.fxml
 */
public class DoctorsDetailController {

    private MainController mainController;

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
        if (doctorNameField       != null) doctorNameField.setText(name);
        if (doctorSpecialtyField  != null) doctorSpecialtyField.setText(specialty);
        if (doctorDepartmentField != null) doctorDepartmentField.setText(dept);
        if (doctorExperienceField != null) doctorExperienceField.setText(exp);
        if (contactDetailsField   != null) contactDetailsField.setText(email);
        if (availabilityField     != null) availabilityField.setText(avail);

        if (medicalDegreeField != null) medicalDegreeField.setPromptText("Medical Degree / Transcripts — " + name);
    }

    // Personal & Professional Information
    @FXML private TextField doctorNameField;
    @FXML private TextField doctorSpecialtyField;
    @FXML private TextField doctorDepartmentField;
    @FXML private TextField doctorExperienceField;
    @FXML private TextField contactDetailsField;
    @FXML private TextField availabilityField;

    // Professional & Educational Background
    @FXML private TextField medicalDegreeField;
    @FXML private TextArea residencyField;
    @FXML private TextArea boardCertificationsField;
    @FXML private TextArea licensingInfoField;

    // Work Experience
    @FXML private TextArea previousHospitalsField;
    @FXML private TextArea proceduresExpertiseField;
    @FXML private TextArea researchTeachingField;

    // Compliance & Legal Requirements
    @FXML private TextArea malpracticeInsuranceField;
    @FXML private TextArea backgroundChecksField;
    @FXML private TextArea referencesField;
    @FXML private TextArea immunizationRecordsField;

    // Personal & Administrative Information
    @FXML private TextArea cvField;
    @FXML private TextArea idDocsField;

    // Optional but Valuable
    @FXML private TextArea sopField;
    @FXML private TextArea membershipsField;
    @FXML private TextArea continuingEducationField;

    /**
     * Handle Save button click
     */
    @FXML
    private void handleSave() {
        System.out.println("Doctor Details Saved successfully.");
        handleBack();
    }

    /**
     * Handle Cancel button click
     */
    @FXML
    private void handleCancel() {
        handleBack();
    }
}
