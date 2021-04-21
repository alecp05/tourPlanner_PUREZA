package dataaccesslayer.file;

import dataaccesslayer.file.fileAccess;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;

public class filesystem implements fileAccess {

    private String filePath;

    public filesystem(){
        //get info from config file
        filePath = "src/tourImages/";
    };

    @Override
    public void SaveImageInFile(String completeURL, String tourName) throws IOException {

        URL url = new URL(completeURL);
        Image image = ImageIO.read(url);

        //create path with tourName
        tourName = tourName.replace(" ","");
        String fileDirectory = filePath + tourName + ".jpg";

        ImageIO.write((RenderedImage) image, "jpg", new File(fileDirectory));
    }

    @Override
    public void DeleteImage(String tourName) {

        tourName = tourName.replace(" ","");
        String path = "src/tourImages/"+tourName + ".jpg";

        File tmpDir = new File(path);
        boolean exists = tmpDir.exists();
        if(exists){
            tmpDir.delete();
            System.out.println("deleting file");
        }else {
            System.out.println("no file found");
        }
    }
}
