package com.hospital.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;

public class Settings {
    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;
    @FXML private Button saveButton;

    @FXML
    public void initialize() {
        saveButton.setOnAction(e -> handleSave());
    }

    private void handleSave() {
        String username = usernameField.getText();
        String password = passwordField.getText();
        System.out.println("Username: " + username);
        System.out.println("Password: " + password);
    }
    
}
