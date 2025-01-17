module org.example {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires java.sql;
    requires junit;
    requires org.junit.jupiter.api;


    opens org.example to javafx.fxml;
    exports org.example;
}