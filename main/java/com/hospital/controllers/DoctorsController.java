package com.hospital.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Region;
import javafx.geometry.Pos;

public class DoctorsController {

    @FXML private FlowPane doctorsGrid;

    @FXML
    public void initialize() {
        addDocCard("Dr. Sarah Wilson", "Cardiology", "Cardiology Dept", "15", "sarah.wilson@hospital.com", "Mon-Fri, 9AM-5PM");
        addDocCard("Dr. Michael Davis", "Pediatrics", "Pediatrics Dept", "10", "m.davis@hospital.com", "Mon-Thu, 8AM-4PM");
        addDocCard("Dr. Emily Smith", "Neurology", "Neurology Dept", "12", "e.smith@hospital.com", "Tue-Sat, 10AM-6PM");
    }

    private void addDocCard(String name, String specialty, String dept, String exp, String email, String avail) {
        VBox card = new VBox();
        card.getStyleClass().add("card");
        card.setPrefWidth(300);
        
        VBox content = new VBox(12);
        content.getStyleClass().add("card-content");

        Label nameL = new Label(name);
        nameL.setStyle("-fx-font-size: 16; -fx-font-weight: bold;");
        Label specL = new Label(specialty);
        specL.setStyle("-fx-text-fill: #4b5563; -fx-font-size: 14;");
        
        Label available = new Label("AVAILABLE");
        available.getStyleClass().addAll("badge", "badge-completed");

        VBox details = new VBox(6);
        details.getChildren().addAll(
            new Label("Experience: " + exp + " years"),
            new Label("Department: " + dept),
            new Label("Email: " + email)
        );
        details.setStyle("-fx-text-fill: #374151; -fx-font-size: 13;");

        content.getChildren().addAll(nameL, specL, available, details);
        card.getChildren().add(content);
        doctorsGrid.getChildren().add(card);
    }

    @FXML
    public void handleAddDoctor() {
        javafx.scene.control.Dialog<javafx.scene.control.ButtonType> dialog = new javafx.scene.control.Dialog<>();
        dialog.setTitle("Add New Doctor");
        dialog.setHeaderText("Enter doctor details:");

        javafx.scene.control.DialogPane dialogPane = dialog.getDialogPane();
        dialogPane.getButtonTypes().addAll(javafx.scene.control.ButtonType.OK, javafx.scene.control.ButtonType.CANCEL);

        javafx.scene.layout.GridPane grid = new javafx.scene.layout.GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new javafx.geometry.Insets(20, 150, 10, 10));

        javafx.scene.control.TextField nameInput = new javafx.scene.control.TextField();
        nameInput.setPromptText("Name (e.g., Dr. Smith)");
        javafx.scene.control.TextField specInput = new javafx.scene.control.TextField();
        specInput.setPromptText("Specialty (e.g., Cardiology)");

        grid.add(new Label("Name:"), 0, 0); grid.add(nameInput, 1, 0);
        grid.add(new Label("Specialty:"), 0, 1); grid.add(specInput, 1, 1);

        dialogPane.setContent(grid);
        dialogPane.lookupButton(javafx.scene.control.ButtonType.OK).setStyle("-fx-background-color: #111827; -fx-text-fill: white; -fx-cursor: hand; -fx-padding: 8 16; -fx-background-radius: 6;");

        dialog.showAndWait().ifPresent(response -> {
            if (response == javafx.scene.control.ButtonType.OK) {
                addDocCard(
                    nameInput.getText().isEmpty() ? "New Doctor" : nameInput.getText(),
                    specInput.getText().isEmpty() ? "General" : specInput.getText(),
                    "TBD Dept", "0", "N/A", "Mon-Fri"
                );
            }
        });
    }
}
