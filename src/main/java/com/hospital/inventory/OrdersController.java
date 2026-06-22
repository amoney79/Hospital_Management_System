package com.hospital.inventory;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class OrdersController {
    
    @FXML private TableView<OrderItem> ordersTable;
    @FXML private TableColumn<OrderItem, String> colOrderId;
    @FXML private TableColumn<OrderItem, String> colSupplier;
    @FXML private TableColumn<OrderItem, Number> colSkus;
    @FXML private TableColumn<OrderItem, String> colTotalValue;
    @FXML private TableColumn<OrderItem, String> colStatus;
    @FXML private TableColumn<OrderItem, String> colExpectedDelivery;

    private ObservableList<OrderItem> masterData = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        setupTable();
        loadMockData();
    }

    private void setupTable() {
        colOrderId.setCellValueFactory(data -> data.getValue().orderIdProperty());
        colSupplier.setCellValueFactory(data -> data.getValue().supplierProperty());
        colSkus.setCellValueFactory(data -> data.getValue().skusProperty());
        colTotalValue.setCellValueFactory(data -> data.getValue().totalValueProperty());
        colStatus.setCellValueFactory(data -> data.getValue().statusProperty());
        colExpectedDelivery.setCellValueFactory(data -> data.getValue().expectedDeliveryProperty());
    }

    private void loadMockData() {
        masterData.addAll(
            new OrderItem("PO-2026-001", "Medline Industries", 14, "$12,450.00", "Pending Approval", "Jun 28, 2026"),
            new OrderItem("PO-2026-002", "Baxter Healthcare", 8, "$8,920.00", "Shipped", "Jun 25, 2026"),
            new OrderItem("PO-2026-003", "Pfizer Pharmaceuticals", 25, "$45,600.00", "Received", "Jun 20, 2026"),
            new OrderItem("PO-2026-004", "Johnson & Johnson", 12, "$18,500.00", "Draft", "N/A"),
            new OrderItem("PO-2026-005", "McKesson Corp", 40, "$57,030.00", "Pending Approval", "Jul 02, 2026")
        );
        ordersTable.setItems(masterData);
    }
}
