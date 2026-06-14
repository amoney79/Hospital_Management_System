package com.hospital.inventory;

import javafx.beans.property.*;

public class DirectoryItem {
    private final StringProperty itemName;
    private final StringProperty category;
    private final StringProperty sku;
    private final IntegerProperty stockLevel;
    private final StringProperty expiration;
    private final StringProperty status; // e.g., "In Stock", "Low Stock"

    public DirectoryItem(String itemName, String category, String sku, int stockLevel, String expiration, String status) {
        this.itemName = new SimpleStringProperty(itemName);
        this.category = new SimpleStringProperty(category);
        this.sku = new SimpleStringProperty(sku);
        this.stockLevel = new SimpleIntegerProperty(stockLevel);
        this.expiration = new SimpleStringProperty(expiration);
        this.status = new SimpleStringProperty(status);
    }

    public String getItemName() { return itemName.get(); }
    public StringProperty itemNameProperty() { return itemName; }

    public String getCategory() { return category.get(); }
    public StringProperty categoryProperty() { return category; }

    public String getSku() { return sku.get(); }
    public StringProperty skuProperty() { return sku; }

    public int getStockLevel() { return stockLevel.get(); }
    public IntegerProperty stockLevelProperty() { return stockLevel; }

    public String getExpiration() { return expiration.get(); }
    public StringProperty expirationProperty() { return expiration; }
    
    public String getStatus() { return status.get(); }
    public StringProperty statusProperty() { return status; }
}
