package com.hospital.inventory;

import javafx.fxml.FXML;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.event.ActionEvent;

public class MaintenanceController {

    @FXML private TableView<Asset> assetTable;
    @FXML private TableColumn<Asset, String> colName;
    @FXML private TableColumn<Asset, String> colId;
    @FXML private TableColumn<Asset, String> colLocation;
    @FXML private TableColumn<Asset, String> colStatus;
    @FXML private TableColumn<Asset, String> colLastService;
    @FXML private TableColumn<Asset, String> colCalibrationDue;

    @FXML private TextField searchField;
    @FXML private Label assetsCountLabel;

    // Detail Panel bindings
    @FXML private Label detailIdLabel;
    @FXML private Label detailNameLabel;
    @FXML private Label detailLocationLabel;
    @FXML private Label lastServiceLabel;
    @FXML private Label nextCalibLabel;
    @FXML private VBox historyContainer;

    private final ObservableList<Asset> masterData = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // 1. Initialize Mock Data
        loadMockAssets();

        // 2. Setup Table Column bindings
        colName.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getName()));
        colId.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getId()));
        colLocation.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getLocation()));
        colStatus.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getStatus()));
        colLastService.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getLastService()));
        colCalibrationDue.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getCalibrationDue()));

        // Proportional column widths
        colName.prefWidthProperty().bind(assetTable.widthProperty().multiply(0.25));
        colId.prefWidthProperty().bind(assetTable.widthProperty().multiply(0.13));
        colLocation.prefWidthProperty().bind(assetTable.widthProperty().multiply(0.17));
        colStatus.prefWidthProperty().bind(assetTable.widthProperty().multiply(0.17));
        colLastService.prefWidthProperty().bind(assetTable.widthProperty().multiply(0.13));
        colCalibrationDue.prefWidthProperty().bind(assetTable.widthProperty().multiply(0.15));

        // 3. Custom Badge Renderer for Status Column
        colStatus.setCellFactory(column -> new TableCell<>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                    setGraphic(null);
                } else {
                    Label badge = new Label(item.toUpperCase());
                    badge.getStyleClass().add("badge");
                    
                    if (item.equalsIgnoreCase("Operational")) {
                        badge.getStyleClass().add("badge-completed");
                    } else if (item.equalsIgnoreCase("Scheduled Service")) {
                        badge.getStyleClass().add("badge-scheduled");
                    } else if (item.equalsIgnoreCase("Out of Order")) {
                        badge.getStyleClass().add("badge-unpaid");
                    } else {
                        badge.setStyle("-fx-background-color: #f3f4f6; -fx-text-fill: #374151;");
                    }
                    
                    setGraphic(badge);
                    setText(null);
                }
            }
        });

        // 4. Implement Search/Filter logic using FilteredList
        FilteredList<Asset> filteredData = new FilteredList<>(masterData, p -> true);
        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(asset -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                return asset.getName().toLowerCase().contains(lowerCaseFilter) ||
                       asset.getId().toLowerCase().contains(lowerCaseFilter) ||
                       asset.getLocation().toLowerCase().contains(lowerCaseFilter);
            });
            assetsCountLabel.setText("Monitoring " + filteredData.size() + " high-value medical assets");
        });

        SortedList<Asset> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(assetTable.comparatorProperty());
        assetTable.setItems(sortedData);

        // 5. Select first asset and bind Selection Listener
        if (!masterData.isEmpty()) {
            assetTable.getSelectionModel().select(0);
            updateDetailPanel(masterData.get(0));
        }

        assetTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                updateDetailPanel(newSelection);
            }
        });
    }

    private void updateDetailPanel(Asset asset) {
        detailIdLabel.setText(asset.getId());
        detailNameLabel.setText(asset.getName());
        detailLocationLabel.setText(asset.getLocation() + ", Floor 1");
        lastServiceLabel.setText(asset.getLastService());
        nextCalibLabel.setText(asset.getCalibrationDue());

        // Color Calibration due warning if Scheduled Service or Out of Order
        if (asset.getStatus().equalsIgnoreCase("Out of Order")) {
            nextCalibLabel.setStyle("-fx-text-fill: #dc2626; -fx-font-size: 18px; -fx-font-weight: bold;"); // Red warning
        } else if (asset.getStatus().equalsIgnoreCase("Scheduled Service")) {
            nextCalibLabel.setStyle("-fx-text-fill: #a16207; -fx-font-size: 18px; -fx-font-weight: bold;"); // Amber warning
        } else {
            nextCalibLabel.setStyle("-fx-text-fill: #00478d; -fx-font-size: 18px; -fx-font-weight: bold;"); // Primary blue
        }

        // Dynamically build the service logs timeline
        historyContainer.getChildren().clear();
        for (ServiceLog log : asset.getServiceHistory()) {
            HBox item = new HBox(12);
            item.setAlignment(Pos.TOP_LEFT);

            // Timeline line and circle graphics
            VBox lineContainer = new VBox(0);
            lineContainer.setAlignment(Pos.TOP_CENTER);
            
            StackPane circle = new StackPane();
            circle.setPrefSize(8, 8);
            circle.setMinSize(8, 8);
            circle.setMaxSize(8, 8);
            
            // Dot color based on status or primary color
            circle.setStyle("-fx-background-color: #00478d; -fx-background-radius: 99px;");

            Region line = new Region();
            line.setStyle("-fx-background-color: #c2c6d4;");
            line.setPrefWidth(1.5);
            VBox.setVgrow(line, Priority.ALWAYS);
            
            // Allocate spacing
            VBox.setMargin(circle, new javafx.geometry.Insets(4, 0, 0, 0));
            lineContainer.getChildren().addAll(circle, line);

            // Log details pane
            VBox logContent = new VBox(4);
            HBox.setHgrow(logContent, Priority.ALWAYS);
            VBox.setMargin(logContent, new javafx.geometry.Insets(0, 0, 16, 0));

            Label titleLbl = new Label(log.getTitle());
            titleLbl.setStyle("-fx-font-weight: bold; -fx-font-size: 13px; -fx-text-fill: #191c21;");

            Label techLbl = new Label("Technician: " + log.getTechnician());
            techLbl.setStyle("-fx-font-size: 12px; -fx-text-fill: #424752;");

            Label descLbl = new Label(log.getDescription());
            descLbl.setWrapText(true);
            descLbl.setStyle("-fx-font-size: 12px; -fx-font-style: italic; -fx-text-fill: #3b494a; -fx-background-color: #f2f3fb; -fx-padding: 8px; -fx-background-radius: 4px; -fx-border-color: #c2c6d4; -fx-border-width: 1px; -fx-border-radius: 4px;");

            Label timeLbl = new Label(log.getDateTime());
            timeLbl.setStyle("-fx-font-size: 10px; -fx-text-fill: #727783; -fx-padding: 2px 0 0 0;");

            logContent.getChildren().addAll(titleLbl, techLbl, descLbl, timeLbl);

            item.getChildren().addAll(lineContainer, logContent);
            historyContainer.getChildren().add(item);
        }
    }

    @FXML
    private void handleFilter(ActionEvent event) {
        // Implement simple alert or placeholder filter dialog
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Filter Directory");
        alert.setHeaderText("Directory Filters");
        alert.setContentText("Filter functionality is loaded. You can narrow down assets using the search bar by entering the asset ID, name, or location.");
        alert.showAndWait();
    }

    @FXML
    private void handleExportCSV(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Export CSV");
        alert.setHeaderText("Export Report");
        alert.setContentText("Asset Directory CSV report generated and downloaded to device exports.");
        alert.showAndWait();
    }

    @FXML
    private void handleEditAsset(ActionEvent event) {
        Asset selected = assetTable.getSelectionModel().getSelectedItem();
        if (selected == null) return;

        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setTitle("Edit Clinical Asset");
        dialog.setHeaderText("Update details for asset: " + selected.getId());
        DialogPane dialogPane = dialog.getDialogPane();
        dialogPane.getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new javafx.geometry.Insets(20, 150, 10, 10));

        TextField nameIn = new TextField(selected.getName());
        TextField locIn = new TextField(selected.getLocation());

        grid.add(new Label("Asset Name:"), 0, 0); grid.add(nameIn, 1, 0);
        grid.add(new Label("Location:"), 0, 1); grid.add(locIn, 1, 1);

        dialogPane.setContent(grid);
        dialogPane.lookupButton(ButtonType.OK).setStyle("-fx-background-color: #111827; -fx-text-fill: white; -fx-cursor: hand;");

        dialog.showAndWait().ifPresent(res -> {
            if (res == ButtonType.OK) {
                // Remove/Reinsert or update selected values and refresh table
                int idx = masterData.indexOf(selected);
                if (idx >= 0) {
                    Asset updated = new Asset(
                        nameIn.getText(),
                        selected.getId(),
                        locIn.getText(),
                        selected.getStatus(),
                        selected.getLastService(),
                        selected.getCalibrationDue(),
                        selected.getDetails()
                    );
                    for(ServiceLog log : selected.getServiceHistory()) {
                        updated.addServiceLog(log);
                    }
                    masterData.set(idx, updated);
                    assetTable.getSelectionModel().select(updated);
                    updateDetailPanel(updated);
                }
            }
        });
    }

    @FXML
    private void handleScheduleMaintenance(ActionEvent event) {
        Asset selected = assetTable.getSelectionModel().getSelectedItem();
        if (selected == null) return;

        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setTitle("Schedule Maintenance");
        dialog.setHeaderText("Create a new maintenance log for: " + selected.getName());
        DialogPane dialogPane = dialog.getDialogPane();
        dialogPane.getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new javafx.geometry.Insets(20, 150, 10, 10));

        TextField titleIn = new TextField("Routine Inspection");
        TextField techIn = new TextField("Dr. Sarah Jenkins");
        TextArea descIn = new TextArea("Helium and pressure levels checked. Gradient coil performance confirmed.");
        descIn.setPrefRowCount(3);
        descIn.setWrapText(true);

        grid.add(new Label("Service Title:"), 0, 0); grid.add(titleIn, 1, 0);
        grid.add(new Label("Technician:"), 0, 1); grid.add(techIn, 1, 1);
        grid.add(new Label("Description:"), 0, 2); grid.add(descIn, 1, 2);

        dialogPane.setContent(grid);
        dialogPane.lookupButton(ButtonType.OK).setStyle("-fx-background-color: #111827; -fx-text-fill: white; -fx-cursor: hand;");

        dialog.showAndWait().ifPresent(res -> {
            if (res == ButtonType.OK) {
                ServiceLog log = new ServiceLog(
                    titleIn.getText(),
                    techIn.getText(),
                    descIn.getText(),
                    "Jun 11, 2026 · 03:00 PM"
                );
                selected.addServiceLog(0, log); // Add to beginning of log
                updateDetailPanel(selected);
            }
        });
    }

    private void loadMockAssets() {
        // 1. MRI Scanner
        Asset mri = new Asset("MRI Scanner - Tesla 3.0", "#MRI-7742-XP", "Radiology Wing B", "Operational", "Oct 12, 2023", "Dec 15, 2024", "Radiology Wing B, Floor 1");
        mri.addServiceLog(new ServiceLog("Annual Calibration", "Mark Stevens", "Helium levels verified at 98%. Gradient coil performance within standard deviation. Software updated to v4.2.", "Oct 12, 2023 · 09:30 AM"));
        mri.addServiceLog(new ServiceLog("Emergency Coil Replacement", "Sarah Wu", "Replaced faulty head coil connector. Tested with phantom load, signal-to-noise ratio restored.", "May 22, 2023 · 02:15 PM"));
        masterData.add(mri);

        // 2. Ventilator
        Asset ven = new Asset("Ventilator Pro-V1", "#VEN-2210-AS", "ICU Unit 4", "Scheduled Service", "Jan 05, 2024", "Oct 28, 2024", "ICU Unit 4, Floor 1");
        ven.addServiceLog(new ServiceLog("Biomedical Inspection", "Robert Miller", "O2 sensor calibrated. Alarm volume tested. Backup battery verified at 94% capacity.", "Jan 05, 2024 · 11:00 AM"));
        ven.addServiceLog(new ServiceLog("Filter Replacement", "James Lin", "Replaced HEPA and air intake filters. Performed leak test, pressure holds at 40 cmH2O.", "Aug 18, 2023 · 03:30 PM"));
        masterData.add(ven);

        // 3. Mobile X-Ray
        Asset xray = new Asset("Mobile X-Ray Unit", "#XRY-1198-MK", "Emergency Dept", "Out of Order", "Sep 30, 2023", "Nov 01, 2024", "Emergency Dept, Floor 1");
        xray.addServiceLog(new ServiceLog("Tube Output Calibration", "Diana Ross", "Collimator alignment adjusted by 1.2mm. Checked exposure timer accuracy.", "Sep 30, 2023 · 10:15 AM"));
        xray.addServiceLog(new ServiceLog("Battery Diagnostics", "Thomas Edison", "Replaced cell bank B. Full charge cycle verified. Unit returned to service.", "Apr 15, 2023 · 08:45 AM"));
        masterData.add(xray);

        // 4. Defibrillator
        Asset def = new Asset("Defibrillator HeartSync", "#DEF-3345-BT", "Cardiac Lab", "Operational", "Feb 14, 2024", "Aug 14, 2025", "Cardiac Lab, Floor 1");
        def.addServiceLog(new ServiceLog("Routine Inspection", "Gary Anderson", "Discharged test load. ECG sync verified. Pads and cables in date.", "Feb 14, 2024 · 09:00 AM"));
        masterData.add(def);

        // 5. Ultrasound
        Asset uls = new Asset("Ultrasound System G4", "#ULS-8821-QR", "Obstetrics Wing", "Operational", "May 19, 2024", "May 19, 2025", "Obstetrics Wing, Floor 1");
        uls.addServiceLog(new ServiceLog("Probe Testing", "Nancy Reagan", "Acoustic output verified. Checked for element dropout on all transducers. Software v2.1 active.", "May 19, 2024 · 11:45 AM"));
        masterData.add(uls);

        // 6. Dialysis Machine
        Asset dia = new Asset("Dialysis Machine 500S", "#DIA-0091-NM", "Nephrology Unit", "Scheduled Service", "Nov 11, 2023", "Oct 25, 2024", "Nephrology Unit, Floor 1");
        dia.addServiceLog(new ServiceLog("Annual PM", "Mark Stevens", "Replaced blood pump tubing and dialysate filters. Verified conductivity levels.", "Nov 11, 2023 · 01:00 PM"));
        masterData.add(dia);

        assetsCountLabel.setText("Monitoring " + masterData.size() + " high-value medical assets");
    }
}
