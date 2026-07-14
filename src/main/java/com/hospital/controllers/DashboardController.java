package com.hospital.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.geometry.Pos;

public class DashboardController {

    @FXML private Label totalPatientsLabel;
    @FXML private Label availableDoctorsLabel;
    @FXML private Label todayAppointmentsLabel;
    @FXML private Label totalRevenueLabel;

    @FXML private VBox recentAppointmentsContainer;
    @FXML private VBox recentTransactionsContainer;

    @FXML
    public void initialize() {
        // Mock data initialization
        totalPatientsLabel.setText("4"); // Based on mockPatients.length
        availableDoctorsLabel.setText("4"); // Based on available doctors
        todayAppointmentsLabel.setText("2"); // Based on mock appointments
        totalRevenueLabel.setText("$1100"); // Based on mock transactions

        loadRecentAppointments();
        loadRecentTransactions();
    }

    private MainController mainController;

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    private void loadRecentAppointments() {
        // Mock list of up to 3 appointments
        addAppointmentItem("Sarah Johnson", "Dr. Wilson", "09:00 AM", "scheduled", "badge-scheduled");
        addAppointmentItem("Michael Chen", "Dr. Davis", "10:30 AM", "in-progress", "badge-in-progress");
        addAppointmentItem("Emily Brown", "Dr. Smith", "02:00 PM", "completed", "badge-completed");
    }

    private void addAppointmentItem(String patient, String doctor, String time, String status, String badgeClass) {
        HBox item = new HBox();
        item.getStyleClass().add("list-item-card");
        item.setAlignment(Pos.CENTER_LEFT);

        VBox leftInfo = new VBox(4);
        Label patientL = new Label(patient);
        patientL.getStyleClass().add("list-item-title");
        Label doctorL = new Label(doctor);
        doctorL.getStyleClass().add("list-item-subtitle");
        leftInfo.getChildren().addAll(patientL, doctorL);
        
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        VBox rightInfo = new VBox(4);
        rightInfo.setAlignment(Pos.CENTER_RIGHT);
        Label timeL = new Label(time);
        timeL.getStyleClass().add("list-item-right-title");
        Label statusL = new Label(status);
        statusL.getStyleClass().addAll("badge", badgeClass);
        rightInfo.getChildren().addAll(timeL, statusL);

        item.getChildren().addAll(leftInfo, spacer, rightInfo);

        // Wire click handler to open details
        item.setOnMouseClicked(event -> openAppointmentDetail(patient, doctor, "2026-04-02", time, "Consultation", status));
        item.setStyle("-fx-cursor: hand;");

        recentAppointmentsContainer.getChildren().add(item);
    }

    private void openAppointmentDetail(String patient, String doctor, String date, String time, String type, String status) {
        try {
            javafx.fxml.FXMLLoader loader = new javafx.fxml.FXMLLoader(getClass().getResource("/com/hospital/fxml/AppointmentDetailView.fxml"));
            javafx.scene.Parent view = loader.load();

            AppointmentDetailController controller = loader.getController();
            controller.setMainController(mainController);
            controller.populateAppointment(patient, doctor, date, time, type, status);

            if (mainController != null) {
                mainController.setContent(view);
            }
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }

    private void loadRecentTransactions() {
        // Mock list of up to 4 transactions
        addTransactionItem("Sarah Johnson", "General Consultation", "$150.00", "paid", "badge-paid");
        addTransactionItem("Michael Chen", "Dental Cleaning", "$200.00", "pending", "badge-pending");
        addTransactionItem("Emily Brown", "Blood Test", "$85.00", "partial", "badge-partial");
        addTransactionItem("James Wilson", "X-Ray", "$300.00", "paid", "badge-paid");
    }

    private void addTransactionItem(String patient, String service, String amount, String status, String badgeClass) {
        HBox item = new HBox();
        item.getStyleClass().add("list-item-card");
        item.setAlignment(Pos.CENTER_LEFT);

        VBox leftInfo = new VBox(4);
        Label patientL = new Label(patient);
        patientL.getStyleClass().add("list-item-title");
        Label serviceL = new Label(service);
        serviceL.getStyleClass().add("list-item-subtitle");
        leftInfo.getChildren().addAll(patientL, serviceL);
        
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        VBox rightInfo = new VBox(4);
        rightInfo.setAlignment(Pos.CENTER_RIGHT);
        Label amountL = new Label(amount);
        amountL.getStyleClass().add("list-item-right-title");
        Label statusL = new Label(status);
        statusL.getStyleClass().addAll("badge", badgeClass);
        rightInfo.getChildren().addAll(amountL, statusL);

        item.getChildren().addAll(leftInfo, spacer, rightInfo);
        recentTransactionsContainer.getChildren().add(item);
    }
}
