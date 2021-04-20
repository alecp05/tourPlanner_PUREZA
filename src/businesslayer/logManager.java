package businesslayer;

import models.logModel;

import java.util.List;

public interface logManager {

    public List<logModel> GetLogItems();
    public List<logModel> SearchLogItems(String itemName, boolean caseSensitive);
    public void InsertLogItem(String name, String date, String report, String distance, String totalTime,
                         int rating, String averageSpeed, String weather, int breaks, String start, String end);
    public void DeleteLogItem(String date, String report);
    public List<String> GetLogNames();
}
