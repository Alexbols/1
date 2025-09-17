module com.example.zoomir {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;


    opens com.example.zoomir to javafx.fxml;
    exports com.example.zoomir;
}