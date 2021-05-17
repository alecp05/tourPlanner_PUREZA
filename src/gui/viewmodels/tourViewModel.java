package gui.viewmodels;

import businesslayer.jsonConverter.jsonConverterHandler;
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
    private jsonConverterHandler jsonConverterHandler;

    public List<tourModel> gettingTourItems() throws IOException {
        tourManager = tourManagerFactory.GetTourManager();
        return tourManager.GetTourItems();
    }

    public List<tourModel> searchingTours(String tourName) throws IOException {
        tourManager = tourManagerFactory.GetTourManager();
        return tourManager.SearchTourItems(tourName, false);
    }
    public void deletingTour(String tourName, String description) throws IOException {
        tourManager = tourManagerFactory.GetTourManager();
        tourManager.DeleteTourItem(tourName,description);
    }
    public void deletingImage(String tourName) throws IOException {
        tourManager = tourManagerFactory.GetTourManager();
        tourManager.DeleteImage(tourName);
    }
    public Image gettingTourImage(String tourName, String tourStart, String tourEnd) throws IOException {
        tourManager = tourManagerFactory.GetTourManager();
        return tourManager.GetTourImage(tourName, tourStart, tourEnd);
    }
    public void gettingTourNameForReport(String tourName) throws IOException {
        tourManager = tourManagerFactory.GetTourManager();
        tourManager.GetTourNameForReport(tourName);
    }

    public void importAction() throws IOException {
        jsonConverterHandler = new jsonConverterHandler();
        jsonConverterHandler.fileChoosing();
    }

    public void exportAction(String tourName) throws IOException {
        jsonConverterHandler = new jsonConverterHandler();
        jsonConverterHandler.exportingTour(tourName);
    }
}
