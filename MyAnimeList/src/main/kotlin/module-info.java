module com.example.myanimelist {
    requires javafx.controls;
    requires javafx.fxml;
    requires kotlin.stdlib;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    exports com.example.myanimelist;
    exports com.example.myanimelist.controllers;
}