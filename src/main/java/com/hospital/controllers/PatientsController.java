package com.hospital.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.geometry.Pos;
import org.kordamp.ikonli.javafx.FontIcon;
import com.hospital.models.Patient;
import java.io.IOException;

public class PatientsController {

    @FXML private TextField searchField;
    @FXML private FlowPane patientsGrid;
    @FXML private Button addPatientButton;

    private MainController mainController;

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    @FXML
    public void initialize() {
        loadPatients();
        searchField.textProperty().addListener((obs, oldVal, newVal) -> filterPatients(newVal));
        addPatientButton.setOnAction(e -> handleAddPatient());
    }

    private void handleAddPatient() {
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setTitle("Add New Patient");
        dialog.setHeaderText("Enter patient details below");

        DialogPane dialogPane = dialog.getDialogPane();
        dialogPane.getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new javafx.geometry.Insets(20, 150, 10, 10));

        TextField nameInput  = new TextField(); nameInput.setPromptText("Name");
        TextField ageInput   = new TextField(); ageInput.setPromptText("Age");
        TextField bloodInput = new TextField(); bloodInput.setPromptText("e.g. O+");
        TextField phoneInput = new TextField(); phoneInput.setPromptText("Phone");
        TextField emailInput = new TextField(); emailInput.setPromptText("Email");

        ComboBox<String> genderCombo = new ComboBox<>();
        genderCombo.getItems().addAll("Male", "Female", "Other");
        genderCombo.setPromptText("Select Gender");

        ComboBox<String> typeCombo = new ComboBox<>();
        typeCombo.getItems().addAll("INPATIENT", "OUTPATIENT", "MATERNITY");
        typeCombo.setPromptText("Select Patient Type");

        grid.add(new Label("Name:"),       0, 0); grid.add(nameInput,   1, 0);
        grid.add(new Label("Age:"),        0, 1); grid.add(ageInput,    1, 1);
        grid.add(new Label("Gender:"),     0, 2); grid.add(genderCombo, 1, 2);
        grid.add(new Label("Blood Type:"), 0, 3); grid.add(bloodInput,  1, 3);
        grid.add(new Label("Phone:"),      0, 4); grid.add(phoneInput,  1, 4);
        grid.add(new Label("Email:"),      0, 5); grid.add(emailInput,  1, 5);
        grid.add(new Label("Type:"),       0, 6); grid.add(typeCombo,   1, 6);

        dialogPane.setContent(grid);
        dialogPane.lookupButton(ButtonType.OK).setStyle(
            "-fx-background-color: #111827; -fx-text-fill: white; -fx-cursor: hand; -fx-padding: 8 16; -fx-background-radius: 6;");

        dialog.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                Patient patient = new Patient();
                patient.setName(nameInput.getText().isEmpty()   ? "New Patient" : nameInput.getText());
                patient.setAge(ageInput.getText().isEmpty()     ? 0 : Integer.parseInt(ageInput.getText()));
                patient.setGender(genderCombo.getValue() != null ? genderCombo.getValue() : "Unknown");
                patient.setBloodType(bloodInput.getText().isEmpty() ? "N/A" : bloodInput.getText());
                patient.setPhone(phoneInput.getText().isEmpty()     ? "N/A" : phoneInput.getText());
                patient.setEmail(emailInput.getText().isEmpty()     ? "N/A" : emailInput.getText());
                patient.setStatus("Active");
                patient.setType(typeCombo.getValue() != null
                    ? Patient.PatientType.valueOf(typeCombo.getValue())
                    : Patient.PatientType.INPATIENT);

