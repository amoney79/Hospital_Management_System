package com.hospital.inventory;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class DirectoryController {

    @FXML private TableView<DirectoryItem> directoryTable;
    @FXML private TableColumn<DirectoryItem, String> colItemName;
    @FXML private TableColumn<DirectoryItem, String> colCategory;
    @FXML private TableColumn<DirectoryItem, String> colSku;
    @FXML private TableColumn<DirectoryItem, Number> colStockLevel;
    @FXML private TableColumn<DirectoryItem, String> colExpiration;

    private ObservableList<DirectoryItem> masterData = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        setupTable();
        loadMockData();
    }

    private void setupTable() {
        colItemName.setCellValueFactory(data -> data.getValue().itemNameProperty());
        colCategory.setCellValueFactory(data -> data.getValue().categoryProperty());
        colSku.setCellValueFactory(data -> data.getValue().skuProperty());
        colStockLevel.setCellValueFactory(data -> data.getValue().stockLevelProperty());
        colExpiration.setCellValueFactory(data -> data.getValue().expirationProperty());
        
        // Let CSS handle the row styling based on status or we can add a row factory later.
    }

    private void loadMockData() {
        masterData.addAll(
            new DirectoryItem("Sterile Scalpel Blades #11", "SURGICAL", "SURG-882-X11", 1240, "Oct 12, 2026", "In Stock"),
            new DirectoryItem("Amoxicillin 500mg Caps", "PHARMA", "RX-AMX-500", 45, "Jan 05, 2024", "Low Stock"),
            new DirectoryItem("Latex-Free Exam Gloves (L)", "CONSUMABLE", "GLV-LFX-992", 4500, "N/A", "In Stock"),
            new DirectoryItem("IV Administration Set", "SURGICAL", "IVS-SET-500", 210, "May 20, 2025", "In Stock")
        );
        directoryTable.setItems(masterData);
    }
}
