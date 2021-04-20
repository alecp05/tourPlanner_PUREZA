package dataaccesslayer.database;

import models.logModel;
import models.tourModel;

import java.util.List;

public interface dataAccess {
    public List<tourModel> GetTourItems();
    public List<logModel> GetLogItems();
    public void InsertTourItems(String name, String description, int distance, String start, String end);
    public void InsertLogItems(String name, String date, String report, String distance, String totalTime,
                               int rating, String averageSpeed, String weather, int breaks, String start, String end);
    public void DeleteTourItem(String name, String description);
    public void DeleteLogItem(String date, String report);
    public List<String> GetTourNames();
}
