package com.hospital.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.BorderPane;
import javafx.geometry.Pos;
import org.kordamp.ikonli.javafx.FontIcon;

public class PatientsController {

    @FXML private TextField searchField;
    @FXML private FlowPane patientsGrid;
    @FXML private Button addPatientButton;

    @FXML
    public void initialize() {
        // Mock data initialization
        loadPatients();

        // Optional: Simple search filter logic setup
        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            filterPatients(newValue);
        });

        // Add Patient Button functionality
        addPatientButton.setOnAction(e -> handleAddPatient());
    }

    private void handleAddPatient() {
        javafx.scene.control.Dialog<javafx.scene.control.ButtonType> dialog = new javafx.scene.control.Dialog<>();
        dialog.setTitle("Add New Patient");
        dialog.setHeaderText("Enter patient details below");

        javafx.scene.control.DialogPane dialogPane = dialog.getDialogPane();
        dialogPane.getButtonTypes().addAll(javafx.scene.control.ButtonType.OK, javafx.scene.control.ButtonType.CANCEL);

        javafx.scene.layout.GridPane grid = new javafx.scene.layout.GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new javafx.geometry.Insets(20, 150, 10, 10));

        TextField nameInput = new TextField();
        nameInput.setPromptText("Name");
        TextField ageInput = new TextField();
        ageInput.setPromptText("Age");
        TextField genderInput = new TextField();
        genderInput.setPromptText("Gender");
        TextField bloodInput = new TextField();
        bloodInput.setPromptText("Blood Type");
        TextField phoneInput = new TextField();
        phoneInput.setPromptText("Phone");
        TextField emailInput = new TextField();
        emailInput.setPromptText("Email");

        grid.add(new Label("Name:"), 0, 0); grid.add(nameInput, 1, 0);
        grid.add(new Label("Age:"), 0, 1); grid.add(ageInput, 1, 1);
        grid.add(new Label("Gender:"), 0, 2); grid.add(genderInput, 1, 2);
        grid.add(new Label("Blood Type:"), 0, 3); grid.add(bloodInput, 1, 3);
        grid.add(new Label("Phone:"), 0, 4); grid.add(phoneInput, 1, 4);
        grid.add(new Label("Email:"), 0, 5); grid.add(emailInput, 1, 5);

        dialogPane.setContent(grid);
        
        // Optional styling
        dialogPane.lookupButton(javafx.scene.control.ButtonType.OK).setStyle("-fx-background-color: #111827; -fx-text-fill: white; -fx-cursor: hand; -fx-padding: 8 16; -fx-background-radius: 6;");

        dialog.showAndWait().ifPresent(response -> {
            if (response == javafx.scene.control.ButtonType.OK) {
                // Today as mock last visit
                addPatientCard(
                    nameInput.getText().isEmpty() ? "New Patient" : nameInput.getText(),
                    ageInput.getText().isEmpty() ? "0" : ageInput.getText(),
                    genderInput.getText().isEmpty() ? "Unknown" : genderInput.getText(),
                    "active",
                    bloodInput.getText().isEmpty() ? "N/A" : bloodInput.getText(),
                    phoneInput.getText().isEmpty() ? "N/A" : phoneInput.getText(),
                    emailInput.getText().isEmpty() ? "N/A" : emailInput.getText(),
                    "2026-04-06"
                );
            }
        });
    }

    private void loadPatients() {
        patientsGrid.getChildren().clear();
        
        // Creating mock patients equivalent to mockData in React
        addPatientCard("Sarah Johnson", "34", "Female", "active", "O+", "(555) 123-4567", "sarah.j@example.com", "2026-03-15");
        addPatientCard("Michael Chen", "45", "Male", "active", "A-", "(555) 987-6543", "m.chen@example.com", "2026-03-10");
        addPatientCard("Emily Brown", "28", "Female", "inactive", "B+", "(555) 456-7890", "emily.b@example.com", "2025-11-22");
        addPatientCard("James Wilson", "52", "Male", "active", "AB+", "(555) 234-5678", "j.wilson@example.com", "2026-04-01");
    }

    private void addPatientCard(String name, String age, String gender, String status, 
                                String bloodType, String phone, String email, String lastVisit) {
        
        VBox card = new VBox();
        card.getStyleClass().add("card");
        card.setPrefWidth(300); // Set fixed width for flow pane wrapping
        
        VBox content = new VBox(16);
        content.getStyleClass().add("card-content");

        // Top section: Name, age/gender, and status badge
        BorderPane topBox = new BorderPane();
        VBox nameBox = new VBox(2);
        Label nameL = new Label(name);
        nameL.getStyleClass().add("card-title");
        Label detailsL = new Label(age + " years • " + gender);
        detailsL.getStyleClass().add("list-item-subtitle"); // Using subtitle class
        nameBox.getChildren().addAll(nameL, detailsL);
        topBox.setLeft(nameBox);

        Label statusL = new Label(status);
        if ("active".equals(status)) {
            statusL.getStyleClass().addAll("badge", "badge-completed"); // Green badge
        } else {
            statusL.getStyleClass().addAll("badge");
            statusL.setStyle("-fx-background-color: #f3f4f6; -fx-text-fill: #374151;"); // Gray badge
        }
        BorderPane.setAlignment(statusL, Pos.TOP_RIGHT);
        topBox.setRight(statusL);

        // Middle section: details grid
        VBox detailsBox = new VBox(8);
        detailsBox.getChildren().add(createDetailRow("Blood Type:", bloodType));
        detailsBox.getChildren().add(createDetailRow("Phone:", phone));
        detailsBox.getChildren().add(createDetailRow("Email:", email));
        detailsBox.getChildren().add(createDetailRow("Last Visit:", lastVisit));

        // Bottom section: Actions
        HBox actionsBox = new HBox(8);
        actionsBox.setStyle("-fx-border-color: #e5e7eb transparent transparent transparent; -fx-border-width: 1 0 0 0; -fx-padding: 16 0 0 0;");
        
        Button editBtn = new Button("Edit");
        editBtn.getStyleClass().addAll("btn", "btn-outline");
        editBtn.setMaxWidth(Double.MAX_VALUE);
        HBox.setHgrow(editBtn, Priority.ALWAYS);
        FontIcon editIcon = new FontIcon("fth-edit");
        editIcon.setIconSize(14);
        editBtn.setGraphic(editIcon);

        Button deleteBtn = new Button("");
        deleteBtn.getStyleClass().addAll("btn", "btn-outline", "btn-danger");
        FontIcon deleteIcon = new FontIcon("fth-trash-2");
        deleteIcon.setIconSize(14);
        deleteBtn.setGraphic(deleteIcon);

        actionsBox.getChildren().addAll(editBtn, deleteBtn);

        content.getChildren().addAll(topBox, detailsBox, actionsBox);
        card.getChildren().add(content);
        
        // Save the patient name in the userData so we can filter it later
        card.setUserData(name + " " + email);
        patientsGrid.getChildren().add(card);
    }

    private HBox createDetailRow(String label, String value) {
        HBox row = new HBox(8);
        Label lbl = new Label(label);
        lbl.setStyle("-fx-text-fill: #4b5563; -fx-font-size: 14;"); // gray-600
        Label val = new Label(value);
        val.setStyle("-fx-text-fill: #111827; -fx-font-weight: bold; -fx-font-size: 14;"); // gray-900, semi-bold
        row.getChildren().addAll(lbl, val);
        return row;
    }

    private void filterPatients(String query) {
        String lowerCaseQuery = query.toLowerCase();
        for (javafx.scene.Node node : patientsGrid.getChildren()) {
            if (node instanceof VBox) {
                String searchData = (String) node.getUserData();
                if (searchData.toLowerCase().contains(lowerCaseQuery)) {
                    node.setVisible(true);
                    node.setManaged(true);
                } else {
                    node.setVisible(false);
                    node.setManaged(false);
                }
            }
        }
    }
}
