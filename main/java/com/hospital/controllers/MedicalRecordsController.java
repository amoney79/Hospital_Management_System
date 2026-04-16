package com.hospital.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.geometry.Pos;

public class MedicalRecordsController {

    @FXML private VBox recordsContainer;

    @FXML
    public void initialize() {
        addRecord("Sarah Johnson", "Dr. Wilson", "2026-03-15", "Routine Checkup. Patient is healthy.", "Vitamin D Supplements", "Annual Checkup");
        addRecord("Michael Chen", "Dr. Davis", "2026-03-10", "Mild hypertension", "Lisinopril 10mg", "Blood pressure monitoring");
    }

    private void addRecord(String pName, String dName, String date, String diag, String presc, String notes) {
        VBox card = new VBox();
        card.getStyleClass().add("card");
        
        VBox content = new VBox(12);
        content.getStyleClass().add("card-content");

        HBox header = new HBox(16);
        header.setAlignment(Pos.CENTER_LEFT);
        VBox left = new VBox(2);
        Label title = new Label(pName);
        title.setStyle("-fx-font-size: 18; -fx-font-weight: bold;");
        Label sub = new Label("Attending: " + dName + " | Date: " + date);
        sub.setStyle("-fx-text-fill: #4b5563; -fx-font-size: 14;");
        left.getChildren().addAll(title, sub);
        header.getChildren().add(left);

        HBox details = new HBox(20);
        details.getChildren().addAll(
            createSection("Diagnosis", diag),
            createSection("Prescription", presc),
            createSection("Notes", notes)
        );

        content.getChildren().addAll(header, details);
        card.getChildren().add(content);
        recordsContainer.getChildren().add(card);
    }

    private VBox createSection(String title, String data) {
        VBox section = new VBox(4);
        Label t = new Label(title);
        t.setStyle("-fx-font-weight: bold; -fx-text-fill: #374151;");
        Label d = new Label(data);
        d.setWrapText(true);
        d.setMaxWidth(250);
        d.setStyle("-fx-background-color: #f3f4f6; -fx-padding: 8; -fx-background-radius: 6;");
        section.getChildren().addAll(t, d);
        return section;
    }
}
