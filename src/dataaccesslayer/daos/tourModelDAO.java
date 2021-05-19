package dataaccesslayer.daos;

import dataaccesslayer.database.dataAccess;
import dataaccesslayer.database.database;
import dataaccesslayer.file.filesystem;
import models.logModel;
import models.tourModel;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

public class tourModelDAO {

    private dataaccesslayer.database.dataAccess dataAccess;
    private dataaccesslayer.file.fileAccess fileAccess;

    public tourModelDAO() throws IOException {
        dataAccess = new database();
        fileAccess = new filesystem();
    }

    public List<tourModel> GetTourItems(){return dataAccess.GetTourItems();}

    public void InsertTourItems(String name, String description, int distance, String start, String end){
        dataAccess.InsertTourItems(name, description, distance, start, end);
    }

    public void DeleteTourItems(String name, String description){
        dataAccess.DeleteTourItem(name, description);
    };
    public List<String> GetTourNames(){
        return dataAccess.GetTourNames();
    };

    public void UpdateTourItem(String chosenTourName, String description, int distance, String start, String end){
        dataAccess.UpdateTourItem(chosenTourName,description,distance,start,end);
    };

    public void DeleteImage(String tourName){
        fileAccess.DeleteImage(tourName);
    }
    public void SavePdfReport(List<tourModel> tours, List<logModel> logs) throws FileNotFoundException, MalformedURLException {
        fileAccess.SavePdfReport(tours,logs);
    };
    public BufferedImage getGalleryImage(String fileName){return fileAccess.getGalleryImages(fileName);};
    public List<String> getGalleryNames(){return fileAccess.getGalleryNames();};
    public void saveImageForGallery(BufferedImage img, String imagePath){fileAccess.saveImageForGallery(img,imagePath);}
}
