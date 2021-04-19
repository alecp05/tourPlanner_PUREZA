package businesslayer;

import dataaccesslayer.daos.tourModelDAO;
import models.tourModel;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class tourImplementation implements tourManager{

    tourModelDAO tourModelDAO = new tourModelDAO();


    public List<tourModel> GetTourItems(){return tourModelDAO.GetTourItems(); }

    @Override
    public List<tourModel> SearchTourItems(String itemName, boolean caseSensitive) {
        List<tourModel> items = GetTourItems();

        if(caseSensitive){
            return items
                    .stream()
                    .filter(x -> (x.getTourName().contains(itemName) || x.getTourDescription().contains(itemName)
                            || String.valueOf(x.getTourDistance()).contains(itemName)
                            || x.getTourStart().contains(itemName)
                            || x.getTourEnd().contains(itemName)))
                    .collect(Collectors.toList());
        }
        return items
                .stream()
                .filter(x -> x.getTourName().toLowerCase().contains(itemName.toLowerCase(Locale.ROOT))
                        || x.getTourDescription().toLowerCase().contains(itemName.toLowerCase(Locale.ROOT))
                        || String.valueOf(x.getTourDistance()).contains(itemName.toLowerCase(Locale.ROOT))
                        || x.getTourStart().toLowerCase().contains(itemName.toLowerCase(Locale.ROOT))
                        || x.getTourEnd().toLowerCase().contains(itemName.toLowerCase(Locale.ROOT)))
                .collect(Collectors.toList());
    }

    @Override
    public void InsertTourItem(String name, String description, int distance, String start, String end) {
        tourModelDAO.InsertTourItems(name, description, distance, start, end);
    }

    @Override
    public void DeleteTourItem(String name, String description) {
        tourModelDAO.DeleteTourItems(name, description);
    }


}
