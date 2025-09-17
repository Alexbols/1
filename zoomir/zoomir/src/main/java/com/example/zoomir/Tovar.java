package com.example.zoomir;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
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

import static com.example.zoomir.DatabaseConnector.getSetTovarObservableList;


public class Tovar {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button dob;

    @FXML
    private TextField id;

    @FXML
    private TableColumn<getsettovars, Integer> idd;

    @FXML
    private Button izm;

    @FXML
    private TableColumn<getsettovars, Integer> koll;

    @FXML
    private TextField kolvo;

    @FXML
    private Button nazad;

    @FXML
    private TableColumn<getsettovars, String> nazv;

    @FXML
    private TextField nazvan;

    @FXML
    private Button obn;

    @FXML
    private TextField poisk;

    @FXML
    private TableView<getsettovars> tabl;

    @FXML
    private Button vixod;

    @FXML
    private Button ydal;


    ObservableList<getsettovars> listBAP; //Название массива
    Connection conn1 = null; //Конектор равен нулю
    PreparedStatement pst1 = null;

    void search_user() {
        idd.setCellValueFactory(new PropertyValueFactory<>("id"));
        nazv.setCellValueFactory(new PropertyValueFactory<>("Название"));
        koll.setCellValueFactory(new PropertyValueFactory<>("Количество"));

        FilteredList<getsettovars> filteredData = new FilteredList<>(listBAP, b -> true);
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

        SortedList<getsettovars> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tabl.comparatorProperty());
        tabl.setItems(sortedData);
    }

    private void UpdateTable() {
        idd.setCellValueFactory(new PropertyValueFactory<>("id"));
        nazv.setCellValueFactory(new PropertyValueFactory<>("Название"));
        koll.setCellValueFactory(new PropertyValueFactory<>("Количество"));
        listBAP = getSetTovarObservableList();
        tabl.setItems(listBAP);

    }

    @FXML
    void dob(ActionEvent event) {


        conn1 = DatabaseConnector.getConnection(); // Получаем соединение с базой данных

        String sql = "INSERT INTO tovars (id, название, количество) VALUES (?, ?, ?)";

        try {
            // Проверка на пустые значения
            if (id.getText().trim().isEmpty() || nazvan.getText().trim().isEmpty() || kolvo.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Пожалуйста, заполните все поля!");
                return; // Выходим из метода, если какие-либо поля пустые
            }

            pst1 = conn1.prepareStatement(sql); // Подготовка SQL-запроса
            pst1.setInt(1, Integer.parseInt(id.getText().trim())); // Установка значения id
            pst1.setString(2, nazvan.getText().trim()); // Установка названия
            pst1.setInt(3, Integer.parseInt(kolvo.getText().trim())); // Установка количества

            int rowsAffected = pst1.executeUpdate(); // Выполнение запроса
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "Товар добавлен успешно!"); // Успешное добавление
                UpdateTable(); // Обновление таблицы
            }

        } catch (SQLException e) {
            e.printStackTrace(); // Печатаем ошибку в консоль
            JOptionPane.showMessageDialog(null, "Ошибка при добавлении товара: " + e.getMessage()); // Сообщаем об ошибке
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



    @FXML
    void izm(ActionEvent event) {
        try {
            conn1 = DatabaseConnector.getConnection();
            String value1 = id.getText();
            String value2 = nazvan.getText();
            String value3 = kolvo.getText();
            // Проверяем, не является ли какое-либо значение пустым
            if (value1.isEmpty() || value2.isEmpty() || value3.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Все поля должны быть заполнены.", "Ошибка", JOptionPane.ERROR_MESSAGE);
                return; // Выходим из метода, если есть пустота
            }


            String sql = "UPDATE tovars SET id= '" + value1 + "',название= '" + value2 + "',количество= '"+ value3 + "' where id='" + value1 + "' ";

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
        FXMLLoader fmxlLoader = new FXMLLoader(HelloApplication.class.getResource("tovar.fxml"));
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
        listBAP = getSetTovarObservableList();
        System.out.println("Загружено записей: " + listBAP.size()); // Проверка загрузки данных
        tabl.setItems(listBAP);
        tabl.refresh(); // Принудительное обновление таблицы
    }



    @FXML
    void vixod(ActionEvent event) {
        System.exit(0);
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

        String sql = "DELETE FROM tovars WHERE id = ?";

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
    private void fillFieldsFromSelectedRow(getsettovars selectedProduct) {
        id.setText(String.valueOf(selectedProduct.getId()));
        nazvan.setText(selectedProduct.getНазвание());
        kolvo.setText(String.valueOf(selectedProduct.getКоличество()));
    }

    // Метод очистки полей формы (перенесен из initialize)
    private void clearFormFields() {
        id.clear();
        nazvan.clear();
        kolvo.clear();
        assert dob != null : "fx:id=\"dob\" was not injected: check your FXML file 'tovar.fxml'.";
        assert id != null : "fx:id=\"id\" was not injected: check your FXML file 'tovar.fxml'.";
        assert idd != null : "fx:id=\"idd\" was not injected: check your FXML file 'tovar.fxml'.";
        assert izm != null : "fx:id=\"izm\" was not injected: check your FXML file 'tovar.fxml'.";
        assert koll != null : "fx:id=\"koll\" was not injected: check your FXML file 'tovar.fxml'.";
        assert kolvo != null : "fx:id=\"kolvo\" was not injected: check your FXML file 'tovar.fxml'.";
        assert nazad != null : "fx:id=\"nazad\" was not injected: check your FXML file 'tovar.fxml'.";
        assert nazv != null : "fx:id=\"nazv\" was not injected: check your FXML file 'tovar.fxml'.";
        assert nazvan != null : "fx:id=\"nazvan\" was not injected: check your FXML file 'tovar.fxml'.";
        assert obn != null : "fx:id=\"obn\" was not injected: check your FXML file 'tovar.fxml'.";
        assert poisk != null : "fx:id=\"poisk\" was not injected: check your FXML file 'tovar.fxml'.";
        assert tabl != null : "fx:id=\"tabl\" was not injected: check your FXML file 'tovar.fxml'.";
        assert vixod != null : "fx:id=\"vixod\" was not injected: check your FXML file 'tovar.fxml'.";
        assert ydal != null : "fx:id=\"ydal\" was not injected: check your FXML file 'tovar.fxml'.";

    }

}
