module com.hospital {
    requires javafx.controls;
    requires javafx.graphics;
    requires javafx.fxml;
    requires javafx.web;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.ikonli.feather;

    opens com.hospital to javafx.fxml;
    opens com.hospital.controllers to javafx.fxml;

    exports com.hospital;
    exports com.hospital.controllers;
}
