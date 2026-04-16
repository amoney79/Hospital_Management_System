package com.hospital.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

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
    public void initialize() {
        loadView("Dashboard");
        setActiveNav(navDashboard);
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
        
        setActiveNav(source);
    }

    private void setActiveNav(HBox activeNav) {
        HBox[] navs = {navDashboard, navPatients, navDoctors, navAppointments, navRecords, navTransactions};
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
}