                addPatientCard(patient);
            }
        });
    }

    private void loadPatients() {
        patientsGrid.getChildren().clear();
        addPatientCard(new Patient("Sarah Johnson", 34, "Female", "Active", "O+",  "(555) 123-4567", "sarah.j@example.com",  Patient.PatientType.INPATIENT));
        addPatientCard(new Patient("Michael Chen",  45, "Male",   "Active", "A-",  "(555) 987-6543", "m.chen@example.com",   Patient.PatientType.OUTPATIENT));
        addPatientCard(new Patient("Emily Brown",   28, "Female", "Active", "B+",  "(555) 456-7890", "emily.b@example.com",  Patient.PatientType.MATERNITY));
        addPatientCard(new Patient("James Wilson",  52, "Male",   "Active", "AB+", "(555) 234-5678", "j.wilson@example.com", Patient.PatientType.INPATIENT));
    }

    private void addPatientCard(Patient patient) {
        VBox card = new VBox();
        card.getStyleClass().add("card");
        card.setPrefWidth(300);

        VBox content = new VBox(16);
        content.getStyleClass().add("card-content");

        BorderPane topBox = new BorderPane();
        VBox nameBox = new VBox(2);
        Label nameL    = new Label(patient.getName()); nameL.getStyleClass().add("card-title");
        Label detailsL = new Label(patient.getAge() + " yrs • " + patient.getGender());
        detailsL.getStyleClass().add("list-item-subtitle");
        nameBox.getChildren().addAll(nameL, detailsL);
        topBox.setLeft(nameBox);

        Label statusL = new Label("active");
        statusL.getStyleClass().addAll("badge", "badge-completed");
        BorderPane.setAlignment(statusL, Pos.TOP_RIGHT);
        topBox.setRight(statusL);

        VBox detailsBox = new VBox(8);
        detailsBox.getChildren().addAll(
            createDetailRow("Blood Type:", patient.getBloodType()),
            createDetailRow("Phone:",      patient.getPhone()),
            createDetailRow("Email:",      patient.getEmail()),
            createDetailRow("Status:",     patient.getStatus())
        );

        HBox actionsBox = new HBox(8);
        actionsBox.setStyle("-fx-border-color: #e5e7eb transparent transparent transparent; -fx-border-width: 1 0 0 0; -fx-padding: 16 0 0 0;");

        Button editBtn = new Button("Edit");
        editBtn.getStyleClass().addAll("btn", "btn-outline");
        FontIcon editIcon = new FontIcon("fth-edit"); editIcon.setIconSize(14);
        editBtn.setGraphic(editIcon);

        Button deleteBtn = new Button("");
        deleteBtn.getStyleClass().addAll("btn", "btn-outline", "btn-danger");
        FontIcon deleteIcon = new FontIcon("fth-trash-2"); deleteIcon.setIconSize(14);
        deleteBtn.setGraphic(deleteIcon);

        editBtn.setOnMouseClicked(event -> event.consume());
        deleteBtn.setOnMouseClicked(event -> {
            event.consume();
            patientsGrid.getChildren().remove(card);
        });

        actionsBox.getChildren().addAll(editBtn, deleteBtn);
        content.getChildren().addAll(topBox, detailsBox, actionsBox);
        card.getChildren().add(content);

        card.setUserData(patient);
        card.setOnMouseClicked(event -> openPatientView(patient));

        patientsGrid.getChildren().add(card);
    }

    private void openPatientView(Patient patient) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/hospital/fxml/PatientDetailsView.fxml"));
            Parent view = loader.load();

            PatientDetailsController controller = loader.getController();
            controller.setPatient(patient);
            controller.setMainController(mainController);

            if (mainController != null) {
                mainController.setContent(view);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private HBox createDetailRow(String label, String value) {
        HBox row = new HBox(8);
        Label lbl = new Label(label);
        lbl.setStyle("-fx-text-fill: #4b5563; -fx-font-size: 14;");
        Label val = new Label(value);
        val.setStyle("-fx-text-fill: #111827; -fx-font-weight: bold; -fx-font-size: 14;");
        row.getChildren().addAll(lbl, val);
        return row;
    }

    private void filterPatients(String query) {
        String q = query.toLowerCase();
        for (javafx.scene.Node node : patientsGrid.getChildren()) {
            if (node instanceof VBox card && card.getUserData() instanceof Patient patient) {
                boolean matches = patient.getName().toLowerCase().contains(q)
                    || patient.getGender().toLowerCase().contains(q)
                    || (patient.getType() != null && patient.getType().name().toLowerCase().contains(q));
                card.setVisible(matches);
                card.setManaged(matches);
            }
        }
    }
}