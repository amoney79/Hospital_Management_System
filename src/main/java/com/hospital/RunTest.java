package com.hospital;
import javafx.fxml.FXMLLoader;

public class RunTest {
    public static void main(String[] args) {
        try {
            System.out.println("Testing Settings...");
            FXMLLoader loader = new FXMLLoader(RunTest.class.getResource("/com/hospital/fxml/Settings.fxml"));
            loader.load();
            System.out.println("Settings OK!");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Cause:");
            if (e.getCause() != null) e.getCause().printStackTrace();
        }
        try {
            System.out.println("Testing Inventory...");
            new FXMLLoader(RunTest.class.getResource("/com/hospital/fxml/Inventory.fxml"));
            System.out.println("Inventory OK!");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Cause:");
            if (e.getCause() != null) e.getCause().printStackTrace();
        }
    }
}
