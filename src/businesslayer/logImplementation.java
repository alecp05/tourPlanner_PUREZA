package businesslayer;

import dataaccesslayer.logModelDAO;
import models.logModel;
import models.tourModel;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class logImplementation implements logManager{

    logModelDAO logModelDAO = new logModelDAO();

    public List<logModel> GetLogItems(){return logModelDAO.GetLogItems();}

    @Override
    public List<logModel> SearchLogItems(String itemName, boolean caseSensitive) {
        List<logModel> items = GetLogItems();

        if(caseSensitive){
            return items
                    .stream()
                    .filter(x -> x.getTourName().contains(itemName))
                    .collect(Collectors.toList());
        }
        return items
                .stream()
                .filter(x -> x.getTourName().toLowerCase().contains(itemName.toLowerCase(Locale.ROOT)))
                .collect(Collectors.toList());
    }
}
