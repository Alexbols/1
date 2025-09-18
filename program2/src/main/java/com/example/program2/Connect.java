package com.example.program2;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Connect {
    public static Connection ConnectDb() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = (Connection) DriverManager.getConnection("jdbc:mysql://192.168.1.141:3306/isp16", "isp16", "isp16");
            JOptionPane.showMessageDialog(null, "Соединение установлено.");
            return conn;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }
    public static ObservableList<GetSet> TicketUserGetSet() {
        Connection connTABL = ConnectDb();
        ObservableList<GetSet> list = FXCollections.observableArrayList();
        try {
            assert connTABL != null;
            PreparedStatement ps1 = connTABL.prepareStatement("select * from Tickets");
            ResultSet rs1 = ps1.executeQuery();

            while (rs1.next()) {
                while (rs1.next()) {
                    int TicketID = Integer.parseInt(rs1.getString("TicketID"));
                    int EventID = Integer.parseInt(rs1.getString("EventID"));
                    int ParticipantID = Integer.parseInt(rs1.getString("ParticipantID"));
                    int TicketTypeID = Integer.parseInt(rs1.getString("TicketTypeID"));
                    int Price = Integer.parseInt(rs1.getString("Price"));
                    java.sql.Date PurchaseDate = rs1.getDate("PurchaseDate");

                    // Создание объекта getset и добавление его в список
                    list.add(new GetSet(TicketID, EventID, ParticipantID, TicketTypeID, Price, PurchaseDate));
                }

            }
        } catch (Exception ignored) {
        }
        return list;
    }
}
