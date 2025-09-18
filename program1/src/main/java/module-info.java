module com.example.program1 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.program1 to javafx.fxml;
    exports com.example.program1;
}