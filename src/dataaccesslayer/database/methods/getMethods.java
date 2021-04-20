package dataaccesslayer.database.methods;

import models.logModel;
import models.tourModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class getMethods {

    public ArrayList<tourModel> getTourMethod(String url, String user, String password){
        ArrayList<tourModel> tours = new ArrayList<tourModel>();

        //int count = 0;
        ResultSet myRs = null;
        try(Connection connection = DriverManager.getConnection(url, user,password);
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM touritems")
        ){
            myRs = statement.executeQuery();

            while(myRs.next()) {

                String tourName = myRs.getString(1);
                String tourDescription = myRs.getString(3);
                Integer tourDistance = myRs.getInt(4);
                String tourStart = myRs.getString(5);
                String tourEnd = myRs.getString(6);

                tourModel tempTour = new tourModel(tourName,tourDescription,tourDistance,tourStart,tourEnd);
                tours.add(tempTour);
                System.out.println(tourName);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return tours;
    }

    public ArrayList<logModel> getLogMethod(String url, String user, String password){

        ArrayList<logModel> logs = new ArrayList<logModel>();

        ResultSet myRs2 = null;
        try(Connection connection = DriverManager.getConnection(url, user,password);
            PreparedStatement statement2 = connection.prepareStatement("SELECT * FROM logitems")
        ){
            myRs2 = statement2.executeQuery();

            while(myRs2.next()) {

                String logName = myRs2.getString(1);
                String logDate = myRs2.getString(3);
                String logReport = myRs2.getString(4);
                String logDistance = myRs2.getString(5);
                String logTotalTime = myRs2.getString(6);
                Integer logRating = myRs2.getInt(7);
                String logAverageSpeed = myRs2.getString(8);
                String logWeather = myRs2.getString(9);
                Integer logBreaks = myRs2.getInt(10);
                String logStart = myRs2.getString(11);
                String logEnd = myRs2.getString(12);

                logModel tempLog = new logModel(logName,logDate,logReport,logDistance,logTotalTime,logRating,
                        logAverageSpeed,logWeather,logBreaks,logStart,logEnd);
                logs.add(tempLog);
                System.out.println(logName);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return logs;

    }

    public List<String> getTourName(String url, String user, String password){

        List<String> tourNames = new ArrayList<String>();

        ResultSet myRs = null;
        try(Connection connection = DriverManager.getConnection(url, user,password);
            PreparedStatement statement = connection.prepareStatement("SELECT tour_name FROM touritems")
        ){
            myRs = statement.executeQuery();

            while(myRs.next()) {

                String tourName = myRs.getString(1);
                tourNames.add(tourName);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return tourNames;

    }


}
