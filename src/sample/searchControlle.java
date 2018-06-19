package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.SQLException;

public class searchControlle  {
    public TableView<person> table;
    public TableColumn<person , String> nom;
    public TableColumn<person , String> prenom;
    public TableColumn<person , DatePicker> date;
    public TableColumn<person , String> ville;
    public TableColumn<person, Integer> id;
    public TableColumn<person, String> temp;

    ObservableList<person> list = FXCollections.observableArrayList(

    );
    sqlcontroller s = new sqlcontroller();
    public void myf(String a){
        nom.setCellValueFactory(new PropertyValueFactory<person,String>("nom"));
        prenom.setCellValueFactory(new PropertyValueFactory<person,String>("prenom"));
        date.setCellValueFactory(new PropertyValueFactory<person,DatePicker>("date"));
        ville.setCellValueFactory(new PropertyValueFactory<person,String>("ville"));
        id.setCellValueFactory(new PropertyValueFactory<person,Integer>("id"));
        id.setVisible(false);
        temp.setCellValueFactory(new PropertyValueFactory<person,String>("temp"));
        try {
            table.setItems(s.search(list,a));
        } catch (SQLException e) {
            e.printStackTrace();

        }}





    }









