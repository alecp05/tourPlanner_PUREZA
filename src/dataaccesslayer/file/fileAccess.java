package dataaccesslayer.file;

import models.logModel;
import models.tourModel;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

public interface fileAccess {
    public void SaveImageInFile(String completeURL, String tourName) throws IOException;
    public void DeleteImage(String tourName);
    public void SavePdfReport(List<tourModel> tours, List<logModel> logs) throws FileNotFoundException, MalformedURLException;
    public BufferedImage getGalleryImages(String fileName);
    public List<String> getGalleryNames();
    public void saveImageForGallery(BufferedImage savingImage, String path);
    public void deleteImageFromGallery(String galleryImageName);
}
