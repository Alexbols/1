package com.example.program2;

import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.sql.Connection;

import javax.swing.*;

public class AuthorizationController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField LoginField;

    @FXML
    private Button LoginID;

    @FXML
    private PasswordField PasswordField;

    @FXML
    private Button RegistrationID;

    @FXML
    private CheckBox ShowPassword;

    @FXML
    private TextField ShowPasswordField;

    Connection conn1 = null; //Конектор равен нулю
    ResultSet rs = null;
    ResultSet rs2 = null;
    PreparedStatement pst1 = null;
    PreparedStatement pst2 = null;

    @FXML
    void AboutAuthor(ActionEvent event) {

    }

    @FXML
    void Exit(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    void Login(ActionEvent event) {
        try {
            conn1 = Connect.ConnectDb();
            String sql = "SELECT * FROM UsersATS WHERE Login_user = ? AND Pass_user = ?";
            pst1 = conn1.prepareStatement(sql);
            pst1.setString(1, LoginField.getText());
            pst1.setString(2, PasswordField.getText());
            rs = pst1.executeQuery();
            if (rs.next()){
                try {
                    FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("menuadmin.fxml"));
                    Scene scene = new Scene(fxmlLoader.load(), 600, 400);
                    Stage stage = new Stage();
                    stage.setTitle("Меню");
                    stage.setScene(scene);
                    stage.show();

                    Stage currentStage = (Stage) LoginID.getScene().getWindow();
                    currentStage.close();
                } catch (IOException e) {
                    JOptionPane.showMessageDialog(null, e);
                }
            }else if (rs.next()) {
                    JOptionPane.showMessageDialog(null, "Данные введены верно.");
                    try {
                        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("tickettableuser.fxml"));
                        Scene scene = new Scene(fxmlLoader.load(), 791, 636);
                        Stage stage = new Stage();
                        stage.setTitle("Главное меню");
                        stage.setScene(scene);
                        stage.show();

                        Stage currentStage = (Stage) LoginID.getScene().getWindow();
                        currentStage.close();
                    } catch (IOException e) {
                        JOptionPane.showMessageDialog(null, e);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Данные введены неверно.");
                }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    @FXML
    void Registration(ActionEvent event) throws IOException{
        FXMLLoader fmxlLoader = new FXMLLoader(HelloApplication.class.getResource("authorization.fxml"));
        Stage stage1 = (Stage) RegistrationID.getScene().getWindow();
        stage1.close();

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("registration.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 310, 400);
        Stage stage2 = new Stage();
        stage2.setTitle("Регистрация");
        stage2.setScene(scene);
        stage2.show();
    }

    @FXML
    void initialize() {
        assert LoginField != null : "fx:id=\"LoginField\" was not injected: check your FXML file 'authorization.fxml'.";
        assert LoginID != null : "fx:id=\"LoginID\" was not injected: check your FXML file 'authorization.fxml'.";
        assert PasswordField != null : "fx:id=\"PasswordField\" was not injected: check your FXML file 'authorization.fxml'.";
        assert RegistrationID != null : "fx:id=\"RegistrationID\" was not injected: check your FXML file 'authorization.fxml'.";
        assert ShowPassword != null : "fx:id=\"ShowPassword\" was not injected: check your FXML file 'authorization.fxml'.";
        assert ShowPasswordField != null : "fx:id=\"ShowPasswordField\" was not injected: check your FXML file 'authorization.fxml'.";

    }
}