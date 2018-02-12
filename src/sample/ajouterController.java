package sample;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class ajouterController {
    tableController taa;
    ObservableList<person> tab;

    public TextField nom_a;
    public TextField prenom_a;
    public DatePicker date_a;
    public TextField ville_a;
    sqlcontroller s = new sqlcontroller();

    public void ajout() throws SQLException {
        if (nom_a.getText().trim().isEmpty() || prenom_a.getText().trim().isEmpty()
            || ville_a.getText().trim().isEmpty() || date_a.getValue() == null){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Erruer");
            alert.setHeaderText(null);
            alert.setContentText("please entre all fields");
            alert.showAndWait();
        }
        else{
            s.insert(nom_a.getText(),prenom_a.getText(),String.valueOf(date_a.getValue()),ville_a.getText());
            Stage a =(Stage) nom_a.getScene().getWindow();
            a.close();}


    }



    public void annu(ActionEvent actionEvent) throws IOException {
        Stage a =(Stage) nom_a.getScene().getWindow();
        a.close();
    }

    }
