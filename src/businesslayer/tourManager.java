package businesslayer;

import javafx.scene.image.Image;
import models.tourModel;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

public interface tourManager {

    public List<tourModel> GetTourItems();
    public List<tourModel> SearchTourItems(String itemName, boolean caseSensitive);
    public void InsertTourItem(String name, String description, int distance, String start, String end);
    public void DeleteTourItem(String name, String description);
    public List<String> GetTourNames();
    public void UpdateTourItem(String chosenTourName, String description, int distance, String start, String end);
    public void GetImageRequest(String tourName, String start, String end) throws IOException;
    public Image GetTourImage(String tourName, String start,String end) throws IOException;
    public void DeleteImage(String tourName);
    public void GetTourNameForReport(String tourName) throws FileNotFoundException, MalformedURLException;
}
