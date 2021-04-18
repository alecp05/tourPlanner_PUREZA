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

        //int count = 0;
        ResultSet myRs = null;
        try(Connection connection = DriverManager.getConnection(url, user,password);
            PreparedStatement statement2 = connection.prepareStatement("SELECT * FROM touritems")
        ){
            myRs = statement2.executeQuery();

            while(myRs.next()) {

                String name = myRs.getString(1);
                System.out.println(name);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        tourModel[] tourModels = {
                new tourModel("Tour 1", "Vienna to Lower Austria",170 ,"Vienna","Lower Austria"),
                new tourModel("Tour 2", "Vienna to Upper Austria",  220,"Vienna","Upper Austria"),
                new tourModel("Tour 3", "Vienna to Salzburg",  330,"Vienna","Salzburg")
        };


        return new ArrayList<tourModel>(Arrays.asList(tourModels));
    }

    @Override
    public List<logModel> GetLogItems() {
        logModel[] logModels = {
                new logModel("Tour 1", "16-02-2021","It was fun.","170 km", "27 Hours", 8,"15km/h",
                        "It was cloudy",12,"Floridsdorf", "Buchbach"),
                new logModel("Tour 1", "08-04-2021","It was okay.","170 km", "29 Hours", 8,"17km/h",
                        "It was sunny and cloudy",12,"Floridsdorf", "Buchbach"),
                new logModel("Tour 3", "08-04-2021","It was super fun.","330 km", "230 Hours", 8,"14km/h",
                        "It was sunny and cloudy",25,"Floridsdorf", "Eugendorf"),
        };
        return new ArrayList<logModel>(Arrays.asList(logModels));
    }
}
