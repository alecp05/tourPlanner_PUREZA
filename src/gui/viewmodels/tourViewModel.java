package gui.viewmodels;

import businesslayer.tourManagerFactory;
import javafx.scene.image.Image;
import models.tourModel;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

public class tourViewModel {
    //businessLayer communication
    private businesslayer.tourManager tourManager;

    public List<tourModel> gettingTourItems(){
        tourManager = tourManagerFactory.GetTourManager();
        return tourManager.GetTourItems();
    }

    public List<tourModel> searchingTours(String tourName){
        tourManager = tourManagerFactory.GetTourManager();
        return tourManager.SearchTourItems(tourName, false);
    }
    public void deletingTour(String tourName, String description){
        tourManager = tourManagerFactory.GetTourManager();
        tourManager.DeleteTourItem(tourName,description);
    }
    public void deletingImage(String tourName){
        tourManager = tourManagerFactory.GetTourManager();
        tourManager.DeleteImage(tourName);
    }
    public Image gettingTourImage(String tourName, String tourStart, String tourEnd) throws IOException {
        tourManager = tourManagerFactory.GetTourManager();
        return tourManager.GetTourImage(tourName, tourStart, tourEnd);
    }
    public void gettingTourNameForReport(String tourName) throws FileNotFoundException, MalformedURLException {
        tourManager = tourManagerFactory.GetTourManager();
        tourManager.GetTourNameForReport(tourName);
    }
}
