package dataaccesslayer.database;

import dataaccesslayer.database.methods.getMethods;
import dataaccesslayer.database.methods.insertMethods;
import models.logModel;
import models.tourModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class database implements dataAccess {

    private String url;
    private String user;
    private String password;
    private String connectionString;

    getMethods getMethod = new getMethods();
    insertMethods insertMethods = new insertMethods();

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
        tours = getMethod.getTourMethod(url,user,password);
        return tours;
    }

    @Override
    public List<logModel> GetLogItems() {
        ArrayList<logModel> logs = new ArrayList<logModel>();
        logs = getMethod.getLogMethod(url,user,password);
        return logs;
    }

    @Override
    public void InsertTourItems(String name, String description, int distance, String start, String end) {
        System.out.println(name + description + distance + start + end);
        insertMethods.insertTourMethod(url,user,password,name,description,distance,start,end);
    }

    @Override
    public void InsertLogItems(String name, String date, String report, String distance, String totalTime, int rating,
                               String averageSpeed, String weather, int breaks, String start, String end) {
        System.out.println(name + date + report + distance + totalTime);
        insertMethods.insertLogMethod(url,user,password,name,date,report,distance,totalTime,rating,averageSpeed,weather,breaks,start,end);
    }
}
