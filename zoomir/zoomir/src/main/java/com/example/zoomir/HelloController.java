package com.example.zoomir;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

import javax.swing.*;



public class HelloController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField login;

    @FXML
    private TextField parol;

    @FXML
    private Button prog;

    @FXML
    private Button razrab;

    @FXML
    private Button registr;

    @FXML
    private Button vixod;

    @FXML
    private Button vxod;
    private String message;

    @FXML
    void prog(ActionEvent event) {
        JOptionPane.showMessageDialog(null,"программа создана для зоомагазина-зоомир");
    }

    @FXML
    void razrab(ActionEvent event) {
JOptionPane.showMessageDialog(null,"Киселева юлия Исп-31");
    }

    @FXML
    void registr(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("registr.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Регистрация");
        stage.show();


        FXMLLoader fmxlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Stage stage1 = (Stage) registr.getScene().getWindow();
        stage1.close();
    }


    @FXML
    void vixod(ActionEvent event) {
System.exit(0);
    }

    @FXML
    void vxod(ActionEvent event) {
        String log = login.getText().trim();
        String password = parol.getText().trim();

// Валидация полей
        if (log.isEmpty() || password.isEmpty()) {
            showErrorMessage("Введите логин и пароль", "Ошибка авторизации");
            return;
        }

        try (Connection conn = DatabaseConnector.getConnection()) {
            String sql = "SELECT * FROM admin WHERE login = ? AND parol = ?";
            try (PreparedStatement pst = conn.prepareStatement(sql)) {
                pst.setString(1, log); // Используем переменную log
                pst.setString(2, password); // Здесь желательно использовать хеш пароля

                try (ResultSet rs = pst.executeQuery()) {
                    if (rs.next()) {
                        // Авторизация успешна
                        showInfoMessage("Добро пожаловать!", "Успех");
                        openMainWindow(log); // Открываем главное окно
                    } else {
                        showErrorMessage("Неверный логин или пароль", "Ошибка авторизации");
                    }
                }
            }
        } catch (SQLException e) {
            showErrorMessage("Ошибка БД: " + e.getMessage(), "Ошибка");
        } catch (Exception e) {
            showErrorMessage("Системная ошибка: " + e.getMessage(), "Ошибка");
        }
    }

    private void showErrorMessage(String message, String title) {
        // Проверяем, чтобы title не был null
        if (title == null || title.isEmpty()) {
            title = "Ошибка"; // Устанавливаем заголовок по умолчанию
        }

        JOptionPane.showMessageDialog(null, message, title, JOptionPane.ERROR_MESSAGE);
    }

    private void openMainWindow(String log) throws IOException {
        FXMLLoader fmxlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Stage stage1 = (Stage) vxod.getScene().getWindow();
        stage1.close();

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("menu.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 570, 500);
        stage1.setTitle("Меню");
        stage1.setScene(scene);

        stage1.show();
    }



    private void showInfoMessage(String message, String title) {
        JOptionPane.showMessageDialog(null, message, title, JOptionPane.INFORMATION_MESSAGE);
    }



    @FXML
    void initialize() {
        assert login != null : "fx:id=\"login\" was not injected: check your FXML file 'hello-view.fxml'.";
        assert parol != null : "fx:id=\"parol\" was not injected: check your FXML file 'hello-view.fxml'.";
        assert prog != null : "fx:id=\"prog\" was not injected: check your FXML file 'hello-view.fxml'.";
        assert razrab != null : "fx:id=\"razrab\" was not injected: check your FXML file 'hello-view.fxml'.";
        assert registr != null : "fx:id=\"registr\" was not injected: check your FXML file 'hello-view.fxml'.";
        assert vixod != null : "fx:id=\"vixod\" was not injected: check your FXML file 'hello-view.fxml'.";
        assert vxod != null : "fx:id=\"vxod\" was not injected: check your FXML file 'hello-view.fxml'.";

    }

}
