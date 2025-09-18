package com.example.program1;

import java.io.EOFException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;

public class HelloController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField Conclusion;

    @FXML
    private TextField Enter;

    @FXML
    void About(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("aboutauthore.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 327, 155);
        Stage stage = new Stage();
        stage.setTitle("Об авторе.");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void Execute(ActionEvent event) {
        String input = Enter.getText();
        int sum = 0;
        String[] numbers = input.split("\\s+");
        for (String number : numbers){
            try{
                int num = Integer.parseInt(number.trim());
                if (num % 2 == 0 && num>=10 && num<=99){
                    sum += num * num;
                }
                Conclusion.setText(String.valueOf(sum));
            } catch (NumberFormatException e) {

            }
        }
    }

    @FXML
    void initialize() {
        assert Conclusion != null : "fx:id=\"Conclusion\" was not injected: check your FXML file 'menu.fxml'.";
        assert Enter != null : "fx:id=\"Enter\" was not injected: check your FXML file 'menu.fxml'.";
    }

}
