package sample;

import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import javax.swing.text.TableView;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class sqlcontroller {
    Connection cnn;
    public sqlcontroller() {
        sqlcnx c = new sqlcnx();
        cnn=c.cnx();

        if (cnn==null) System.exit(1);

    }
    public boolean iscnn(){
        try {
            return !cnn.isClosed();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void alert(String a ,String b) throws SQLException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet= null;
        String query ="select nom,prenom from person where date=? and temp=?";
        try {
            preparedStatement = cnn.prepareStatement(query);
            preparedStatement.setString(1,a);
            preparedStatement.setString(2,b);
            resultSet = preparedStatement.executeQuery();
            System.out.println("out");
            if (resultSet.next()){
                System.out.println("in");
                Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                alert1.setTitle("Rendez-vous");
                alert1.setHeaderText(null);
                alert1.setContentText("Render-vous de " + resultSet.getString("nom") + " " + resultSet.getString("prenom"));
                alert1.show();
            }
        } catch (Exception e) {
            System.out.println(e);

        }finally {
            preparedStatement.close();
            resultSet.close();
        }
    }

    public ObservableList<person> initable(ObservableList<person> a,String b) throws SQLException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet= null;
        String query ="select * from person where date=?";
        try {
            preparedStatement = cnn.prepareStatement(query);
            preparedStatement.setString(1,b);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                a.add(new person(
                        resultSet.getString("nom"),
                        resultSet.getString("prenom"),
                        resultSet.getString("date"),
                        resultSet.getString("ville"),
                        resultSet.getString("temp"),
                        resultSet.getInt("id")
                ));
            }

            return a;

    } catch (Exception e) {
            System.out.println(e);
            return a;
        }finally {
            preparedStatement.close();
            resultSet.close();
        }
    }

    public ObservableList<person> search(ObservableList<person> a,String b) throws SQLException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet= null;
        String query ="select * from person where nom=? or prenom=? or ville=?";
        try {
            preparedStatement = cnn.prepareStatement(query);
            preparedStatement.setString(1,b);
            preparedStatement.setString(2,b);
            preparedStatement.setString(3,b);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                a.add(new person(
                        resultSet.getString("nom"),
                        resultSet.getString("prenom"),
                        resultSet.getString("date"),
                        resultSet.getString("ville"),
                        resultSet.getString("temp"),
                        resultSet.getInt("id")
                ));
            }
            return a;
        } catch (Exception e) {
            System.out.println(e);
            return a;
        }finally {
            preparedStatement.close();
            resultSet.close();
        }
    }

    public void update(String a,String b,String c,String d,int e,String f)throws SQLException{
            PreparedStatement preparedStatement = null;
            String query ="UPDATE person set nom=? , prenom=? , date=? , ville=? , temp=? where id=?";
            try {
                preparedStatement = cnn.prepareStatement(query);
                preparedStatement.setString(1,a);
                preparedStatement.setString(2,b);
                preparedStatement.setString(3,c);
                preparedStatement.setString(4,d);
                preparedStatement.setString(5,f);
                preparedStatement.setInt(6,e);
                preparedStatement.execute();

            }catch (Exception e1){
                System.out.println(e1);
                System.out.println("not delete");

            }
            finally {
                preparedStatement.close();
            }
        }
        public void delete(Integer a,ObservableList<person> b,person c) throws SQLException {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Erruer");
            alert.setHeaderText(null);
            alert.setContentText("do you want to delete this");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK){
            PreparedStatement preparedStatement = null;
            String query ="DELETE FROM  person where id=?";
            try {


                    preparedStatement = cnn.prepareStatement(query);
                    preparedStatement.setInt(1,a);
                    preparedStatement.execute();
                    b.remove(c);


            }catch (Exception e){
                System.out.println(e);
                System.out.println("not delete");

            }
            finally {
                preparedStatement.close();
            }
        }}
        public void insert(String a,String b,String c,String d,String e)throws SQLException {
        PreparedStatement preparedStatement = null;
        String query ="INSERT INTO person (nom,prenom,date,ville,temp)VALUES(?,?,?,?,?)";
        try {
            preparedStatement = cnn.prepareStatement(query);
            System.out.println(d);
            preparedStatement.setString(1,a);
            preparedStatement.setString(2,b);
            preparedStatement.setString(3,c);
            preparedStatement.setString(4,d);
            preparedStatement.setString(5,e);

            preparedStatement.execute();

        }catch (Exception e2){
            System.out.println(e2);
            System.out.println("not added");

        }
        finally {
            preparedStatement.close();
        }
            }
    public boolean login(String a , String b) throws SQLException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet= null;
        String query ="select * from login where username= ? and password= ?";
        try {
            preparedStatement = cnn.prepareStatement(query);
            preparedStatement.setString(1,a);
            preparedStatement.setString(2,b);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next())
                return true;
            else
                return false;

        }catch (Exception e){
            return false;
        }finally {
            preparedStatement.close();
            resultSet.close();
        }
    }
}
