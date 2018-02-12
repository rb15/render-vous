package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    public TextField username;
    public PasswordField password;
    public Label text;
    Stage st1, st2;


    sqlcontroller s = new sqlcontroller();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    if (s.iscnn()) text.setText("is"); else text.setText("not");
    }

    public void login() throws IOException, SQLException {
        if (s.login(username.getText(),password.getText())) {
            Parent windows = FXMLLoader.load(getClass().getResource("table.fxml"));
            Scene badro = new Scene(windows);
            st1 = new Stage();
            st1.setTitle("table");
            st1.setScene(badro);
            st1.show();
            st2 = (Stage) username.getScene().getWindow();
            st2.close();
        } else
            text.setText("Wrong username or password");
    }


    public void entre(KeyEvent keyEvent) throws IOException, SQLException {
        if(keyEvent.getCode()== KeyCode.ENTER){
            login();
        }
    }
}


