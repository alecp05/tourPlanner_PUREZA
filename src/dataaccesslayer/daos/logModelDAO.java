package dataaccesslayer.daos;

import dataaccesslayer.database.dataAccess;
import dataaccesslayer.database.database;
import models.logModel;

import java.io.IOException;
import java.util.List;

public class logModelDAO {
    private dataaccesslayer.database.dataAccess dataAccess;

    public logModelDAO() throws IOException {
        dataAccess = new database();
    }

    public List<logModel> GetLogItems(){return dataAccess.GetLogItems();}

    public void InsertLogItems(String name, String date, String report, String distance, String totalTime,
                               int rating, String averageSpeed, String weather, int breaks, String start, String end)
    {
        dataAccess.InsertLogItems(name,date,report,distance,totalTime,rating,averageSpeed,weather,breaks,start,end);
    }
    public void DeleteLogItem(String date, String report) {
        dataAccess.DeleteLogItem(date, report);
    }
    public List<String> GetLogNames(){return dataAccess.GetLogNames();}
    public void UpdateLogItem(String indexString, String date, String report, String distance,
                              String totalTime, int rating, String averageSpeed, String weather, int breaks, String start, String end){
        dataAccess.UpdateLogItem(indexString, date,report,distance,totalTime,rating,averageSpeed,weather,breaks,start,end);
    };
};
