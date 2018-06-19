package sample;

import javafx.animation.KeyFrame;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
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
import javafx.animation.Animation;
import javafx.animation.Timeline;
import javafx.util.Duration;

import java.lang.Object;
import java.time.LocalDate;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
    public TableColumn<person , String> temp;
    public TableColumn<person, Integer> id;

    public Button add;
    public  TextField search;
    public Label time;
    public Label time2;
    public DatePicker date1;

    ObservableList<person> list = FXCollections.observableArrayList();
    sqlcontroller s = new sqlcontroller();
    ObservableList<person> list1 = FXCollections.observableArrayList();

        public DateFormat format = new SimpleDateFormat("HH:mm");
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
          public Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), new EventHandler() {
            @Override
            public void handle(Event event) {
                final Calendar cal = Calendar.getInstance();
                time.setText(format.format(cal.getTime()));
                 }
        }));
          public Timeline timealert = new Timeline(new KeyFrame(Duration.seconds(30), new EventHandler() {
            @Override
            public void handle(Event event) {
                final Calendar cal = Calendar.getInstance();
                try {
                    s.alert(dateFormat.format(cal.getTime()),format.format(cal.getTime()));
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                 }
        }));




        @Override
        public void initialize(URL location, ResourceBundle resources) {
            Calendar cal = Calendar.getInstance();
            date1.setValue(LocalDate.parse(dateFormat.format(cal.getTime())));
            time2.setText(String.valueOf(LocalDate.parse(dateFormat.format(cal.getTime()))));
            timeline.setCycleCount(Animation.INDEFINITE);
            timeline.play();
            timealert.setCycleCount(Animation.INDEFINITE);
            timealert.play();
            nom.setCellValueFactory(new PropertyValueFactory<person,String>("nom"));
            prenom.setCellValueFactory(new PropertyValueFactory<person,String>("prenom"));
            date.setCellValueFactory(new PropertyValueFactory<person,DatePicker>("date"));
            ville.setCellValueFactory(new PropertyValueFactory<person,String>("ville"));
            temp.setCellValueFactory(new PropertyValueFactory<person,String>("temp"));
            id.setCellValueFactory(new PropertyValueFactory<person,Integer>("id"));
            id.setVisible(false);
            try {
                table.setItems(s.initable(list,String.valueOf(date1.getValue())));
            } catch (SQLException e) {
                e.printStackTrace();
            }


         /*
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
            DateFormat dateFormat1 = new SimpleDateFormat("HH:mm:ss");
            //get current date time with Date()
            //get current date time with Calendar()
            Calendar cal = Calendar.getInstance();
            System.out.println(dateFormat.format(cal.getTime()));
            time.setText(dateFormat.format(cal.getTime()));
            time2.setText(dateFormat1.format(cal.getTime()));*/
        }

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
            ref();
        }catch (NullPointerException e){
            Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
            alert1.setTitle("Erruer");
            alert1.setHeaderText(null);
            alert1.setContentText("Please select element");
            alert1.showAndWait();

            }

        }

    public void modifier(ActionEvent actionEvent)  {
        try {
            FXMLLoader loader =new  FXMLLoader(getClass().getResource("modifier.fxml"));
            Parent window = loader.load();
            Scene badro = new Scene(window);
            Stage st1 = new Stage();
            modifierControlle k = loader.getController();
            k.myf(setitem().getNom(),setitem().getPrenom(),setitem().getDate(),setitem().getVille(),setitem().getId(),setitem().getTemp());
            st1.initModality(Modality.APPLICATION_MODAL);
            st1.initOwner(table.getScene().getWindow());
            st1.setTitle("Modifier");
            st1.setScene(badro);
            st1.showAndWait();
            ref();

        }catch(IOException e) {
            e.printStackTrace();
        }}

    public void ref() {
            

        list.clear();
        try {
            table.setItems(s.initable(list,String.valueOf(date1.getValue())));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        list1.clear();
        try {
            table.setItems(s.initable(list1,String.valueOf(date1.getValue())));
        } catch (SQLException e) {
            e.printStackTrace();

        }
        }

        public void entre(KeyEvent keyEvent) throws IOException {
         /*   if(keyEvent.getCode()== KeyCode.ENTER) {
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
            }*/


            if (keyEvent.getCode() == KeyCode.ENTER) {
                System.out.print(search.getText());


                list.clear();
                try {

                    table.setItems(s.initable(list,String.valueOf(date1.getValue())));
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                if (!search.getText().isEmpty()) {
                    try {
                        list1.clear();
                        table.setItems(s.search(list1, search.getText()));
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        }


        public void date(ActionEvent actionEvent) {
            if(date1.getValue()!=null) {
                list.clear();
                try {
                    table.setItems(s.initable(list, String.valueOf(date1.getValue())));
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

