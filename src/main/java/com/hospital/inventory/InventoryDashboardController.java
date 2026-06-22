package com.hospital.inventory;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import org.kordamp.ikonli.javafx.FontIcon;

public class InventoryDashboardController {

    @FXML private Label totalInventoryLabel;
    @FXML private Label lowStockLabel;
    @FXML private Label pendingOrdersLabel;
    @FXML private ProgressBar storageProgress;
    @FXML private StackPane chartPlaceholder;

    // Bottom Table
    @FXML private TableView<ActivityItem> recentActivityTable;
    @FXML private TableColumn<ActivityItem, String> colSkuItemName;
    @FXML private TableColumn<ActivityItem, String> colActionType;
    @FXML private TableColumn<ActivityItem, String> colBatchNo;
    @FXML private TableColumn<ActivityItem, Number> colQuantity;
    @FXML private TableColumn<ActivityItem, String> colStaffEntity;
    @FXML private TableColumn<ActivityItem, String> colStatus;

    private ObservableList<ActivityItem> activityData = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // Set header stats
        totalInventoryLabel.setText("12,842");
        lowStockLabel.setText("18");
        pendingOrdersLabel.setText("07");
        storageProgress.setProgress(0.84);

        // Load Bar Chart
        loadBarChart();

        // Setup Recent Activity Table
        setupActivityTable();
        loadActivityMockData();
    }

    private void loadBarChart() {
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis(0, 10000, 2500);
        
        BarChart<String, Number> barChart = new BarChart<>(xAxis, yAxis);
        barChart.setTitle(null);
        barChart.setLegendVisible(true);
        barChart.setAnimated(false);
        barChart.setBarGap(3);
        barChart.setCategoryGap(20);

        XYChart.Series<String, Number> usageSeries = new XYChart.Series<>();
        usageSeries.setName("Usage Volume");
        usageSeries.getData().add(new XYChart.Data<>("Jan", 4800));
        usageSeries.getData().add(new XYChart.Data<>("Feb", 6500));
        usageSeries.getData().add(new XYChart.Data<>("Mar", 5600));
        usageSeries.getData().add(new XYChart.Data<>("Apr", 7200));
        usageSeries.getData().add(new XYChart.Data<>("May", 6300));
        usageSeries.getData().add(new XYChart.Data<>("Jun", 6400));

        XYChart.Series<String, Number> deliverySeries = new XYChart.Series<>();
        deliverySeries.setName("Delivery Volume");
        deliverySeries.getData().add(new XYChart.Data<>("Jan", 5800));
        deliverySeries.getData().add(new XYChart.Data<>("Feb", 7400));
        deliverySeries.getData().add(new XYChart.Data<>("Mar", 9100));
        deliverySeries.getData().add(new XYChart.Data<>("Apr", 8300));
        deliverySeries.getData().add(new XYChart.Data<>("May", 6700));
        deliverySeries.getData().add(new XYChart.Data<>("Jun", 9600));

        ObservableList<XYChart.Series<String, Number>> chartData = FXCollections.observableArrayList();
        chartData.add(usageSeries);
        chartData.add(deliverySeries);
        barChart.setData(chartData);
        chartPlaceholder.getChildren().setAll(barChart);
    }

    private void setupActivityTable() {
        // 1. SKU / Item Name Custom Cell
        colSkuItemName.setCellValueFactory(data -> data.getValue().itemNameProperty());
        colSkuItemName.setCellFactory(column -> new TableCell<>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setGraphic(null);
                } else {
                    ActivityItem rowData = getTableView().getItems().get(getIndex());
                    VBox box = new VBox(2);
                    Label nameLbl = new Label(rowData.getItemName());
                    nameLbl.setStyle("-fx-font-weight: bold; -fx-text-fill: #1e293b;");
                    Label skuLbl = new Label(rowData.getSku());
                    skuLbl.setStyle("-fx-text-fill: #64748b; -fx-font-size: 11px;");
                    box.getChildren().addAll(nameLbl, skuLbl);
                    setGraphic(box);
                }
            }
        });

        // 2. Action Type Custom Cell (with icon)
        colActionType.setCellValueFactory(data -> data.getValue().actionTypeProperty());
        colActionType.setCellFactory(column -> new TableCell<>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setGraphic(null);
                } else {
                    HBox box = new HBox(6);
                    box.setAlignment(javafx.geometry.Pos.CENTER_LEFT);
                    FontIcon icon;
                    if (item.contains("Inbound")) {
                        icon = new FontIcon("fth-arrow-down-left");
                        icon.setIconColor(javafx.scene.paint.Color.web("#00478d"));
                    } else {
                        icon = new FontIcon("fth-arrow-up-right");
                        icon.setIconColor(javafx.scene.paint.Color.web("#e11d48"));
                    }
                    icon.setIconSize(12);
                    Label lbl = new Label(item);
                    lbl.setStyle("-fx-text-fill: #334155;");
                    box.getChildren().addAll(icon, lbl);
                    setGraphic(box);
                }
            }
        });

        // 3. Batch Column
        colBatchNo.setCellValueFactory(data -> data.getValue().batchNoProperty());

        // 4. Quantity Column
        colQuantity.setCellValueFactory(data -> data.getValue().quantityProperty());
        colQuantity.setCellFactory(column -> new TableCell<>() {
            @Override
            protected void updateItem(Number item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                    setGraphic(null);
                } else {
                    int val = item.intValue();
                    Label lbl = new Label((val > 0 ? "+" : "") + val);
                    if (val > 0) {
                        lbl.setStyle("-fx-text-fill: #00478d; -fx-font-weight: bold;");
                    } else {
                        lbl.setStyle("-fx-text-fill: #ba1a1a; -fx-font-weight: bold;");
                    }
                    setGraphic(lbl);
                }
            }
        });

        // 5. Staff Column
        colStaffEntity.setCellValueFactory(data -> data.getValue().staffEntityProperty());

        // 6. Status Badge Column
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
                    if ("verified".equalsIgnoreCase(item)) {
                        badge.getStyleClass().add("badge-success");
                    } else {
                        badge.getStyleClass().add("badge-neutral");
                    }
                    setGraphic(badge);
                }
            }
        });
    }

    private void loadActivityMockData() {
        activityData.addAll(
            new ActivityItem("Saline Bag 500ml", "#SB-500-A2", "Stock Inbound", "BTCH-2291-X", 500, "Logistics Hub A", "Verified"),
            new ActivityItem("Amoxicillin 500mg Caps", "#RX-AMX-500", "Stock Inbound", "BTCH-4491-Y", 1200, "Main Pharmacy", "Verified"),
            new ActivityItem("Sterile Scalpel Blades #11", "#SURG-882-X11", "Stock Outbound", "BTCH-8821-A", -50, "Operating Room B", "Verified"),
            new ActivityItem("Latex Gloves Large", "#GLV-LFX-992", "Stock Outbound", "BTCH-9092-B", -300, "Emergency Dept", "Verified")
        );
        recentActivityTable.setItems(activityData);
    }
}