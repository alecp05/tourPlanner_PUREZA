package dataaccesslayer.database;

import dataaccesslayer.configuration.configurationHandler;
import dataaccesslayer.database.methods.deleteMethods;
import dataaccesslayer.database.methods.getMethods;
import dataaccesslayer.database.methods.insertMethods;
import dataaccesslayer.database.methods.updateMethods;
import models.logModel;
import models.tourModel;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class database implements dataAccess {

    configurationHandler configurationHandler = new configurationHandler();

    private String url;
    private String user;
    private String password;

    getMethods getMethod = new getMethods();
    insertMethods insertMethods = new insertMethods();
    deleteMethods deleteMethods = new deleteMethods();
    updateMethods updateMethods= new updateMethods();

    public database() throws IOException {
        //get info from config file
        //establish DB connection
        url = configurationHandler.getUrl();
        user = configurationHandler.getUser();
        password = configurationHandler.getPassword();
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
        insertMethods.insertTourMethod(url,user,password,name,description,distance,start,end);
    }

    @Override
    public void InsertLogItems(String name, String date, String report, String distance, String totalTime, int rating,
                               String averageSpeed, String weather, int breaks, String start, String end) {
        insertMethods.insertLogMethod(url,user,password,name,date,report,distance,totalTime,rating,averageSpeed,weather,breaks,start,end);
    }

    @Override
    public void DeleteTourItem(String name, String description) {
        deleteMethods.deleteTourMethod(url, user, password, name, description);
    }

    @Override
    public void DeleteLogItem(String date, String report) {
        deleteMethods.deleteLogMethod(url, user, password, date, report);
    }

    @Override
    public List<String> GetTourNames() {
        return getMethod.getTourName(url,user,password);
    }

    @Override
    public List<String> GetLogNames() {
        return getMethod.getLogNames(url, user, password);
    }

    @Override
    public void UpdateTourItem(String chosenTourName, String description, int distance, String start, String end) {
        updateMethods.UpdateTourItem(url,user,password, chosenTourName, description, distance,start,end);
    }

    @Override
    public void UpdateLogItem(String indexString, String date, String report, String distance,
                              String totalTime, int rating, String averageSpeed, String weather, int breaks, String start, String end) {
        updateMethods.UpdateLogItem(url, user, password,indexString,date,report,distance,totalTime,rating,averageSpeed,weather,breaks,start,end);
    }
}
