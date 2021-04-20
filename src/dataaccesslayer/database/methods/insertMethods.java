package dataaccesslayer.database.methods;

import models.tourModel;

import java.sql.*;

public class insertMethods {

    public void insertTourMethod(String url, String user, String password,
                                  String name, String description, int distance, String start, String end){

        //find new tourId for new tour
        int count = 1;
        ResultSet myRs = null;
        try(Connection connection = DriverManager.getConnection(url, user,password);
            PreparedStatement statement = connection.prepareStatement("SELECT MAX(tour_id) FROM touritems")
        ){
            myRs = statement.executeQuery();

            while(myRs.next()) {
                Integer tempTourId = myRs.getInt(1);
                count = tempTourId + 1;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        //insert into database
        try(Connection connection = DriverManager.getConnection(url, user, password);
            PreparedStatement statement2 = connection.prepareStatement("INSERT INTO touritems VALUES (?,?,?,?,?,?);");
        ){
            statement2.setString(1, name);
            statement2.setInt(2, count);
            statement2.setString(3, description);
            statement2.setInt(4,distance);
            statement2.setString(5,start);
            statement2.setString(6, end);
            statement2.execute();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        System.out.println(count);
    }

    public void insertLogMethod(String url, String user, String password,String name, String date, String report, String distance,
                                String totalTime, int rating, String averageSpeed, String weather, int breaks, String start, String end){

        //find new tourId for new tour
        int count = 1;
        ResultSet myRs = null;
        try(Connection connection = DriverManager.getConnection(url, user,password);
            PreparedStatement statement = connection.prepareStatement("SELECT MAX(log_id) FROM logitems")
        ){
            myRs = statement.executeQuery();

            while(myRs.next()) {
                Integer tempLogId = myRs.getInt(1);
                count = tempLogId + 1;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        //insert into database
        try(Connection connection = DriverManager.getConnection(url, user, password);
            PreparedStatement statement2 = connection.prepareStatement("INSERT INTO logitems VALUES (?,?,?,?,?,?,?,?,?,?,?,?);");
        ){
            statement2.setString(1, name);
            statement2.setInt(2, count);
            statement2.setString(3, date);
            statement2.setString(4,report);
            statement2.setString(5,distance);
            statement2.setString(6, totalTime);
            statement2.setInt(7, rating);
            statement2.setString(8, averageSpeed);
            statement2.setString(9, weather);
            statement2.setInt(10, breaks);
            statement2.setString(11, start);
            statement2.setString(12,end);
            statement2.execute();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }
}
