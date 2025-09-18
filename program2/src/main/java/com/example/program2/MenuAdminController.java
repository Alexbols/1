package com.example.program2;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class MenuAdminController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button BackID;

    @FXML
    private Button TableTicketsID;

    @FXML
    private Button TableUsersID;

    @FXML
    void Back(ActionEvent event) throws IOException {
        FXMLLoader fmxlLoader = new FXMLLoader(HelloApplication.class.getResource("menuadmin.fxml"));
        Stage stage1 = (Stage) BackID.getScene().getWindow();
        stage1.close();

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("authorization.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 310, 400);
        Stage stage2 = new Stage();
        stage2.setTitle("Авторизация");
        stage2.setScene(scene);
        stage2.show();
    }

    @FXML
    void Exit(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    void TableTickets(ActionEvent event) throws IOException{
        FXMLLoader fmxlLoader = new FXMLLoader(HelloApplication.class.getResource("menuadmin.fxml"));
        Stage stage1 = (Stage) BackID.getScene().getWindow();
        stage1.close();

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("tickettableadmin.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 791, 636);
        Stage stage2 = new Stage();
        stage2.setTitle("Таблица Билеты");
        stage2.setScene(scene);
        stage2.show();
    }

    @FXML
    void TableUsers(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert BackID != null : "fx:id=\"BackID\" was not injected: check your FXML file 'menuadmin.fxml'.";
        assert TableTicketsID != null : "fx:id=\"TableTicketsID\" was not injected: check your FXML file 'menuadmin.fxml'.";
        assert TableUsersID != null : "fx:id=\"TableUsersID\" was not injected: check your FXML file 'menuadmin.fxml'.";

    }

}
