package sample;

import javafx.collections.ObservableList;

import javax.swing.text.TableView;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
    public ObservableList<person> initable(ObservableList<person> a) throws SQLException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet= null;
        String query ="select * from person";
        try {
            preparedStatement = cnn.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                a.add(new person(
                        resultSet.getString("nom"),
                        resultSet.getString("prenom"),
                        resultSet.getString("date"),
                        resultSet.getString("ville"),
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
        public void update(String a,String b,String c,String d,int e)throws SQLException{
            PreparedStatement preparedStatement = null;
            String query ="UPDATE person set nom=? , prenom=? , date=? , ville=? where id=?";
            try {
                preparedStatement = cnn.prepareStatement(query);
                preparedStatement.setString(1,a);
                preparedStatement.setString(2,b);
                preparedStatement.setString(3,c);
                preparedStatement.setString(4,d);
                preparedStatement.setInt(5,e);
                preparedStatement.execute();

            }catch (Exception e1){
                System.out.println(e1);
                System.out.println("not delete");

            }
            finally {
                preparedStatement.close();
            }
        }
        public void delete(String a,String b,String c,String d) throws SQLException {
            PreparedStatement preparedStatement = null;
            String query ="DELETE FROM  person where nom=? and prenom=? and date=? and ville=?";
            try {
                preparedStatement = cnn.prepareStatement(query);
                preparedStatement.setString(1,a);
                preparedStatement.setString(2,b);
                preparedStatement.setString(3,c);
                preparedStatement.setString(4,d);
                preparedStatement.execute();

            }catch (Exception e){
                System.out.println(e);
                System.out.println("not delete");

            }
            finally {
                preparedStatement.close();
            }
        }
        public void insert(String a,String b,String c,String d)throws SQLException {
        PreparedStatement preparedStatement = null;
        String query ="INSERT INTO person (nom,prenom,date,ville)VALUES(?,?,?,?)";
        try {
            preparedStatement = cnn.prepareStatement(query);
            System.out.println(d);
            preparedStatement.setString(1,a);
            preparedStatement.setString(2,b);
            preparedStatement.setString(3,c);
            preparedStatement.setString(4,d);
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
