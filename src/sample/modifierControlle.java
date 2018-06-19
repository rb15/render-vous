package sample;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.time.LocalDate;


public class modifierControlle {
    public TextField nom_a;
    public TextField prenom_a;
    public DatePicker date_a;
    public TextField ville_a;
    public TextField id_a;
    public TextField temp_a;



    sqlcontroller s = new sqlcontroller();
    public void modifier(ActionEvent actionEvent)throws SQLException {
        if (nom_a.getText().trim().isEmpty() || prenom_a.getText().trim().isEmpty()
                || ville_a.getText().trim().isEmpty() || date_a.getValue() == null || temp_a.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Erruer");
            alert.setHeaderText(null);
            alert.setContentText("please entre all fields");
            alert.showAndWait();
        }
        else{
       s.update(nom_a.getText(),prenom_a.getText(),String.valueOf(date_a.getValue()),ville_a.getText(),Integer.parseInt(id_a.getText()),temp_a.getText());
        Stage a =(Stage) nom_a.getScene().getWindow();
        a.close();}
    }
    public void myf(String a,String b,String c,String d,int e,String f){
        nom_a.setText(a);
        prenom_a.setText(b);
        date_a.setValue(LocalDate.parse(c));
        ville_a.setText(d);
        temp_a.setText(f);
        id_a.setText(String.valueOf(e));

    }
    public void annu(ActionEvent actionEvent) {
        Stage a =(Stage) nom_a.getScene().getWindow();
        a.close();
    }
}
