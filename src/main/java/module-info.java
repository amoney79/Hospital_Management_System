module com.hospital {
    requires transitive javafx.controls;
    requires transitive javafx.graphics;
    requires javafx.fxml;
    requires javafx.web;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.ikonli.feather;

    opens com.hospital to javafx.fxml;
    opens com.hospital.controllers to javafx.fxml;
    opens com.hospital.inventory to javafx.fxml;

    exports com.hospital;
    exports com.hospital.controllers;
    exports com.hospital.inventory;
}
