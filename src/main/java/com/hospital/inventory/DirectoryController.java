package com.hospital.inventory;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import org.kordamp.ikonli.javafx.FontIcon;

public class DirectoryController {

    @FXML private TableView<DirectoryItem> directoryTable;
    @FXML private TableColumn<DirectoryItem, String> colItemName;
    @FXML private TableColumn<DirectoryItem, String> colCategory;
    @FXML private TableColumn<DirectoryItem, String> colSku;
    @FXML private TableColumn<DirectoryItem, Number> colStockLevel;
    @FXML private TableColumn<DirectoryItem, String> colExpiration;
    @FXML private TableColumn<DirectoryItem, String> colActions;

    private ObservableList<DirectoryItem> masterData = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        setupTable();
        loadMockData();
    }

    private void setupTable() {
        // 1. Item Name Cell: render colored status dot, Name, and Subtitle
        colItemName.setCellValueFactory(data -> data.getValue().itemNameProperty());
        colItemName.setCellFactory(column -> new TableCell<>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setGraphic(null);
                } else {
                    DirectoryItem rowData = getTableView().getItems().get(getIndex());
                    HBox rootBox = new HBox(10);
                    rootBox.setAlignment(javafx.geometry.Pos.CENTER_LEFT);

                    // Circle status dot
                    Region dot = new Region();
                    dot.setPrefSize(8, 8);
                    dot.setMinSize(8, 8);
                    dot.setMaxSize(8, 8);
                    String status = rowData.getStatus();
                    if ("Low Stock".equalsIgnoreCase(status)) {
                        dot.setStyle("-fx-background-color: #ba1a1a; -fx-background-radius: 50%;"); // red
                    } else if ("Expiring Soon".equalsIgnoreCase(status)) {
                        dot.setStyle("-fx-background-color: #eab308; -fx-background-radius: 50%;"); // yellow
                    } else {
                        dot.setStyle("-fx-background-color: #16a34a; -fx-background-radius: 50%;"); // green
                    }

                    VBox textBox = new VBox(2);
                    Label nameLbl = new Label(rowData.getItemName());
                    nameLbl.setStyle("-fx-font-weight: bold; -fx-text-fill: #1e293b;");
                    Label subtitleLbl = new Label(rowData.getSubtitle());
                    subtitleLbl.setStyle("-fx-text-fill: #64748b; -fx-font-size: 11px;");
                    textBox.getChildren().addAll(nameLbl, subtitleLbl);

                    rootBox.getChildren().addAll(dot, textBox);
                    setGraphic(rootBox);
                }
            }
        });

        // 2. Category Cell: render category as a styled badge
        colCategory.setCellValueFactory(data -> data.getValue().categoryProperty());
        colCategory.setCellFactory(column -> new TableCell<>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setGraphic(null);
                } else {
                    Label badge = new Label(item.toUpperCase());
                    badge.getStyleClass().add("badge");
                    if ("surgical".equalsIgnoreCase(item)) {
                        badge.getStyleClass().add("badge-surgical");
                    } else if ("pharma".equalsIgnoreCase(item)) {
                        badge.getStyleClass().add("badge-pharma");
                    } else {
                        badge.getStyleClass().add("badge-consumable");
                    }
                    setGraphic(badge);
                }
            }
        });

        // 3. SKU Cell
        colSku.setCellValueFactory(data -> data.getValue().skuProperty());

        // 4. Stock Level Cell: render number with custom horizontal bar graph underneath
        colStockLevel.setCellValueFactory(data -> data.getValue().stockLevelProperty());
        colStockLevel.setCellFactory(column -> new TableCell<>() {
            @Override
            protected void updateItem(Number item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setGraphic(null);
                } else {
                    DirectoryItem rowData = getTableView().getItems().get(getIndex());
                    VBox box = new VBox(4);
                    box.setAlignment(javafx.geometry.Pos.CENTER_LEFT);

                    Label countLbl = new Label(String.format("%,d", item.intValue()));
                    countLbl.setStyle("-fx-font-weight: bold; -fx-text-fill: #1e293b;");
                    if ("Low Stock".equalsIgnoreCase(rowData.getStatus())) {
                        countLbl.setStyle("-fx-font-weight: bold; -fx-text-fill: #ba1a1a;");
                    }

                    // custom progress indicator bar
                    HBox barContainer = new HBox();
                    barContainer.getStyleClass().add("stock-bar-container");
                    barContainer.setMaxWidth(80);
                    
                    Region track = new Region();
                    track.getStyleClass().add("stock-bar-track");
                    HBox.setHgrow(track, Priority.ALWAYS);
                    
                    // colored bar representation of fullness
                    Region fill = new Region();
                    fill.setPrefHeight(4);
                    fill.setMinHeight(4);
                    
                    String status = rowData.getStatus();
                    if ("Low Stock".equalsIgnoreCase(status)) {
                        fill.getStyleClass().add("stock-bar-fill-red");
                        fill.setPrefWidth(20);
                    } else if (item.intValue() < 1000) {
                        fill.getStyleClass().add("stock-bar-fill-yellow");
                        fill.setPrefWidth(50);
                    } else {
                        fill.getStyleClass().add("stock-bar-fill-blue");
                        fill.setPrefWidth(80);
                    }
                    
                    StackPane progressPane = new StackPane(track, fill);
                    progressPane.setAlignment(javafx.geometry.Pos.CENTER_LEFT);
                    
                    box.getChildren().addAll(countLbl, progressPane);
                    setGraphic(box);
                }
            }
        });

        // 5. Expiration Cell: make text red for past or warning dates
        colExpiration.setCellValueFactory(data -> data.getValue().expirationProperty());
        colExpiration.setCellFactory(column -> new TableCell<>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                    setGraphic(null);
                } else {
                    Label lbl = new Label(item);
                    if (item.contains("2024") || item.contains("Jan")) {
                        lbl.setStyle("-fx-text-fill: #ba1a1a; -fx-font-weight: bold;"); // Expired/Warning
                    } else {
                        lbl.setStyle("-fx-text-fill: #334155;");
                    }
                    setGraphic(lbl);
                }
            }
        });

        // 6. Actions Cell: render Edit, Scan, Delete, or Reorder button
        colActions.setCellValueFactory(data -> data.getValue().statusProperty());
        colActions.setCellFactory(column -> new TableCell<>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setGraphic(null);
                } else {
                    DirectoryItem rowData = getTableView().getItems().get(getIndex());
                    HBox actionBox = new HBox(12);
                    actionBox.setAlignment(javafx.geometry.Pos.CENTER_LEFT);

                    if ("Low Stock".equalsIgnoreCase(item)) {
                        Button reorderBtn = new Button("REORDER");
                        reorderBtn.getStyleClass().addAll("btn", "btn-primary", "btn-sm");
                        reorderBtn.setStyle("-fx-font-size: 10px; -fx-padding: 4 8;");
                        
                        FontIcon dotsIcon = new FontIcon("fth-more-vertical");
                        dotsIcon.setIconColor(javafx.scene.paint.Color.web("#64748b"));
                        dotsIcon.setIconSize(14);
                        dotsIcon.setStyle("-fx-cursor: hand;");

                        actionBox.getChildren().addAll(reorderBtn, dotsIcon);
                    } else {
                        FontIcon editIcon = new FontIcon("fth-edit-2");
                        editIcon.setIconColor(javafx.scene.paint.Color.web("#64748b"));
                        editIcon.setIconSize(14);
                        editIcon.setStyle("-fx-cursor: hand;");

                        if ("SURGICAL".equalsIgnoreCase(rowData.getCategory())) {
                            FontIcon scanIcon = new FontIcon("fth-maximize"); // scan/barcode placeholder
                            scanIcon.setIconColor(javafx.scene.paint.Color.web("#64748b"));
                            scanIcon.setIconSize(14);
                            scanIcon.setStyle("-fx-cursor: hand;");

                            FontIcon deleteIcon = new FontIcon("fth-trash-2");
                            deleteIcon.setIconColor(javafx.scene.paint.Color.web("#ef4444"));
                            deleteIcon.setIconSize(14);
                            deleteIcon.setStyle("-fx-cursor: hand;");

                            actionBox.getChildren().addAll(editIcon, scanIcon, deleteIcon);
                        } else {
                            FontIcon dotsIcon = new FontIcon("fth-more-vertical");
                            dotsIcon.setIconColor(javafx.scene.paint.Color.web("#64748b"));
                            dotsIcon.setIconSize(14);
                            dotsIcon.setStyle("-fx-cursor: hand;");

                            actionBox.getChildren().addAll(editIcon, dotsIcon);
                        }
                    }
                    setGraphic(actionBox);
                }
            }
        });
    }

    private void loadMockData() {
        masterData.addAll(
            new DirectoryItem("Sterile Scalpel Blades #11", "Qty per Unit: 100pk", "SURGICAL", "SURG-882-X11", 1240, "Oct 12, 2026", "In Stock"),
            new DirectoryItem("Amoxicillin 500mg Caps", "Lot: AMX-4491", "PHARMA", "RX-AMX-500", 45, "Jan 05, 2024", "Low Stock"),
            new DirectoryItem("Latex-Free Exam Gloves (L)", "Box of 100", "CONSUMABLE", "GLV-LFX-992", 4500, "N/A", "In Stock"),
            new DirectoryItem("IV Administration Set", "Gravity Feed, 20 drops/ml", "SURGICAL", "IVS-SET-500", 210, "May 20, 2025", "In Stock")
        );
        directoryTable.setItems(masterData);
    }
}
