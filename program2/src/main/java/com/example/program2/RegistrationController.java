package com.example.program2;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.swing.*;

public class RegistrationController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button BackID;

    @FXML
    private TextField LoginField;

    @FXML
    private Button LoginID;

    @FXML
    private PasswordField PasswordField;

    @FXML
    private CheckBox ShowPassword;

    @FXML
    private TextField ShowPasswordField;

    Connection conn1 = null; //Конектор равен нулю
    PreparedStatement pst1 = null;
    ResultSet rs = null;

    @FXML
    void AboutAuthor(ActionEvent event) {

    }

    @FXML
    void Back(ActionEvent event) throws IOException {
        FXMLLoader fmxlLoader = new FXMLLoader(HelloApplication.class.getResource("registration.fxml"));
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
    void Login(ActionEvent event) {
        String Log = LoginField.getText().toString();
        String Pass = PasswordField.getText().toString();

        try {
            conn1 = Connect.ConnectDb();

            // Проверка существования пользователя
            String checkSql = "SELECT COUNT(*) FROM UsersATS WHERE Login_user = ?";
            pst1 = conn1.prepareStatement(checkSql);
            pst1.setString(1, Log);
            rs = pst1.executeQuery();

            if (rs.next() && rs.getInt(1) > 0) {
                JOptionPane.showMessageDialog(null, "Пользователь с таким логином уже существует!");
            } else {
                // Вставка нового пользователя
                String insertSql = "INSERT INTO UsersATS (Login_user, Pass_user) VALUES (?, ?)";
                pst1 = conn1.prepareStatement(insertSql);
                pst1.setString(1, Log);
                pst1.setString(2, Pass);
                pst1.executeUpdate();
                JOptionPane.showMessageDialog(null, "Пользователь зарегистрирован!");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);

        }
    }

    @FXML
    void initialize() {
        assert BackID != null : "fx:id=\"BackID\" was not injected: check your FXML file 'registration.fxml'.";
        assert LoginField != null : "fx:id=\"LoginField\" was not injected: check your FXML file 'registration.fxml'.";
        assert LoginID != null : "fx:id=\"LoginID\" was not injected: check your FXML file 'registration.fxml'.";
        assert PasswordField != null : "fx:id=\"PasswordField\" was not injected: check your FXML file 'registration.fxml'.";
        assert ShowPassword != null : "fx:id=\"ShowPassword\" was not injected: check your FXML file 'registration.fxml'.";
        assert ShowPasswordField != null : "fx:id=\"ShowPasswordField\" was not injected: check your FXML file 'registration.fxml'.";

    }

}
