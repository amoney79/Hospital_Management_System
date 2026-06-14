package com.hospital.inventory;

public class ServiceLog {
    private final String title;
    private final String technician;
    private final String description;
    private final String dateTime;

    public ServiceLog(String title, String technician, String description, String dateTime) {
        this.title = title;
        this.technician = technician;
        this.description = description;
        this.dateTime = dateTime;
    }

    public String getTitle() { return title; }
    public String getTechnician() { return technician; }
    public String getDescription() { return description; }
    public String getDateTime() { return dateTime; }
}
