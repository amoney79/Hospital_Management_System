package com.hospital.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.Stage;
import java.io.IOException;

public class MainController {

    @FXML
    private StackPane contentArea;

    @FXML
    private HBox navDashboard;

    @FXML
    private HBox navPatients;

    @FXML
    private HBox navDoctors;

    @FXML
    private HBox navAppointments;

    @FXML
    private HBox navRecords;

    @FXML
    private HBox navTransactions;

    @FXML
    private HBox navInventory;

    @FXML
    private HBox navSettings;

    @FXML
    public void initialize() {
        loadView("Dashboard");
        setActiveNav(navDashboard);
        // Inventory module is loaded on-demand in openInventoryModule()
    }

    @FXML
    private void handleNavClick(MouseEvent event) {
        HBox source = (HBox) event.getSource();
        String id = source.getId();
        
        if (id.equals("navDashboard")) loadView("Dashboard");
        else if (id.equals("navPatients")) loadView("Patients"); // Placeholder
        else if (id.equals("navDoctors")) loadView("Doctors"); // Placeholder
        else if (id.equals("navAppointments")) loadView("Appointments"); // Placeholder
        else if (id.equals("navRecords")) loadView("MedicalRecords"); // Placeholder
        else if (id.equals("navTransactions")) loadView("Transactions"); // Placeholder
        else if (id.equals("navInventory")) {
            openInventoryModule(event);
            return;
        }
        else if (id.equals("navSettings")) loadView("Settings");//placeholder
        
        setActiveNav(source);
    }

    private void setActiveNav(HBox activeNav) {
        HBox[] navs = {navDashboard, navPatients, navDoctors, navAppointments, navRecords, navTransactions, navInventory, navSettings};
        for (HBox nav : navs) {
            if (nav != null) {
                nav.getStyleClass().remove("nav-item-active");
                if (nav.equals(activeNav)) {
                    nav.getStyleClass().add("nav-item-active");
                }
            }
        }
    }

    private void loadView(String viewName) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/hospital/fxml/" + viewName + ".fxml"));
            Parent view = loader.load();
            contentArea.getChildren().clear();
            contentArea.getChildren().add(view);
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Could not load view: " + viewName);
            // Optionally, load a generic placeholder if view doesn't exist yet
            Label placeholder = new Label(viewName + " View (Not yet implemented)");
            placeholder.setStyle("-fx-font-size: 24; -fx-text-fill: #666;");
            contentArea.getChildren().clear();
            contentArea.getChildren().add(placeholder);
        }
    }

    private Stage inventoryStage;
    private com.hospital.inventory.InventoryModuleController inventoryController;

    private void openInventoryModule(MouseEvent event) {
        Stage mainStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        
        if (inventoryStage == null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/hospital/fxml/inventory/InventoryLayout.fxml"));
                Parent root = loader.load();
                
                inventoryController = loader.getController();
                
                inventoryStage = new Stage();
                javafx.scene.Scene scene = new javafx.scene.Scene(root);
                inventoryStage.setScene(scene);
                inventoryStage.initStyle(javafx.stage.StageStyle.UNDECORATED);
                inventoryStage.setMaximized(true);
            } catch (IOException e) {
                e.printStackTrace();
                return; // Do not hide mainStage if load failed
            }
        }
        
        // Ensure main stage is passed to the controller so we can go back
        if (inventoryController != null) {
            inventoryController.setMainHmsStage(mainStage);
        }
        
        inventoryStage.show();
        mainStage.hide();
    }

    private double xOffset = 0;
    private double yOffset = 0;

    @FXML
    private void handleTitleBarPressed(MouseEvent event) {
        xOffset = event.getSceneX();
        yOffset = event.getSceneY();
    }

    @FXML
    private void handleTitleBarDragged(MouseEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setX(event.getScreenX() - xOffset);
        stage.setY(event.getScreenY() - yOffset);
    }

    @FXML
    private void handleMinimize(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setIconified(true);
    }

    @FXML
    private void handleMaximize(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setMaximized(!stage.isMaximized());
    }

    @FXML
    private void handleClose(ActionEvent event) {
        Platform.exit();
        System.exit(0);
    }
}
