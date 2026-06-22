package com.hospital.inventory;

import javafx.beans.property.*;

public class ActivityItem {
    private final StringProperty itemName;
    private final StringProperty sku;
    private final StringProperty actionType;
    private final StringProperty batchNo;
    private final IntegerProperty quantity;
    private final StringProperty staffEntity;
    private final StringProperty status;

    public ActivityItem(String itemName, String sku, String actionType, String batchNo, int quantity, String staffEntity, String status) {
        this.itemName = new SimpleStringProperty(itemName);
        this.sku = new SimpleStringProperty(sku);
        this.actionType = new SimpleStringProperty(actionType);
        this.batchNo = new SimpleStringProperty(batchNo);
        this.quantity = new SimpleIntegerProperty(quantity);
        this.staffEntity = new SimpleStringProperty(staffEntity);
        this.status = new SimpleStringProperty(status);
    }

    public String getItemName() { return itemName.get(); }
    public StringProperty itemNameProperty() { return itemName; }

    public String getSku() { return sku.get(); }
    public StringProperty skuProperty() { return sku; }

    public String getActionType() { return actionType.get(); }
    public StringProperty actionTypeProperty() { return actionType; }

    public String getBatchNo() { return batchNo.get(); }
    public StringProperty batchNoProperty() { return batchNo; }

    public int getQuantity() { return quantity.get(); }
    public IntegerProperty quantityProperty() { return quantity; }

    public String getStaffEntity() { return staffEntity.get(); }
    public StringProperty staffEntityProperty() { return staffEntity; }

    public String getStatus() { return status.get(); }
    public StringProperty statusProperty() { return status; }
}
