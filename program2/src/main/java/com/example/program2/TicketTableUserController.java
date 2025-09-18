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

public class TicketTableUserController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button BackID;

    @FXML
    private TableColumn<GetSet, Integer> EventIDColumn;

    @FXML
    private TableColumn<GetSet, Integer> ParticipantIDColumn;

    @FXML
    private TableColumn<GetSet, Integer> PriceColumn;

    @FXML
    private TableColumn<GetSet, Date> PurchaseDateColumn;

    @FXML
    private TextField Search;

    @FXML
    private TableColumn<GetSet, Integer> TicketIDColumn;

    @FXML
    private TableView<GetSet> TicketTable;

    @FXML
    private TableColumn<GetSet, Integer> TicketTypeIDColumn;

    int index=-1;
    ObservableList<GetSet> listDB; //Название массива
    Connection conn1 = null; //Конектор равен нулю
    PreparedStatement pst1 = null;
    @FXML
    void Back(ActionEvent event) throws IOException {
        FXMLLoader fmxlLoader = new FXMLLoader(HelloApplication.class.getResource("tickettableuser.fxml"));
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
        assert BackID != null : "fx:id=\"BackID\" was not injected: check your FXML file 'tickettableuser.fxml'.";
        assert EventIDColumn != null : "fx:id=\"EventIDColumn\" was not injected: check your FXML file 'tickettableuser.fxml'.";
        assert ParticipantIDColumn != null : "fx:id=\"ParticipantIDColumn\" was not injected: check your FXML file 'tickettableuser.fxml'.";
        assert PriceColumn != null : "fx:id=\"PriceColumn\" was not injected: check your FXML file 'tickettableuser.fxml'.";
        assert PurchaseDateColumn != null : "fx:id=\"PurchaseDateColumn\" was not injected: check your FXML file 'tickettableuser.fxml'.";
        assert Search != null : "fx:id=\"Search\" was not injected: check your FXML file 'tickettableuser.fxml'.";
        assert TicketIDColumn != null : "fx:id=\"TicketIDColumn\" was not injected: check your FXML file 'tickettableuser.fxml'.";
        assert TicketTable != null : "fx:id=\"TicketTable\" was not injected: check your FXML file 'tickettableuser.fxml'.";
        assert TicketTypeIDColumn != null : "fx:id=\"TicketTypeIDColumn\" was not injected: check your FXML file 'tickettableuser.fxml'.";

    }

}
