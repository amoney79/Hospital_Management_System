package com.hospital.inventory;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.Node;

import java.io.IOException;

public class InventoryModuleController {

    @FXML private StackPane contentArea;
    
    @FXML private HBox navDashboard;
    @FXML private HBox navDirectory;
    @FXML private HBox navOrders;
    @FXML private HBox navMaintenance;
    @FXML private HBox navSettings;
    @FXML private HBox navSupport;

    @FXML private Label headerTitle;
    @FXML private HBox headerSearchContainer;
    @FXML private TextField searchField;
    @FXML private Label profileNameLabel;
    @FXML private Label profileRoleLabel;

    private Stage mainHmsStage;

    public void setMainHmsStage(Stage mainHmsStage) {
        this.mainHmsStage = mainHmsStage;
    }

    @FXML
    public void initialize() {
        // Load default view
        loadView("InventoryDirectory");
        setActiveNav(navDirectory);
    }

    @FXML
    private void handleNavClick(MouseEvent event) {
        HBox source = (HBox) event.getSource();
        String id = source.getId();
        
        if ("navDashboard".equals(id)) loadView("InventoryDashboard");
        else if ("navDirectory".equals(id)) loadView("InventoryDirectory");
        else if ("navOrders".equals(id)) loadView("InventoryOrders");
        else if ("navMaintenance".equals(id)) loadView("InventoryMaintenance");
        else if ("navSettings".equals(id)) loadView("InventorySettings");
        else if ("navSupport".equals(id)) loadView("InventorySupport");
        
        setActiveNav(source);
    }

    private void setActiveNav(HBox activeNav) {
        HBox[] navs = {navDashboard, navDirectory, navOrders, navMaintenance, navSettings, navSupport};
        for (HBox nav : navs) {
            if (nav != null) {
                nav.getStyleClass().remove("nav-item-active"); // We can style this similarly
                if (nav.equals(activeNav)) {
                    nav.getStyleClass().add("nav-item-active");
                }
            }
        }
    }

    private java.util.Map<String, Parent> viewCache = new java.util.HashMap<>();

    private void updateHeader(String viewName) {
        if (headerTitle == null) return; // guard if not loaded yet
        if ("InventoryDashboard".equals(viewName)) {
            headerTitle.setText("MedInventory Pro");
            headerSearchContainer.setVisible(true);
            headerSearchContainer.setManaged(true);
            searchField.setPromptText("Search SKU, Batch, or Item...");
            profileNameLabel.setText("Dr. Sarah Chen");
            profileRoleLabel.setText("CHIEF PHARMACIST");
        } else if ("InventoryDirectory".equals(viewName)) {
            headerTitle.setText("");
            headerSearchContainer.setVisible(true);
            headerSearchContainer.setManaged(true);
            searchField.setPromptText("Search inventory SKU or name...");
            profileNameLabel.setText("Dr. Sarah Chen");
            profileRoleLabel.setText("CHIEF PHARMACIST");
        } else if ("InventoryOrders".equals(viewName)) {
            headerTitle.setText("Orders & Procurement");
            headerSearchContainer.setVisible(true);
            headerSearchContainer.setManaged(true);
            searchField.setPromptText("Search orders, suppliers, or SKUs...");
            profileNameLabel.setText("Dr. Henderson");
            profileRoleLabel.setText("PROCUREMENT LEAD");
        } else {
            String title = viewName.replace("Inventory", "");
            headerTitle.setText(title);
            headerSearchContainer.setVisible(false);
            headerSearchContainer.setManaged(false);
            profileNameLabel.setText("Dr. Sarah Chen");
            profileRoleLabel.setText("CHIEF PHARMACIST");
        }
    }

    private void loadView(String viewName) {
        updateHeader(viewName);
        if (!viewCache.containsKey(viewName)) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/hospital/fxml/inventory/" + viewName + ".fxml"));
                Parent view = loader.load();
                viewCache.put(viewName, view);
            } catch (IOException e) {
                e.printStackTrace();
                System.err.println("Could not load inventory view: " + viewName);
                Label placeholder = new Label(viewName + " View (Not yet implemented)");
                placeholder.setStyle("-fx-font-size: 24; -fx-text-fill: #666;");
                contentArea.getChildren().clear();
                contentArea.getChildren().add(placeholder);
                return;
            }
        }
        contentArea.getChildren().clear();
        contentArea.getChildren().add(viewCache.get(viewName));
    }

    @FXML
    private void handleBackToHMS(MouseEvent event) {
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        currentStage.hide(); // Use hide() instead of close() so the stage can be shown again later
        if (mainHmsStage != null) {
            mainHmsStage.show();
        }
    }
    
    // Window Controls
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
    private void handleMinimize(MouseEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setIconified(true);
    }

    @FXML
    private void handleMaximize(MouseEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setMaximized(!stage.isMaximized());
    }

    @FXML
    private void handleClose(MouseEvent event) {
        handleBackToHMS(event); // Hides the inventory window and returns to HMS
    }
}
