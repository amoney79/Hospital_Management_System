package com.hms.controllers;

// JavaFX imports
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TitledPane;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;

/**
 * Controller for GeneralStaffDetailView.fxml
 */
public class GeneralStaffDetailController {

    // Personal Information
    @FXML private TextField nameField;
    @FXML private TextField idNumberField;
    @FXML private TextField contactField;
    @FXML private TextField addressField;

    // Employment Information
    @FXML private TextField roleField;
    @FXML private TextField departmentField;
    @FXML private TextField employmentDateField;
    @FXML private TextField contractTypeField;
    @FXML private TextField shiftDetailsField;

    // Compliance & Legal
    @FXML private TextArea backgroundChecksField;
    @FXML private TextArea referencesField;
    @FXML private TextArea certificationsField;
    @FXML private TextArea immunizationRecordsField;

    // Optional Information
    @FXML private TextArea notesField;
    @FXML private TextArea achievementsField;
    @FXML private TextArea membershipsField;

    /**
     * Handle Save button click
     */
    @FXML
    private void handleSave() {
        System.out.println("General Staff Details Saved:");

        // Personal
        System.out.println("Name: " + nameField.getText());
        System.out.println("ID Number: " + idNumberField.getText());
        System.out.println("Contact: " + contactField.getText());
        System.out.println("Address: " + addressField.getText());

        // Employment
        System.out.println("Role: " + roleField.getText());
        System.out.println("Department: " + departmentField.getText());
        System.out.println("Employment Date: " + employmentDateField.getText());
        System.out.println("Contract Type: " + contractTypeField.getText());
        System.out.println("Shift Details: " + shiftDetailsField.getText());

        // Compliance
        System.out.println("Background Checks: " + backgroundChecksField.getText());
        System.out.println("References: " + referencesField.getText());
        System.out.println("Certifications: " + certificationsField.getText());
        System.out.println("Immunization Records: " + immunizationRecordsField.getText());

        // Optional
        System.out.println("Notes: " + notesField.getText());
        System.out.println("Achievements: " + achievementsField.getText());
        System.out.println("Memberships: " + membershipsField.getText());

        // TODO: Persist these details into your database or backend service
    }

    /**
     * Handle Cancel button click
     */
    @FXML
    private void handleCancel() {
        // Clear all fields

        // Personal
        nameField.clear();
        idNumberField.clear();
        contactField.clear();
        addressField.clear();

        // Employment
        roleField.clear();
        departmentField.clear();
        employmentDateField.clear();
        contractTypeField.clear();
        shiftDetailsField.clear();

        // Compliance
        backgroundChecksField.clear();
        referencesField.clear();
        certificationsField.clear();
        immunizationRecordsField.clear();

        // Optional
        notesField.clear();
        achievementsField.clear();
        membershipsField.clear();

        System.out.println("General Staff Details form cleared.");
    }
}
