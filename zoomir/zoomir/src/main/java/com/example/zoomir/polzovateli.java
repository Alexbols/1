package com.example.zoomir;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

import static com.example.zoomir.DatabaseConnector.getsetpolzObservableList;

public class polzovateli {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button dob;

    @FXML
    private TextField email;

    @FXML
    private TableColumn<getsetpolz, String> emailtab;

    @FXML
    private TextField id;

    @FXML
    private TableColumn<getsetpolz, Integer> idtab;

    @FXML
    private TextField imya;

    @FXML
    private TableColumn<getsetpolz, String> imyatab;

    @FXML
    private Button izm;

    @FXML
    private Button nazad;

    @FXML
    private Button obn;

    @FXML
    private TextField parol;

    @FXML
    private TableColumn<getsetpolz, String> paroltab;

    @FXML
    private TextField poisk;

    @FXML
    private TableView<getsetpolz> tabl;

    @FXML
    private Button ydal;


    ObservableList<getsetpolz> listBAP; //Название массива
    Connection conn1 = null; //Конектор равен нулю
    PreparedStatement pst1 = null;

    @FXML
    void dob(ActionEvent event) {
        Connection conn1 = DatabaseConnector.getConnection(); // Получаем соединение с базой данных

        String sql = "INSERT INTO users (id, имя, пароль, email) VALUES (?, ?, ?, ?)";

        PreparedStatement pst1 = null;

        try {
            // Проверка на пустые значения
            if (id.getText().trim().isEmpty() || imya.getText().trim().isEmpty() || parol.getText().trim().isEmpty() || email.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Пожалуйста, заполните все поля!");
                return; // Выходим из метода, если какие-либо поля пустые
            }

            pst1 = conn1.prepareStatement(sql); // Подготовка SQL-запроса
            pst1.setInt(1, Integer.parseInt(id.getText().trim())); // Установка значения id
            pst1.setString(2, imya.getText().trim()); // Установка имени
            pst1.setString(3, parol.getText().trim()); // Установка пароля
            pst1.setString(4, email.getText().trim()); // Установка email


            int rowsAffected = pst1.executeUpdate(); // Выполнение запроса
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "Пользователь добавлен успешно!"); // Успешное добавление
                UpdateTable(); // Обновление таблицы
            }

        } catch (SQLException e) {
            e.printStackTrace(); // Печатаем ошибку в консоль
            JOptionPane.showMessageDialog(null, "Ошибка при добавлении Пользователя: " + e.getMessage()); // Сообщаем об ошибке
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Пожалуйста, введите числовое значение для ID и количества!"); // Сообщаем о неверном вводе
        } finally {
            // Закрываем ресурсы
            try {
                if (pst1 != null) pst1.close();
                if (conn1 != null) conn1.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }


    private void UpdateTable() {
        idtab.setCellValueFactory(new PropertyValueFactory<>("id"));
        imyatab.setCellValueFactory(new PropertyValueFactory<>("имя"));
        paroltab.setCellValueFactory(new PropertyValueFactory<>("пароль"));
        emailtab.setCellValueFactory(new PropertyValueFactory<>("email"));
        listBAP = getsetpolzObservableList();
        tabl.setItems(listBAP);
    }

    void search_user() {
        idtab.setCellValueFactory(new PropertyValueFactory<>("id"));
        imyatab.setCellValueFactory(new PropertyValueFactory<>("имя"));
        paroltab.setCellValueFactory(new PropertyValueFactory<>("пароль"));
        emailtab.setCellValueFactory(new PropertyValueFactory<>("email"));

        FilteredList<getsetpolz> filteredData = new FilteredList<>(listBAP, b -> true);
        poisk.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(person -> {
                // Если поле поиска пустое, показываем все элементы
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                // Фильтруем только по ID
                return String.valueOf(person.getId()).toLowerCase().contains(lowerCaseFilter);
            });
        });

        SortedList<getsetpolz> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tabl.comparatorProperty());
        tabl.setItems(sortedData);
    }

    @FXML
    void izm(ActionEvent event) {
        try {
            conn1 = DatabaseConnector.getConnection();
            String value1 = id.getText();
            String value2 = imya.getText();
            String value3 = parol.getText();
            String value4 = email.getText();
            // Проверяем, не является ли какое-либо значение пустым
            if (value1.isEmpty() || value2.isEmpty() || value3.isEmpty() || value4.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Все поля должны быть заполнены.", "Ошибка", JOptionPane.ERROR_MESSAGE);
                return; // Выходим из метода, если есть пустота
            }


            String sql = "UPDATE users SET id= '" + value1 + "',имя= '" + value2 + "',пароль= '"+ value3 + "',email='" + value4 + "'where id='" + value1 + "' ";

            pst1 = conn1.prepareStatement(sql);
            pst1.execute();
            JOptionPane.showMessageDialog(null, "Изменено");

            UpdateTable();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    @FXML
    void nazad(ActionEvent event) throws IOException {
        FXMLLoader fmxlLoader = new FXMLLoader(HelloApplication.class.getResource("polzovateli.fxml"));
        Stage stage1 = (Stage) nazad.getScene().getWindow();
        stage1.close();

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("menu.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 603, 403);
        Stage stage2 = new Stage();
        stage2.setTitle("Меню администратора");
        stage2.setScene(scene);
        stage2.show();

    }

    @FXML
    void obn(ActionEvent event) {
        idtab.setCellValueFactory(new PropertyValueFactory<>("id"));
        imyatab.setCellValueFactory(new PropertyValueFactory<>("имя"));
        paroltab.setCellValueFactory(new PropertyValueFactory<>("пароль"));
        emailtab.setCellValueFactory(new PropertyValueFactory<>("email"));
        listBAP = getsetpolzObservableList();
        tabl.setItems(listBAP);
    }

    @FXML
    void ydal(ActionEvent event) {
        conn1 = DatabaseConnector.getConnection();

        // Получаем ID из текстового поля
        String idToDelete = id.getText();

        // Проверяем, что ID не пустой
        if (idToDelete.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Введите ID для удаления.", "Ошибка", JOptionPane.WARNING_MESSAGE);
            return; // Выходим, если ID пустой
        }

        String sql = "DELETE FROM users WHERE id = ?";

        try {
            assert conn1 != null;

            // Подготовка SQL-запроса
            pst1 = conn1.prepareStatement(sql);
            pst1.setString(1, idToDelete);

            // Выполнение запроса
            int affectedRows = pst1.executeUpdate(); // Используем executeUpdate для выполнения команды удаления

            // Проверка, была ли запись удалена
            if (affectedRows > 0) {
                JOptionPane.showMessageDialog(null, "Удалён");
            } else {
                JOptionPane.showMessageDialog(null, "Запись не найдена.", "Ошибка", JOptionPane.WARNING_MESSAGE);
            }

            UpdateTable(); // Обновляем таблицу
            search_user();  // Обновляем поиск, если это необходимо

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        } finally {
            // Закрываем ресурсы
            try {
                if (pst1 != null) pst1.close();
                if (conn1 != null) conn1.close();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex);
            }
        }
    }

    @FXML
    void initialize() {
        UpdateTable();
        search_user();
// Слушатель выбора строки в таблице
        tabl.getSelectionModel().selectedItemProperty().addListener(
                (obs, oldSelection, newSelection) -> {
                    if (newSelection != null) {
                        fillFieldsFromSelectedRow(newSelection);
                    } else {
                        clearFormFields();
                    }
                }
        );
    }

    // Метод заполнения полей из выбранной строки (перенесен из initialize)
    private void fillFieldsFromSelectedRow(getsetpolz selectedProduct) {
        id.setText(String.valueOf(selectedProduct.getId()));
        imya.setText(selectedProduct.getИмя());
        parol.setText(selectedProduct.getПароль());
        email.setText(String.valueOf(selectedProduct.getEmail()));
    }

    // Метод очистки полей формы (перенесен из initialize)
    private void clearFormFields() {
        id.clear();
        imya.clear();
        parol.clear();
        email.clear();

        assert dob != null : "fx:id=\"dob\" was not injected: check your FXML file 'polzovateli.fxml'.";
        assert email != null : "fx:id=\"email\" was not injected: check your FXML file 'polzovateli.fxml'.";
        assert emailtab != null : "fx:id=\"emailtab\" was not injected: check your FXML file 'polzovateli.fxml'.";
        assert id != null : "fx:id=\"id\" was not injected: check your FXML file 'polzovateli.fxml'.";
        assert idtab != null : "fx:id=\"idtab\" was not injected: check your FXML file 'polzovateli.fxml'.";
        assert imya != null : "fx:id=\"imya\" was not injected: check your FXML file 'polzovateli.fxml'.";
        assert imyatab != null : "fx:id=\"imyatab\" was not injected: check your FXML file 'polzovateli.fxml'.";
        assert izm != null : "fx:id=\"izm\" was not injected: check your FXML file 'polzovateli.fxml'.";
        assert nazad != null : "fx:id=\"nazad\" was not injected: check your FXML file 'polzovateli.fxml'.";
        assert obn != null : "fx:id=\"obn\" was not injected: check your FXML file 'polzovateli.fxml'.";
        assert parol != null : "fx:id=\"parol\" was not injected: check your FXML file 'polzovateli.fxml'.";
        assert paroltab != null : "fx:id=\"paroltab\" was not injected: check your FXML file 'polzovateli.fxml'.";
        assert poisk != null : "fx:id=\"poisk\" was not injected: check your FXML file 'polzovateli.fxml'.";
        assert tabl != null : "fx:id=\"tabl\" was not injected: check your FXML file 'polzovateli.fxml'.";
        assert ydal != null : "fx:id=\"ydal\" was not injected: check your FXML file 'polzovateli.fxml'.";

    }

}
