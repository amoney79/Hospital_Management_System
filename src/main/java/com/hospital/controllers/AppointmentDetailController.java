package com.hospital.controllers;

// JavaFX imports
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.scene.control.DatePicker;


/**
 * Controller for AppointmentDetailView.fxml
 */
public class AppointmentDetailController {

    private MainController mainController;

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    @FXML
    private void handleBack() {
        if (mainController != null) {
            mainController.loadView("Appointments");
        }
    }

    /** Pre-fill fields from the clicked appointment card */
    public void populateAppointment(String patient, String doctor, String date, String time, String type, String status) {
        if (patientNameField        != null) patientNameField.setText(patient);
        if (doctorNameField         != null) doctorNameField.setText(doctor);
        if (appointmentStartTimeField != null) appointmentStartTimeField.setText(time);
        if (appointmentTypeField    != null) appointmentTypeField.setText(type);
        if (statusField             != null) statusField.setText(status);
        if (appointmentDatePicker   != null && date != null) {
            try {
                appointmentDatePicker.setValue(java.time.LocalDate.parse(date));
            } catch (Exception e) {
                // Keep it empty or ignore if date format doesn't match
            }
        }
    }

    // Patient Information
    @FXML private TextField patientNameField;
    @FXML private TextField patientIdField;
    @FXML private TextField dobField;
    @FXML private TextArea patientContactField;
    @FXML private TextArea insuranceInfoField;

    // Appointment Basics
    @FXML private TextField appointmentIdField;
    @FXML private DatePicker appointmentDatePicker;
    @FXML private TextField appointmentStartTimeField;
    @FXML private TextField appointmentEndTimeField;
    @FXML private TextField appointmentTypeField;
    @FXML private TextField statusField;

    // Provider Information
    @FXML private TextField doctorNameField;
    @FXML private TextField doctorIdField;
    @FXML private TextField specializationField;
    @FXML private TextField departmentField;

    // Location Details
    @FXML private TextField facilityNameField;
    @FXML private TextField roomNumberField;
    @FXML private TextArea locationAddressField;
    @FXML private TextField virtualMeetingLinkField;

    // Administrative & Support
    @FXML private TextArea reasonForVisitField;
    @FXML private TextField referralSourceField;
    @FXML private TextArea notesField;
    @FXML private TextArea attachmentsField;

    // Notifications & Tracking
    @FXML private TextField reminderSentField;
    @FXML private TextField checkInTimeField;
    @FXML private TextField followUpRequiredField;

    /**
     * Handle Save button click
     */
    @FXML
    private void handleSave() {
        System.out.println("Appointment Details Saved:");
        System.out.println("Patient Name: " + patientNameField.getText());
        System.out.println("Patient ID: " + patientIdField.getText());
        System.out.println("DOB: " + dobField.getText());
        System.out.println("Contact: " + patientContactField.getText());
        System.out.println("Insurance: " + insuranceInfoField.getText());

        System.out.println("Appointment ID: " + appointmentIdField.getText());
        System.out.println("Date: " + appointmentDatePicker.getValue());
        System.out.println("Start Time: " + appointmentStartTimeField.getText());
        System.out.println("End Time: " + appointmentEndTimeField.getText());
        System.out.println("Type: " + appointmentTypeField.getText());
        System.out.println("Status: " + statusField.getText());

        System.out.println("Doctor Name: " + doctorNameField.getText());
        System.out.println("Doctor ID: " + doctorIdField.getText());
        System.out.println("Specialization: " + specializationField.getText());
        System.out.println("Department: " + departmentField.getText());

        System.out.println("Facility: " + facilityNameField.getText());
        System.out.println("Room: " + roomNumberField.getText());
        System.out.println("Address: " + locationAddressField.getText());
        System.out.println("Virtual Link: " + virtualMeetingLinkField.getText());

        System.out.println("Reason for Visit: " + reasonForVisitField.getText());
        System.out.println("Referral Source: " + referralSourceField.getText());
        System.out.println("Notes: " + notesField.getText());
        System.out.println("Attachments: " + attachmentsField.getText());

        System.out.println("Reminder Sent: " + reminderSentField.getText());
        System.out.println("Check-in Time: " + checkInTimeField.getText());
        System.out.println("Follow-up Required: " + followUpRequiredField.getText());

    }

    /**
     * Handle Cancel button click
     */
    @FXML
    private void handleCancel() {
        // Clear all fields
        patientNameField.clear();
        patientIdField.clear();
        dobField.clear();
        patientContactField.clear();
        insuranceInfoField.clear();

        appointmentIdField.clear();
        appointmentDatePicker.setValue(null);
        appointmentStartTimeField.clear();
        appointmentEndTimeField.clear();
        appointmentTypeField.clear();
        statusField.clear();

        doctorNameField.clear();
        doctorIdField.clear();
        specializationField.clear();
        departmentField.clear();

        facilityNameField.clear();
        roomNumberField.clear();
        locationAddressField.clear();
        virtualMeetingLinkField.clear();

        reasonForVisitField.clear();
        referralSourceField.clear();
        notesField.clear();
        attachmentsField.clear();

        reminderSentField.clear();
        checkInTimeField.clear();
        followUpRequiredField.clear();

        System.out.println("Appointment Details form cleared.");
    }
}
