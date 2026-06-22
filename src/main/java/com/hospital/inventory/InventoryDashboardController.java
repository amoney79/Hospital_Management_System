package com.hospital.inventory;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.StackPane;

public class InventoryDashboardController {

    @FXML private Label totalInventoryLabel;
    @FXML private Label lowStockLabel;
    @FXML private Label pendingOrdersLabel;
    @FXML private ProgressBar storageProgress;
    @FXML private StackPane chartPlaceholder;
    
    @FXML
    public void initialize() {
        // Example mock data (replace with service/database calls later)
        totalInventoryLabel.setText("12,842");
        lowStockLabel.setText("18");
        pendingOrdersLabel.setText("07");
        storageProgress.setProgress(0.84);

        // Replace placeholder with a chart later
        // chartPlaceholder.getChildren().setAll(new BarChart<>(...));
    }
}