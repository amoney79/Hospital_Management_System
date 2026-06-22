package com.hospital.inventory;

import javafx.fxml.FXML;
import javafx.scene.control.*;

public class InventorySupportController {

    @FXML private TextField ticketSubjectField;
    @FXML private ComboBox<String> ticketPriorityCombo;
    @FXML private TextArea ticketDescField;
    @FXML private Button btnSubmitTicket;

    @FXML
    public void initialize() {
        // Populate ticket priority levels
        ticketPriorityCombo.getItems().addAll("Low - General Inquiry", "Medium - Visual Bug/Typo", "High - Functional Interruption", "Critical - System Crash / Blocked");
        ticketPriorityCombo.setValue("Low - General Inquiry");

        // Handle ticket submission
        btnSubmitTicket.setOnAction(event -> {
            String subject = ticketSubjectField.getText();
            String desc = ticketDescField.getText();

            if (subject == null || subject.trim().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Validation Warning");
                alert.setHeaderText("Missing Subject");
                alert.setContentText("Please provide a subject topic for the support ticket.");
                alert.showAndWait();
                return;
            }

            if (desc == null || desc.trim().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Validation Warning");
                alert.setHeaderText("Missing Description");
                alert.setContentText("Please provide details describing the technical issue.");
                alert.showAndWait();
                return;
            }

            // Simulating API ticket submission response
            btnSubmitTicket.setDisable(true);
            btnSubmitTicket.setText("Submitting...");

            javafx.animation.PauseTransition pause = new javafx.animation.PauseTransition(javafx.util.Duration.seconds(1.2));
            pause.setOnFinished(e -> {
                btnSubmitTicket.setDisable(false);
                btnSubmitTicket.setText("Submit Request");

                // Clear fields
                ticketSubjectField.clear();
                ticketDescField.clear();
                ticketPriorityCombo.setValue("Low - General Inquiry");

                Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                successAlert.setTitle("Ticket Submitted");
                successAlert.setHeaderText("Support Ticket Created Successfully");
                successAlert.setContentText("Your request has been logged in the HMS IT system. Support reference ticket ID: #HMS-IT-" + (int)(Math.random() * 90000 + 10000) + ".\nAn IT administrator will review it shortly.");
                successAlert.showAndWait();
            });
            pause.play();
        });
    }
}
