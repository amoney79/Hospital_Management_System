package com.hospital.inventory;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class InventorySettingsController {

    @FXML private Spinner<Integer> thresholdSpinner;
    @FXML private CheckBox emailAlertsCheckbox;
    @FXML private CheckBox smsAlertsCheckbox;
    @FXML private CheckBox autoReorderCheckbox;
    @FXML private ComboBox<String> preferredVendorCombo;
    @FXML private ComboBox<String> reorderQtyCombo;
    @FXML private TextField syncEndpointField;
    @FXML private Button btnSyncNow;
    @FXML private Label syncStatusLabel;
    @FXML private Button btnReset;
    @FXML private Button btnSave;

    @FXML
    public void initialize() {
        // Initialize threshold spinner (10% to 50% threshold, default 20%)
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(10, 50, 20);
        thresholdSpinner.setValueFactory(valueFactory);

        // Populate preferred vendor options
        preferredVendorCombo.getItems().addAll("Medline Industries", "Baxter Healthcare", "Pfizer Pharmaceuticals", "McKesson Corp");
        preferredVendorCombo.setValue("Medline Industries");

        // Populate quantity multiplier options
        reorderQtyCombo.getItems().addAll("1x Minimum Stock Level", "2x Minimum Stock Level", "3x Minimum Stock Level");
        reorderQtyCombo.setValue("2x Minimum Stock Level");

        // Handle Sync catalog
        btnSyncNow.setOnAction(event -> {
            btnSyncNow.setDisable(true);
            btnSyncNow.setText("Syncing...");
            
            // Simulating API call response
            javafx.animation.PauseTransition pause = new javafx.animation.PauseTransition(javafx.util.Duration.seconds(1.5));
            pause.setOnFinished(e -> {
                btnSyncNow.setDisable(false);
                btnSyncNow.setText("Sync Catalog Now");
                String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("hh:mm a"));
                syncStatusLabel.setText("Last synced: Today, " + timestamp);
                
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Catalog Sync Success");
                alert.setHeaderText(null);
                alert.setContentText("Inventory catalog successfully synchronized with HMS Server.");
                alert.showAndWait();
            });
            pause.play();
        });

        // Handle Save settings
        btnSave.setOnAction(event -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Settings Saved");
            alert.setHeaderText(null);
            alert.setContentText("Your inventory settings preferences have been successfully updated.");
            alert.showAndWait();
        });

        // Handle Reset settings
        btnReset.setOnAction(event -> {
            thresholdSpinner.getValueFactory().setValue(20);
            emailAlertsCheckbox.setSelected(true);
            smsAlertsCheckbox.setSelected(false);
            autoReorderCheckbox.setSelected(false);
            preferredVendorCombo.setValue("Medline Industries");
            reorderQtyCombo.setValue("2x Minimum Stock Level");
            syncEndpointField.setText("https://hms-local.hospital.internal/api/v1/sync");
        });
    }
}
