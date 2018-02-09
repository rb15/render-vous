package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ajouterController {
    tableController taa;
    ObservableList<person> tab;
    public ajouterController() {
        this.tab=tab;
    }
    public ajouterController(tableController taa){
        this.taa=taa;
    }
    public TextField nom_a;
    public TextField prenom_a;
    public DatePicker date_a;
    public TextField ville_a;
    sqlcontroller s = new sqlcontroller();
    tableController j = new tableController();
    public void ajout() throws SQLException {
        s.insert(nom_a.getText(),prenom_a.getText(),String.valueOf(date_a.getValue()),ville_a.getText());
        Stage a =(Stage) nom_a.getScene().getWindow();
        a.close();


    }



    public void annu(ActionEvent actionEvent) throws IOException {
        Stage a =(Stage) nom_a.getScene().getWindow();
        a.close();



    }


}
