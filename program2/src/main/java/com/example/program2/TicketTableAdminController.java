package com.example.program2;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Date;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import javax.swing.*;

import static com.example.program2.Connect.TicketUserGetSet;

public class TicketTableAdminController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button BackID;

    @FXML
    private TextField EventID;

    @FXML
    private TableColumn<GetSet, Integer> EventIDColumn;

    @FXML
    private TextField ParticipantID;

    @FXML
    private TableColumn<GetSet, Integer> ParticipantIDColumn;

    @FXML
    private TextField Price;

    @FXML
    private TableColumn<GetSet, Integer> PriceColumn;

    @FXML
    private TextField PurchaseDate;

    @FXML
    private TableColumn<GetSet, Date> PurchaseDateColumn;

    @FXML
    private TextField Search;

    @FXML
    private TextField TicketID;

    @FXML
    private TableColumn<GetSet, Integer> TicketIDColumn;

    @FXML
    private TableView<GetSet> TicketTable;

    @FXML
    private TextField TicketTypeID;

    @FXML
    private TableColumn<GetSet, Integer> TicketTypeIDColumn;

    int index=-1;
    ObservableList<GetSet> listDB; //Название массива
    Connection conn1 = null; //Конектор равен нулю
    PreparedStatement pst1 = null;
    @FXML
    void Add(ActionEvent event) {
        conn1 = Connect.ConnectDb();

        String sql = "insert into Tickets (TicketID, EventID, ParticipantID, TicketTypeID, Price, PurchaseDate) values(?,?,?,?,?,?)";

        try {
            assert conn1 != null;
            pst1 = conn1.prepareStatement(sql);
            pst1.setString(1, TicketID.getText());
            pst1.setString(2, EventID.getText());
            pst1.setString(3, ParticipantID.getText());
            pst1.setString(4, TicketTypeID.getText());
            pst1.setString(5, Price.getText());
            pst1.setString(6, PurchaseDate.getText());
            pst1.execute();

            JOptionPane.showMessageDialog(null, "Добавлено.");
            UpdateTable();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
        private void UpdateTable() {
            TicketIDColumn.setCellValueFactory(new PropertyValueFactory<GetSet, Integer>("TicketID"));
            EventIDColumn.setCellValueFactory(new PropertyValueFactory<GetSet, Integer>("EventID"));
            ParticipantIDColumn.setCellValueFactory(new PropertyValueFactory<GetSet, Integer>("ParticipantID"));
            TicketTypeIDColumn.setCellValueFactory(new PropertyValueFactory<GetSet, Integer>("TicketTypeID"));
            PriceColumn.setCellValueFactory(new PropertyValueFactory<GetSet, Integer>("Price"));
            PurchaseDateColumn.setCellValueFactory(new PropertyValueFactory<GetSet, Date>("PurchaseDate"));
            listDB = TicketUserGetSet();
            TicketTable.setItems(listDB);

            JOptionPane.showMessageDialog(null, "Обновление");
        }

    @FXML
    void Back(ActionEvent event) throws IOException {
        FXMLLoader fmxlLoader = new FXMLLoader(HelloApplication.class.getResource("tickettableadmin.fxml"));
        Stage stage1 = (Stage) BackID.getScene().getWindow();
        stage1.close();

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("menuadmin.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        Stage stage2 = new Stage();
        stage2.setTitle("Меню");
        stage2.setScene(scene);
        stage2.show();
    }

    @FXML
    void Delete(ActionEvent event) {
        conn1 = Connect.ConnectDb();

        String sql = "Delete from Tickets where TicketID = ?";
        try {
            assert conn1 != null;
            pst1 = conn1.prepareStatement(sql);
            pst1.setString(1, TicketID.getText());
            pst1.execute();
            JOptionPane.showMessageDialog(null, "Удалён");
            UpdateTable();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }

    @FXML
    void Edit(ActionEvent event) {
        try {
            conn1 = Connect.ConnectDb();
            String value1 = TicketID.getText();
            String value2 = EventID.getText();
            String value3 = ParticipantID.getText();
            String value4 = TicketTypeID.getText();
            String value5 = Price.getText();
            String value6 = PurchaseDate.getText();
            String sql = "UPDATE Tickets SET TicketID= '" + value1 + "',EventID= '" + value2 + "',ParticipantID= '" + value3 + "',TicketTypeID= '" +value4+ "',Price= '" +value5+ "',PurchaseDate= '" +value6+ "' where TicketID='" + value1 + "' ";

            pst1 = conn1.prepareStatement(sql);
            pst1.execute();
            JOptionPane.showMessageDialog(null, "Изменено");

            UpdateTable();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    @FXML
    void Exit(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    void Update(ActionEvent event) {
        TicketIDColumn.setCellValueFactory(new PropertyValueFactory<GetSet, Integer>("TicketID"));
        EventIDColumn.setCellValueFactory(new PropertyValueFactory<GetSet, Integer>("EventID"));
        ParticipantIDColumn.setCellValueFactory(new PropertyValueFactory<GetSet, Integer>("ParticipantID"));
        TicketTypeIDColumn.setCellValueFactory(new PropertyValueFactory<GetSet, Integer>("TicketTypeID"));
        PriceColumn.setCellValueFactory(new PropertyValueFactory<GetSet, Integer>("Price"));
        PurchaseDateColumn.setCellValueFactory(new PropertyValueFactory<GetSet, Date>("PurchaseDate"));
        listDB = TicketUserGetSet();
        TicketTable.setItems(listDB);

        JOptionPane.showMessageDialog(null, "Обновление");
    }

    @FXML
    void getSelected(MouseEvent event) {

    }

    @FXML
    void initialize() {
        assert BackID != null : "fx:id=\"BackID\" was not injected: check your FXML file 'tickettableadmin.fxml'.";
        assert EventID != null : "fx:id=\"EventID\" was not injected: check your FXML file 'tickettableadmin.fxml'.";
        assert EventIDColumn != null : "fx:id=\"EventIDColumn\" was not injected: check your FXML file 'tickettableadmin.fxml'.";
        assert ParticipantID != null : "fx:id=\"ParticipantID\" was not injected: check your FXML file 'tickettableadmin.fxml'.";
        assert ParticipantIDColumn != null : "fx:id=\"ParticipantIDColumn\" was not injected: check your FXML file 'tickettableadmin.fxml'.";
        assert Price != null : "fx:id=\"Price\" was not injected: check your FXML file 'tickettableadmin.fxml'.";
        assert PriceColumn != null : "fx:id=\"PriceColumn\" was not injected: check your FXML file 'tickettableadmin.fxml'.";
        assert PurchaseDate != null : "fx:id=\"PurchaseDate\" was not injected: check your FXML file 'tickettableadmin.fxml'.";
        assert PurchaseDateColumn != null : "fx:id=\"PurchaseDateColumn\" was not injected: check your FXML file 'tickettableadmin.fxml'.";
        assert Search != null : "fx:id=\"Search\" was not injected: check your FXML file 'tickettableadmin.fxml'.";
        assert TicketID != null : "fx:id=\"TicketID\" was not injected: check your FXML file 'tickettableadmin.fxml'.";
        assert TicketIDColumn != null : "fx:id=\"TicketIDColumn\" was not injected: check your FXML file 'tickettableadmin.fxml'.";
        assert TicketTable != null : "fx:id=\"TicketTable\" was not injected: check your FXML file 'tickettableadmin.fxml'.";
        assert TicketTypeID != null : "fx:id=\"TicketTypeID\" was not injected: check your FXML file 'tickettableadmin.fxml'.";
        assert TicketTypeIDColumn != null : "fx:id=\"TicketTypeIDColumn\" was not injected: check your FXML file 'tickettableadmin.fxml'.";

    }

}
