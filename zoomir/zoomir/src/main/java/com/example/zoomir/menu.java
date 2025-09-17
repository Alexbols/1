package com.example.zoomir;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class menu {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button nazad;

    @FXML
    private Button otchet;

    @FXML
    private Button tovar;

    @FXML
    private Button zakaz;

    @FXML
    void nazad(ActionEvent event) throws IOException {
        FXMLLoader fmxlLoader = new FXMLLoader(HelloApplication.class.getResource("menu.fxml"));
        Stage stage1 = (Stage) nazad.getScene().getWindow();
        stage1.close();

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 603, 403);
        Stage stage2 = new Stage();
        stage2.setTitle("меню менеджера");
        stage2.setScene(scene);
        stage2.show();
    }


    @FXML
    void otchet(ActionEvent event) throws IOException {
        FXMLLoader fmxlLoader = new FXMLLoader(HelloApplication.class.getResource("menu.fxml"));
        Stage stage1 = (Stage) otchet.getScene().getWindow();
        stage1.close();

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("polzovateli.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 603, 403);
        Stage stage2 = new Stage();
        stage2.setTitle("Управление товарами");
        stage2.setScene(scene);
        stage2.show();
    }


    @FXML
    void tovar(ActionEvent event) throws IOException {
        FXMLLoader fmxlLoader = new FXMLLoader(HelloApplication.class.getResource("menu.fxml"));
        Stage stage1 = (Stage) tovar.getScene().getWindow();
        stage1.close();

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("tovar.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 603, 403);
        Stage stage2 = new Stage();
        stage2.setTitle("Управление товарами");
        stage2.setScene(scene);
        stage2.show();

    }


    @FXML
    void zakaz(ActionEvent event) throws IOException {
        FXMLLoader fmxlLoader = new FXMLLoader(HelloApplication.class.getResource("menu.fxml"));
        Stage stage1 = (Stage) zakaz.getScene().getWindow();
        stage1.close();

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("zakaz.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 603, 403);
        Stage stage2 = new Stage();
        stage2.setTitle("Управление заказами");
        stage2.setScene(scene);
        stage2.show();

    }

    @FXML
    void initialize() {
        assert nazad != null : "fx:id=\"nazad\" was not injected: check your FXML file 'menu.fxml'.";
        assert otchet != null : "fx:id=\"otchet\" was not injected: check your FXML file 'menu.fxml'.";
        assert tovar != null : "fx:id=\"tovar\" was not injected: check your FXML file 'menu.fxml'.";
        assert zakaz != null : "fx:id=\"zakaz\" was not injected: check your FXML file 'menu.fxml'.";

    }

}
