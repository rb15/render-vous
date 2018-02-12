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
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

    public class tableController implements Initializable{
    public TableView<person> table;
    public TableColumn<person , String> nom;
    public TableColumn<person , String> prenom;
    public TableColumn<person , DatePicker> date;
    public TableColumn<person , String> ville;
    public TableColumn<person, Integer> id;
    public Button add;
    public  TextField search;


    ObservableList<person> list = FXCollections.observableArrayList();
    sqlcontroller s = new sqlcontroller();
    ObservableList<person> list1 = FXCollections.observableArrayList();


        @Override
    public void initialize(URL location, ResourceBundle resources) {
        nom.setCellValueFactory(new PropertyValueFactory<person,String>("nom"));
        prenom.setCellValueFactory(new PropertyValueFactory<person,String>("prenom"));
        date.setCellValueFactory(new PropertyValueFactory<person,DatePicker>("date"));
        ville.setCellValueFactory(new PropertyValueFactory<person,String>("ville"));
        id.setCellValueFactory(new PropertyValueFactory<person,Integer>("id"));
        id.setVisible(false);
        try {
            table.setItems(s.initable(list));
        } catch (SQLException e) {
            e.printStackTrace();
        }}

    public person setitem() {
        person a = table.getSelectionModel().getSelectedItem();
        return a;
         }


    public void ajouter(ActionEvent actionEvent) throws IOException {
        Parent window = FXMLLoader.load(getClass().getResource("ajouter.fxml"));
        Scene badro = new Scene(window);
        Stage st1 = new Stage();
        st1.initModality(Modality.APPLICATION_MODAL);
        st1.initOwner(table.getScene().getWindow());
        st1.setTitle("ajoute");
        st1.setScene(badro);
        st1.showAndWait();
        ref();
        }


    public void suprimer(ActionEvent actionEvent) throws SQLException {
        try {
            person a =   table.getSelectionModel().getSelectedItem();
            s.delete(a.getId(),list,a);
        }catch (NullPointerException e){
            Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
            alert1.setTitle("Erruer");
            alert1.setHeaderText(null);
            alert1.setContentText("Please select element");
            alert1.showAndWait();
            }}

    public void modifier(ActionEvent actionEvent)  {
        try {
            FXMLLoader loader =new  FXMLLoader(getClass().getResource("modifier.fxml"));
            Parent window = loader.load();
            Scene badro = new Scene(window);
            Stage st1 = new Stage();
            modifierControlle k = loader.getController();
            k.myf(setitem().getNom(),setitem().getPrenom(),setitem().getDate(),setitem().getVille(),setitem().getId());
            st1.initModality(Modality.APPLICATION_MODAL);
            st1.initOwner(table.getScene().getWindow());
            st1.setTitle("Modifier");
            st1.setScene(badro);
            st1.showAndWait();
            ref();
        }catch(NullPointerException a) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Erruer");
            alert.setHeaderText(null);
            alert.setContentText("Please select element");
            alert.showAndWait();
        }catch(IOException e) {
            e.printStackTrace();
        }}

    public void ref() {
        list.clear();
        try {
            table.setItems(s.initable(list));
        } catch (SQLException e) {
            e.printStackTrace();
        }}

        public void entre(KeyEvent keyEvent) throws IOException {
            if(keyEvent.getCode()== KeyCode.ENTER) {
                FXMLLoader loader =new  FXMLLoader(getClass().getResource("search.fxml"));
                Parent window = loader.load();
                Scene badro = new Scene(window);
                Stage st1 = new Stage();
                searchControlle b = loader.getController();
                st1.initModality(Modality.APPLICATION_MODAL);
                st1.initOwner(table.getScene().getWindow());
                st1.setTitle("Modifier");
                st1.setScene(badro);
                b.myf(search.getText());
                st1.showAndWait();
                search.setText("");
            }





       /*     if (search.getText().isEmpty()) {
                list.clear();
                try {
                    table.setItems(s.initable(list));
                } catch (SQLException e) {
                    e.printStackTrace();
                }}
            if(keyEvent.getCode()== KeyCode.ENTER){
                try {
                    list1.clear();
                    table.setItems(s.search(list1,search.getText()));
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                }*/
                }



       }

