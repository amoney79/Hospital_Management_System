package com.hospital.controllers;

// JavaFX imports
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;

import javafx.scene.control.Label;

/**
 * Controller for MedicalRecordDetailView.fxml
 */
public class MedicalRecordDetailController {

    private MainController mainController;

    @FXML private Label patientNameLabel;
    @FXML private Label attendingDoctorLabel;

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
        if (patientNameLabel != null) patientNameLabel.setText(pName);
        if (attendingDoctorLabel != null) attendingDoctorLabel.setText("Attending Doctor: " + dName + " | Date: " + date);
        if (patientIdentificationField != null) patientIdentificationField.setText(pName);
        if (visitDateField             != null) visitDateField.setText(date);
        if (diagnosesField             != null) diagnosesField.setText(diag);
        if (prescriptionsField         != null) prescriptionsField.setText(presc);
        if (progressNotesField         != null) progressNotesField.setText(notes);
    }

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
    @FXML private TextField visitDateField;
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
    @FXML private TextField patientIdentificationField;
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
        System.out.println("Medical Record Details Saved:");

        // Inpatient
        System.out.println("Admission: " + admissionDetailsField.getText());
        System.out.println("Demographics: " + patientDemographicsField.getText());
        System.out.println("Vitals: " + vitalSignsLogsField.getText());
        System.out.println("Diagnoses: " + diagnosesField.getText());
        System.out.println("Treatment Plan: " + treatmentPlanField.getText());
        System.out.println("Progress Notes: " + progressNotesField.getText());
        System.out.println("Lab Results: " + labResultsField.getText());
        System.out.println("Surgical Records: " + surgicalRecordsField.getText());
        System.out.println("Consultations: " + consultationsField.getText());
        System.out.println("Discharge Summary: " + dischargeSummaryField.getText());

        // Outpatient
        System.out.println("Visit Date: " + visitDateField.getText());
        System.out.println("Chief Complaint: " + chiefComplaintField.getText());
        System.out.println("Medical History: " + medicalHistoryField.getText());
        System.out.println("Physical Exam: " + physicalExamNotesField.getText());
        System.out.println("Diagnostic Tests: " + diagnosticTestsField.getText());
        System.out.println("Prescriptions: " + prescriptionsField.getText());
        System.out.println("Follow-up Plan: " + followUpPlanField.getText());
        System.out.println("Billing Info: " + billingInfoField.getText());

        // Maternity
        System.out.println("Antenatal Visits: " + antenatalVisitsField.getText());
        System.out.println("Obstetric History: " + obstetricHistoryField.getText());
        System.out.println("Labor & Delivery: " + laborDeliveryNotesField.getText());
        System.out.println("Newborn Records: " + newbornRecordsField.getText());
        System.out.println("Postnatal Care: " + postnatalCareField.getText());

        // Common
        System.out.println("Patient ID: " + patientIdentificationField.getText());
        System.out.println("Consent Forms: " + consentFormsField.getText());
        System.out.println("Immunization History: " + immunizationHistoryField.getText());
        System.out.println("Allergies: " + allergiesField.getText());
        System.out.println("Family History: " + familyHistoryField.getText());
        System.out.println("Social History: " + socialHistoryField.getText());

    }

    /**
     * Handle Cancel button click
     */
    @FXML
    private void handleCancel() {
        // Clear all fields

        // Inpatient
        admissionDetailsField.clear();
        patientDemographicsField.clear();
        vitalSignsLogsField.clear();
        diagnosesField.clear();
        treatmentPlanField.clear();
        progressNotesField.clear();
        labResultsField.clear();
        surgicalRecordsField.clear();
        consultationsField.clear();
        dischargeSummaryField.clear();

        // Outpatient
        visitDateField.clear();
        chiefComplaintField.clear();
        medicalHistoryField.clear();
        physicalExamNotesField.clear();
        diagnosticTestsField.clear();
        prescriptionsField.clear();
        followUpPlanField.clear();
        billingInfoField.clear();

        // Maternity
        antenatalVisitsField.clear();
        obstetricHistoryField.clear();
        laborDeliveryNotesField.clear();
        newbornRecordsField.clear();
        postnatalCareField.clear();

        // Common
        patientIdentificationField.clear();
        consentFormsField.clear();
        immunizationHistoryField.clear();
        allergiesField.clear();
        familyHistoryField.clear();
        socialHistoryField.clear();

        System.out.println("Medical Record Details form cleared.");
    }
}
