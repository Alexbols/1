package com.example.zoomir;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class DatabaseConnector {
    private static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/zoomir";
    private static final String USER = "root";
    private static final String PASSWORD = "";
    private static Connection connection;

    // Получение соединения (Singleton)
    public static Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
                System.out.println("Подключение к БД установлено!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Ошибка подключения: " + e.getMessage());
        }
        return connection;
    }

    // Закрытие соединения (вызывать только при завершении приложения)
    public static void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Подключение закрыто");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Получение списка товаров
    public static ObservableList<getsettovars> getSetTovarObservableList() {
        ObservableList<getsettovars> list = FXCollections.observableArrayList();
        try (PreparedStatement ps = getConnection().prepareStatement("SELECT * FROM tovars");
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                list.add(new getsettovars(
                        rs.getInt("id"),
                        rs.getString("название"), // можно изменить на "name" или аналогичное
                        rs.getInt("количество") // аналогично, можно использовать "quantity"
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // Получение списка заказов
    public static ObservableList<getsetzakaz> getsetzakazObservableList() {
        ObservableList<getsetzakaz> list = FXCollections.observableArrayList();
        try (PreparedStatement ps = getConnection().prepareStatement("SELECT * FROM zakaz");
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                list.add(new getsetzakaz(
                        rs.getInt("id"),
                        rs.getDate("дата"), // Можно изменить на "date"
                        rs.getString("название"), // Можно изменить на "name"
                        rs.getInt("количество") // Можно изменить на "quantity"
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }


    // Получение списка пользователей
    public static ObservableList<getsetpolz> getsetpolzObservableList() {
        ObservableList<getsetpolz> list = FXCollections.observableArrayList();
        try (PreparedStatement ps = getConnection().prepareStatement("SELECT * FROM users");
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                list.add(new getsetpolz(
                        rs.getInt("id"),
                        rs.getString("имя"), // аналогично, можно использовать другое имя
                        rs.getString("пароль"), // Не храните пароли в открытом виде
                        rs.getString("email")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}