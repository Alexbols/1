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

public class Registr {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button akk;

    @FXML
    private TextField login;

    @FXML
    private TextField parol;

    @FXML
    private Button zareg;

    @FXML
    void akk(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Регистрация");
        stage.show();


        FXMLLoader fmxlLoader = new FXMLLoader(HelloApplication.class.getResource("registr.fxml"));
        Stage stage1 = (Stage) akk.getScene().getWindow();
        stage1.close();

    }

    @FXML
    void zareg(ActionEvent event) {
        String userLogin = login.getText();
        String userPassword = parol.getText();


        if (userLogin.isEmpty() || userPassword.isEmpty() ) {
            showAlert("Ошибка", "Заполните все поля!");
            return;
        }

        // Проверка уникальности логина
        if (isLoginExists(userLogin)) {
            showAlert("Ошибка", "Администратор с таким логином уже существует.");
            return;
        }

        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement ps = connection.prepareStatement(
                     "INSERT INTO admin (login, parol ) VALUES (?, ?)")) {

            ps.setString(1, userLogin);
            ps.setString(2, userPassword);


            int affectedRows = ps.executeUpdate();
            if (affectedRows > 0) {
                showAlert("Успех", "Регистрация прошла успешно!");
            } else {
                showAlert("Ошибка", "Регистрация не удалась. Попробуйте позже.");
            }

        } catch (SQLException e) {
            showAlert("Ошибка БД", e.getMessage());
        }
    }

    // Метод для проверки существования логина
    private boolean isLoginExists(String userLogin) {
        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement ps = connection.prepareStatement("SELECT COUNT(*) FROM admin WHERE login = ?")) {
            ps.setString(1, userLogin);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0; // Если результат больше 0, значит логин существует
            }
        } catch (SQLException e) {
            showAlert("Ошибка БД", e.getMessage());
        }
        return false; // логин не существует
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }



    @FXML
    void initialize() {
        assert akk != null : "fx:id=\"akk\" was not injected: check your FXML file 'registr.fxml'.";
        assert login != null : "fx:id=\"login\" was not injected: check your FXML file 'registr.fxml'.";
        assert parol != null : "fx:id=\"parol\" was not injected: check your FXML file 'registr.fxml'.";
        assert zareg != null : "fx:id=\"zareg\" was not injected: check your FXML file 'registr.fxml'.";

    }

}
