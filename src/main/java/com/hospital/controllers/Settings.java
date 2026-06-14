package com.hospital.controllers;

import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import javafx.scene.control.*;

public class Settings {
    @FXML
    private VBox generalSettingsSection;
    @FXML
    private VBox adminSettingsSection;

    // General Settings
    @FXML
    private TextField usernameField;
    @FXML
    private TextField displayNameField;
    @FXML
    private TextField contactField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private CheckBox twoFactorCheckBox;
    @FXML
    private ComboBox<String> languageComboBox;
    @FXML
    private CheckBox emailNotifCheckBox;
    @FXML
    private CheckBox smsNotifCheckBox;

    // Admin Settings
    @FXML
    private ComboBox<String> roleComboBox;
    @FXML
    private CheckBox patientRecordsCheckBox;
    @FXML
    private CheckBox billingCheckBox;
    @FXML
    private CheckBox pharmacyCheckBox;
    @FXML
    private CheckBox hrCheckBox;

    @FXML
    private TextField departmentField;
    @FXML
    private TextField schedulingRulesField;
    @FXML
    private TextField retentionPolicyField;

    // Security & Compliance
    @FXML
    private TextArea auditLogsArea;
    @FXML
    private CheckBox accessMonitoringCheckBox;
    @FXML
    private CheckBox hipaaComplianceCheckBox;
    @FXML
    private CheckBox gdprComplianceCheckBox;

    @FXML
    private Button saveButton;

    private String currentUserRole = "ADMIN"; // Dummy role for testing

    @FXML
    public void initialize() {
        // Role-based visibility
        adminSettingsSection.setVisible("ADMIN".equals(currentUserRole));
        adminSettingsSection.setManaged("ADMIN".equals(currentUserRole));

        // Dummy language options
        languageComboBox.getItems().addAll("English", "Swahili", "French");

        // Dummy roles
        roleComboBox.getItems().addAll("Doctor", "Nurse", "Receptionist", "Pharmacist", "Admin");

        // Dummy audit logs
        auditLogsArea
                .setText("Audit Log:\n- User 'Doctor1' accessed Patient Records\n- User 'Nurse2' updated Billing info");

        saveButton.setOnAction(e -> handleSave());
    }

    private void handleSave() {
        System.out.println("Username: " + usernameField.getText());
        System.out.println("Display Name: " + displayNameField.getText());
        System.out.println("Contact: " + contactField.getText());
        System.out.println("Password: " + passwordField.getText());
        System.out.println("Two-Factor Enabled: " + twoFactorCheckBox.isSelected());
        System.out.println("Language: " + languageComboBox.getValue());
        System.out.println("Email Notifications: " + emailNotifCheckBox.isSelected());
        System.out.println("SMS Notifications: " + smsNotifCheckBox.isSelected());

        if ("ADMIN".equals(currentUserRole)) {
            System.out.println("Role: " + roleComboBox.getValue());
            System.out.println("Modules Access: PatientRecords=" + patientRecordsCheckBox.isSelected() + ", Billing="
                    + billingCheckBox.isSelected() + ", Pharmacy=" + pharmacyCheckBox.isSelected() + ", HR="
                    + hrCheckBox.isSelected());
            System.out.println("Department: " + departmentField.getText());
            System.out.println("Scheduling Rules: " + schedulingRulesField.getText());
            System.out.println("Retention Policy: " + retentionPolicyField.getText());

            System.out.println("Access Monitoring: " + accessMonitoringCheckBox.isSelected());
            System.out.println("HIPAA Compliance: " + hipaaComplianceCheckBox.isSelected());
            System.out.println("GDPR Compliance: " + gdprComplianceCheckBox.isSelected());
            System.out.println("Audit Logs:\n" + auditLogsArea.getText());
        }
    }
}
