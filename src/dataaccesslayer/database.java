package dataaccesslayer;

import models.logModel;
import models.tourModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class database implements dataAccess{

    private String url;
    private String user;
    private String password;
    private String connectionString;

    public database(){
        //get info from config file
        connectionString = "...";
        //establish DB connection

        url ="jdbc:postgresql://localhost:5432/tourPlanner";
        user = "postgres";
        password = "alecUser";
    };

    @Override
    public List<tourModel> GetTourItems() {

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

        /*
        tourModel[] tourModels = {
                new tourModel("Tour 1", "Vienna to Lower Austria",170 ,"Vienna","Lower Austria"),
                new tourModel("Tour 2", "Vienna to Upper Austria",  220,"Vienna","Upper Austria"),
                new tourModel("Tour 3", "Vienna to Salzburg",  330,"Vienna","Salzburg")
        };

        tourModel tour1 = new tourModel("Tour 4", "Vienna to Salzburg",  330,"Vienna","Salzburg");
        tourModel tour2 = new tourModel("Tour 5", "Vienna to Salzburg",  330,"Vienna","Salzburg");
        tours.add(tour1);
        tours.add(tour2);*/

        //return new ArrayList<tourModel>(Arrays.asList(tourModels));
        return tours;
    }

    @Override
    public List<logModel> GetLogItems() {

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

        /*logModel[] logModels = {
                new logModel("Tour 1", "16-02-2021","It was fun.","170 km", "27 Hours", 8,"15km/h",
                        "It was cloudy",12,"Floridsdorf", "Buchbach"),
                new logModel("Tour 1", "08-04-2021","It was okay.","170 km", "29 Hours", 8,"17km/h",
                        "It was sunny and cloudy",12,"Floridsdorf", "Buchbach"),
                new logModel("Tour 3", "08-04-2021","It was super fun.","330 km", "230 Hours", 8,"14km/h",
                        "It was sunny and cloudy",25,"Floridsdorf", "Eugendorf"),
        };
        return new ArrayList<logModel>(Arrays.asList(logModels));*/

        return logs;
    }
}
