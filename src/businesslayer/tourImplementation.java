package businesslayer;

import businesslayer.mapQuestApi.apiHandler;
import dataaccesslayer.daos.tourModelDAO;
import javafx.scene.image.Image;
import models.tourModel;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class tourImplementation implements tourManager{

    tourModelDAO tourModelDAO = new tourModelDAO();
    apiHandler apiHandler = new apiHandler();


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

    @Override
    public List<String> GetTourNames() {
         return tourModelDAO.GetTourNames();
    }

    @Override
    public void UpdateTourItem(String chosenTourName, String description, int distance, String start, String end) {
        tourModelDAO.UpdateTourItem(chosenTourName,description,distance,start,end);
    }

    @Override
    public void GetImageRequest(String tourName, String start, String end) throws IOException {
        apiHandler.GetRequestMethod(tourName,start,end);
    }

    @Override
    public Image GetTourImage(String tourName, String start, String end) throws IOException {
        return apiHandler.GetImageTour(tourName,start,end);
    }

    @Override
    public void DeleteImage(String tourName) {
        tourModelDAO.DeleteImage(tourName);
    }


}
