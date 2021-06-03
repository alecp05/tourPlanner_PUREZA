package gui.viewmodels;

import businesslayer.tourImplementation;
import javafx.stage.FileChooser;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class galleryViewModel {
    private businesslayer.tourManager tourManager = new tourImplementation();

    public galleryViewModel() throws IOException {
    }
    public List<String> getGalleryNames(){
        List<String> galleryNames = new ArrayList<>();
        galleryNames = tourManager.getGalleryNames();
        return galleryNames;
    };
    public BufferedImage getGalleryImage(String filename){
        return tourManager.getGalleryImages(filename);
    }
    public void uploadingButton() throws IOException {
        FileChooser fc = new FileChooser();
        File selectedFile = fc.showOpenDialog(null);

        if(selectedFile != null){
            BufferedImage img = ImageIO.read(new File(selectedFile.toString()));
            String pathForImage = selectedFile.getName();

            tourManager.saveImageForGallery(img, pathForImage);
            //System.out.println(pathForImage);
        }
    }

    public void deletingButton(String currentItemName) {
        tourManager.deleteImageFromGallery(currentItemName);

    }
}
