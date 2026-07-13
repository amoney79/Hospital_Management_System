package com.hospital.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.geometry.Pos;
import java.io.IOException;

public class AppointmentsController {

    @FXML private VBox appointmentsContainer;

    private MainController mainController;

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    @FXML
    public void initialize() {
        addAppCard("Sarah Johnson", "Dr. Wilson", "2026-04-02", "09:00 AM", "Check-up",     "scheduled",   "badge-scheduled");
        addAppCard("Michael Chen",  "Dr. Davis",  "2026-04-02", "10:30 AM", "Consultation", "in-progress", "badge-in-progress");
        addAppCard("Emily Brown",   "Dr. Smith",  "2026-04-02", "02:00 PM", "Treatment",    "completed",   "badge-completed");
    }

    private void addAppCard(String patient, String doctor, String date, String time, String type, String status, String badgeClass) {
        VBox card = new VBox();
        card.getStyleClass().add("card");

        HBox content = new HBox(16);
        content.getStyleClass().add("card-content");
        content.setAlignment(Pos.CENTER_LEFT);

        VBox leftInfo = new VBox(8);
        leftInfo.getChildren().add(new Label(patient + " | " + doctor));
        ((Label) leftInfo.getChildren().get(0)).setStyle("-fx-font-size: 16; -fx-font-weight: bold;");

        HBox details = new HBox(16);
        details.getChildren().addAll(
            new Label("Date: " + date),
            new Label("Time: " + time),
            new Label("Type: " + type)
        );
        details.setStyle("-fx-text-fill: #4b5563; -fx-font-size: 14;");
        leftInfo.getChildren().add(details);

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        Label statusL = new Label(status.toUpperCase());
        statusL.getStyleClass().addAll("badge", badgeClass);

        content.getChildren().addAll(leftInfo, spacer, statusL);
        card.getChildren().add(content);

        // Wire card click → open AppointmentDetailView in main layout
        card.setOnMouseClicked(event -> openAppointmentDetail(patient, doctor, date, time, type, status));
        card.setStyle("-fx-cursor: hand;");

        appointmentsContainer.getChildren().add(card);
    }

    private void openAppointmentDetail(String patient, String doctor, String date, String time, String type, String status) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/hospital/fxml/AppointmentDetailView.fxml"));
            Parent view = loader.load();

            AppointmentDetailController controller = loader.getController();
            controller.setMainController(mainController);
            controller.populateAppointment(patient, doctor, date, time, type, status);

            if (mainController != null) {
                mainController.setContent(view);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void handleAddAppointment() {
        javafx.scene.control.Dialog<javafx.scene.control.ButtonType> dialog = new javafx.scene.control.Dialog<>();
        dialog.setTitle("Schedule Appointment");
        dialog.setHeaderText("Enter appointment details:");

        javafx.scene.control.DialogPane dialogPane = dialog.getDialogPane();
        dialogPane.getButtonTypes().addAll(javafx.scene.control.ButtonType.OK, javafx.scene.control.ButtonType.CANCEL);

        javafx.scene.layout.GridPane grid = new javafx.scene.layout.GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new javafx.geometry.Insets(20, 150, 10, 10));

        javafx.scene.control.TextField patientInput = new javafx.scene.control.TextField();
        javafx.scene.control.TextField doctorInput  = new javafx.scene.control.TextField();
        javafx.scene.control.TextField dateInput    = new javafx.scene.control.TextField();
        javafx.scene.control.TextField timeInput    = new javafx.scene.control.TextField();

        grid.add(new Label("Patient:"), 0, 0); grid.add(patientInput, 1, 0);
        grid.add(new Label("Doctor:"),  0, 1); grid.add(doctorInput,  1, 1);
        grid.add(new Label("Date:"),    0, 2); grid.add(dateInput,    1, 2);
        grid.add(new Label("Time:"),    0, 3); grid.add(timeInput,    1, 3);

        dialogPane.setContent(grid);
        dialogPane.lookupButton(javafx.scene.control.ButtonType.OK).setStyle(
            "-fx-background-color: #111827; -fx-text-fill: white; -fx-cursor: hand; -fx-padding: 8 16; -fx-background-radius: 6;");

        dialog.showAndWait().ifPresent(response -> {
            if (response == javafx.scene.control.ButtonType.OK) {
                addAppCard(
                    patientInput.getText().isEmpty() ? "Unknown Patient" : patientInput.getText(),
                    doctorInput.getText().isEmpty()  ? "Unknown Doctor"  : doctorInput.getText(),
                    dateInput.getText().isEmpty()    ? "YYYY-MM-DD"      : dateInput.getText(),
                    timeInput.getText().isEmpty()    ? "00:00 AM"        : timeInput.getText(),
                    "Check-up", "SCHEDULED", "badge-scheduled"
                );
            }
        });
    }
}
