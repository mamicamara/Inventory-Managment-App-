/**
 * The module includes information used for compiling the application.
 */
module com.gyp1.inventorymgtapp {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.gyp1.inventorymgtapp to javafx.fxml;
    exports com.gyp1.inventorymgtapp;
}