package com.hospital.inventory;

import java.util.ArrayList;
import java.util.List;

public class Asset {
    private final String name;
    private final String id;
    private final String location;
    private final String status;
    private final String lastService;
    private final String calibrationDue;
    private final String details;
    private final List<ServiceLog> serviceHistory;

    public Asset(String name, String id, String location, String status, String lastService, String calibrationDue, String details) {
        this.name = name;
        this.id = id;
        this.location = location;
        this.status = status;
        this.lastService = lastService;
        this.calibrationDue = calibrationDue;
        this.details = details;
        this.serviceHistory = new ArrayList<>();
    }

    public String getName() { return name; }
    public String getId() { return id; }
    public String getLocation() { return location; }
    public String getStatus() { return status; }
    public String getLastService() { return lastService; }
    public String getCalibrationDue() { return calibrationDue; }
    public String getDetails() { return details; }
    public List<ServiceLog> getServiceHistory() { return serviceHistory; }

    public void addServiceLog(ServiceLog log) {
        serviceHistory.add(log);
    }

    public void addServiceLog(int index, ServiceLog log) {
        serviceHistory.add(index, log);
    }
}
