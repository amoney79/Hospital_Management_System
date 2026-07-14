package com.hospital.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;

/**
 * Controller for MedicalRecordDetailView.fxml
 */
public class MedicalRecordDetailController {

    private MainController mainController;

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    @FXML
    private void handleBack() {
        if (mainController != null) {
            mainController.loadView("MedicalRecords");
        }
    }

    /** Pre-fill key fields from the clicked record card */
    public void populateRecord(String pName, String dName, String date, String diag, String presc, String notes) {
        if (patientIdentificationField != null) patientIdentificationField.setText(pName);
        if (attendingDoctorField       != null) attendingDoctorField.setText(dName);
        if (visitDateField             != null) visitDateField.setText(date);
        if (diagnosesField             != null) diagnosesField.setText(diag);
        if (prescriptionsField         != null) prescriptionsField.setText(presc);
        if (progressNotesField         != null) progressNotesField.setText(notes);
    }

    // Visit & Patient Information
    @FXML private TextField patientIdentificationField;
    @FXML private TextField attendingDoctorField;
    @FXML private TextField visitDateField;

    // Inpatient Records
    @FXML private TextArea admissionDetailsField;
    @FXML private TextArea patientDemographicsField;
    @FXML private TextArea vitalSignsLogsField;
    @FXML private TextArea diagnosesField;
    @FXML private TextArea treatmentPlanField;
    @FXML private TextArea progressNotesField;
    @FXML private TextArea labResultsField;
    @FXML private TextArea surgicalRecordsField;
    @FXML private TextArea consultationsField;
    @FXML private TextArea dischargeSummaryField;

    // Outpatient Records
    @FXML private TextArea chiefComplaintField;
    @FXML private TextArea medicalHistoryField;
    @FXML private TextArea physicalExamNotesField;
    @FXML private TextArea diagnosticTestsField;
    @FXML private TextArea prescriptionsField;
    @FXML private TextArea followUpPlanField;
    @FXML private TextArea billingInfoField;

    // Maternity Records
    @FXML private TextArea antenatalVisitsField;
    @FXML private TextArea obstetricHistoryField;
    @FXML private TextArea laborDeliveryNotesField;
    @FXML private TextArea newbornRecordsField;
    @FXML private TextArea postnatalCareField;

    // Common Across All Categories
    @FXML private TextArea consentFormsField;
    @FXML private TextArea immunizationHistoryField;
    @FXML private TextArea allergiesField;
    @FXML private TextArea familyHistoryField;
    @FXML private TextArea socialHistoryField;

    /**
     * Handle Save button click
     */
    @FXML
    private void handleSave() {
        System.out.println("Medical Record Details Saved successfully.");
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
