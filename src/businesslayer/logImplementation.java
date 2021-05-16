package businesslayer;

import dataaccesslayer.daos.logModelDAO;
import models.logModel;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class logImplementation implements logManager{

    logModelDAO logModelDAO = new logModelDAO();

    public logImplementation() throws IOException {
    }

    public List<logModel> GetLogItems(){return logModelDAO.GetLogItems();}

    @Override
    public List<logModel> SearchLogItems(String itemName, boolean caseSensitive) {
        List<logModel> items = GetLogItems();

        if(caseSensitive){
            return items
                    .stream()
                    .filter(x -> x.getTourName().contains(itemName)
                            || x.getLogDate().contains(itemName)
                            || x.getLogReport().contains(itemName)
                            || x.getLogDistance().contains(itemName)
                            || x.getLogTotalTime().contains(itemName)
                            || String.valueOf(x.getLogRating()).contains(itemName)
                            || x.getLogAverageSpeed().contains(itemName)
                            || x.getLogWeatherCondition().contains(itemName)
                            ||  String.valueOf(x.getLogBreaksTaken()).contains(itemName)
                            || x.getLogStartingPoint().contains(itemName)
                            || x.getLogEndPoint().contains(itemName)
                    )
                    .collect(Collectors.toList());
        }
        return items
                .stream()
                .filter(x -> x.getTourName().toLowerCase().contains(itemName.toLowerCase(Locale.ROOT))
                        || x.getLogDate().toLowerCase().contains(itemName.toLowerCase(Locale.ROOT))
                        || x.getLogReport().toLowerCase().contains(itemName.toLowerCase(Locale.ROOT))
                        || x.getLogDistance().toLowerCase().contains(itemName.toLowerCase(Locale.ROOT))
                        || x.getLogTotalTime().toLowerCase().contains(itemName.toLowerCase(Locale.ROOT))
                        || String.valueOf(x.getLogRating()).contains(itemName.toLowerCase(Locale.ROOT))
                        || x.getLogAverageSpeed().toLowerCase().contains(itemName.toLowerCase(Locale.ROOT))
                        || x.getLogWeatherCondition().toLowerCase().contains(itemName.toLowerCase(Locale.ROOT))
                        || String.valueOf(x.getLogBreaksTaken()).contains(itemName.toLowerCase(Locale.ROOT))
                        || x.getLogStartingPoint().toLowerCase().contains(itemName.toLowerCase(Locale.ROOT))
                        || x.getLogEndPoint().toLowerCase().contains(itemName.toLowerCase(Locale.ROOT)))
                .collect(Collectors.toList());
    }

    @Override
    public void InsertLogItem(String name, String date, String report, String distance, String totalTime, int rating, String averageSpeed, String weather, int breaks, String start, String end) {
        logModelDAO.InsertLogItems(name, date, report, distance, totalTime, rating, averageSpeed, weather, breaks, start, end);
    }

    @Override
    public void DeleteLogItem(String date, String report) {
        logModelDAO.DeleteLogItem(date, report);
    }

    @Override
    public List<String> GetLogNames() {
        return logModelDAO.GetLogNames();
    }

    @Override
    public void UpdateLogItem(String indexString, String date, String report, String distance,
                              String totalTime, int rating, String averageSpeed, String weather, int breaks, String start, String end) {
        logModelDAO.UpdateLogItem(indexString,date,report,distance,totalTime,rating,averageSpeed,weather,breaks,start,end);
    }
}
