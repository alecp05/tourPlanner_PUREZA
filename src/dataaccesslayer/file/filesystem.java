package dataaccesslayer.file;

import dataaccesslayer.file.fileAccess;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

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
}
