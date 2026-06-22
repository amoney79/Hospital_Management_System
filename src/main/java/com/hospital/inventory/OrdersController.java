package com.hospital.inventory;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class OrdersController {
    
    @FXML private TableView<OrderItem> ordersTable;
    @FXML private TableColumn<OrderItem, String> colOrderId;
    @FXML private TableColumn<OrderItem, String> colSupplier;
    @FXML private TableColumn<OrderItem, Number> colSkus;
    @FXML private TableColumn<OrderItem, String> colTotalValue;
    @FXML private TableColumn<OrderItem, String> colStatus;
    @FXML private TableColumn<OrderItem, String> colExpectedDelivery;
    @FXML private TableColumn<OrderItem, String> colActions;

    private ObservableList<OrderItem> masterData = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        setupTable();
        loadMockData();
    }

    private void setupTable() {
        // 1. Order ID Hyperlink cell style
        colOrderId.setCellValueFactory(data -> data.getValue().orderIdProperty());
        colOrderId.setCellFactory(column -> new TableCell<>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setGraphic(null);
                } else {
                    Hyperlink link = new Hyperlink(item);
                    link.setStyle("-fx-text-fill: #00478d; -fx-font-weight: bold; -fx-underline: true; -fx-padding: 0;");
                    setGraphic(link);
                }
            }
        });

        colSupplier.setCellValueFactory(data -> data.getValue().supplierProperty());

        // 2. Skus formatted as "XX Items"
        colSkus.setCellValueFactory(data -> data.getValue().skusProperty());
        colSkus.setCellFactory(column -> new TableCell<>() {
            @Override
            protected void updateItem(Number item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    int val = item.intValue();
                    setText(String.format("%02d Items", val));
                }
            }
        });

        colTotalValue.setCellValueFactory(data -> data.getValue().totalValueProperty());

        // 3. Status Badge cell style
        colStatus.setCellValueFactory(data -> data.getValue().statusProperty());
        colStatus.setCellFactory(column -> new TableCell<>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setGraphic(null);
                } else {
                    Label badge = new Label(item.toUpperCase());
                    badge.getStyleClass().add("badge");
                    if ("sent".equalsIgnoreCase(item)) {
                        badge.getStyleClass().add("badge-sent");
                    } else if ("shipped".equalsIgnoreCase(item)) {
                        badge.getStyleClass().add("badge-shipped");
                    } else if ("draft".equalsIgnoreCase(item)) {
                        badge.getStyleClass().add("badge-draft");
                    } else if ("received".equalsIgnoreCase(item)) {
                        badge.getStyleClass().add("badge-received");
                    } else {
                        badge.getStyleClass().add("badge-neutral");
                    }
                    setGraphic(badge);
                }
            }
        });

        colExpectedDelivery.setCellValueFactory(data -> data.getValue().expectedDeliveryProperty());

        // 4. Actions cell style (dynamic links depending on status)
        colActions.setCellValueFactory(data -> data.getValue().statusProperty());
        colActions.setCellFactory(column -> new TableCell<>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setGraphic(null);
                } else {
                    Hyperlink actionLink = new Hyperlink();
                    actionLink.setStyle("-fx-text-fill: #00478d; -fx-font-weight: bold; -fx-underline: true; -fx-padding: 0;");
                    
                    if ("sent".equalsIgnoreCase(item) || "shipped".equalsIgnoreCase(item)) {
                        actionLink.setText("Track");
                    } else if ("draft".equalsIgnoreCase(item)) {
                        actionLink.setText("Edit");
                    } else if ("received".equalsIgnoreCase(item)) {
                        actionLink.setText("View Receipt");
                    } else {
                        actionLink.setText("Open");
                    }
                    setGraphic(actionLink);
                }
            }
        });
    }

    private void loadMockData() {
        masterData.addAll(
            new OrderItem("PO-2023-9841", "Global Pharma Solutions", 12, "$4,250.00", "Sent", "Oct 28, 2023"),
            new OrderItem("PO-2023-9839", "MedTech Supplies Inc.", 5, "$12,400.00", "Shipped", "Oct 24, 2023"),
            new OrderItem("PO-2023-9838", "SterileClean Logistics", 2, "$890.00", "Draft", "--"),
            new OrderItem("PO-2023-9835", "BioLab Reagents", 8, "$3,120.00", "Received", "Oct 21, 2023")
        );
        ordersTable.setItems(masterData);
    }
}
