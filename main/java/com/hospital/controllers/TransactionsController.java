package com.hospital.controllers;

import javafx.fxml.FXML;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class TransactionsController {

    public static class Trans {
        public String inv, name, srv, dt, amt, st;
        public Trans(String a, String b, String c, String d, String e, String f) {
            inv=a; name=b; srv=c; dt=d; amt=e; st=f;
        }
    }

    @FXML private TableView<Trans> transactionTable;
    @FXML private TableColumn<Trans, String> colInvoice;
    @FXML private TableColumn<Trans, String> colPatient;
    @FXML private TableColumn<Trans, String> colService;
    @FXML private TableColumn<Trans, String> colDate;
    @FXML private TableColumn<Trans, String> colAmount;
    @FXML private TableColumn<Trans, String> colStatus;

    @FXML
    public void initialize() {
        colInvoice.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().inv));
        colPatient.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().name));
        colService.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().srv));
        colDate.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().dt));
        colAmount.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().amt));
        colStatus.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().st));

        transactionTable.getItems().add(new Trans("INV-001", "Sarah Johnson", "General Consultation", "2026-04-01", "$150.00", "Paid"));
        transactionTable.getItems().add(new Trans("INV-002", "Michael Chen", "Dental Cleaning", "2026-04-02", "$200.00", "Pending"));
        transactionTable.getItems().add(new Trans("INV-003", "Emily Brown", "Blood Test", "2026-03-28", "$85.00", "Partial"));
        transactionTable.getItems().add(new Trans("INV-004", "James Wilson", "X-Ray", "2026-03-25", "$300.00", "Paid"));

        // Adjust column widths to be more proportional
        colInvoice.prefWidthProperty().bind(transactionTable.widthProperty().multiply(0.15));
        colPatient.prefWidthProperty().bind(transactionTable.widthProperty().multiply(0.2));
        colService.prefWidthProperty().bind(transactionTable.widthProperty().multiply(0.2));
        colDate.prefWidthProperty().bind(transactionTable.widthProperty().multiply(0.15));
        colAmount.prefWidthProperty().bind(transactionTable.widthProperty().multiply(0.15));
        colStatus.prefWidthProperty().bind(transactionTable.widthProperty().multiply(0.15));
    }

    @FXML
    public void handleAddTransaction() {
        javafx.scene.control.Dialog<javafx.scene.control.ButtonType> dialog = new javafx.scene.control.Dialog<>();
        dialog.setTitle("New Invoice");
        dialog.setHeaderText("Create a new transaction invoice:");

        javafx.scene.control.DialogPane dialogPane = dialog.getDialogPane();
        dialogPane.getButtonTypes().addAll(javafx.scene.control.ButtonType.OK, javafx.scene.control.ButtonType.CANCEL);

        javafx.scene.layout.GridPane grid = new javafx.scene.layout.GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new javafx.geometry.Insets(20, 150, 10, 10));

        javafx.scene.control.TextField patientInput = new javafx.scene.control.TextField();
        javafx.scene.control.TextField serviceInput = new javafx.scene.control.TextField();
        javafx.scene.control.TextField amountInput = new javafx.scene.control.TextField();

        grid.add(new javafx.scene.control.Label("Patient:"), 0, 0); grid.add(patientInput, 1, 0);
        grid.add(new javafx.scene.control.Label("Service:"), 0, 1); grid.add(serviceInput, 1, 1);
        grid.add(new javafx.scene.control.Label("Amount:"), 0, 2); grid.add(amountInput, 1, 2);

        dialogPane.setContent(grid);
        dialogPane.lookupButton(javafx.scene.control.ButtonType.OK).setStyle("-fx-background-color: #111827; -fx-text-fill: white; -fx-cursor: hand; -fx-padding: 8 16; -fx-background-radius: 6;");

        dialog.showAndWait().ifPresent(response -> {
            if (response == javafx.scene.control.ButtonType.OK) {
                int nextInvNum = transactionTable.getItems().size() + 1;
                transactionTable.getItems().add(new Trans(
                    "INV-00" + nextInvNum,
                    patientInput.getText().isEmpty() ? "Unknown Patient" : patientInput.getText(),
                    serviceInput.getText().isEmpty() ? "Standard Visit" : serviceInput.getText(),
                    "2026-04-06",
                    amountInput.getText().isEmpty() ? "$0.00" : "$" + amountInput.getText(),
                    "Pending"
                ));
            }
        });
    }
}
