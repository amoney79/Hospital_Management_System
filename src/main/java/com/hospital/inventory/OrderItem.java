package com.hospital.inventory;

import javafx.beans.property.*;

public class OrderItem {
    private final StringProperty orderId;
    private final StringProperty supplier;
    private final IntegerProperty skus;
    private final StringProperty totalValue;
    private final StringProperty status;
    private final StringProperty expectedDelivery;

    public OrderItem(String orderId, String supplier, int skus, String totalValue, String status, String expectedDelivery) {
        this.orderId = new SimpleStringProperty(orderId);
        this.supplier = new SimpleStringProperty(supplier);
        this.skus = new SimpleIntegerProperty(skus);
        this.totalValue = new SimpleStringProperty(totalValue);
        this.status = new SimpleStringProperty(status);
        this.expectedDelivery = new SimpleStringProperty(expectedDelivery);
    }

    public String getOrderId() { return orderId.get(); }
    public StringProperty orderIdProperty() { return orderId; }

    public String getSupplier() { return supplier.get(); }
    public StringProperty supplierProperty() { return supplier; }

    public int getSkus() { return skus.get(); }
    public IntegerProperty skusProperty() { return skus; }

    public String getTotalValue() { return totalValue.get(); }
    public StringProperty totalValueProperty() { return totalValue; }

    public String getStatus() { return status.get(); }
    public StringProperty statusProperty() { return status; }

    public String getExpectedDelivery() { return expectedDelivery.get(); }
    public StringProperty expectedDeliveryProperty() { return expectedDelivery; }
}
