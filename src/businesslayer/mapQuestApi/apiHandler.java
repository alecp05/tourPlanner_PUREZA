package businesslayer.mapQuestApi;

import dataaccesslayer.file.fileAccess;
import dataaccesslayer.file.filesystem;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URL;

public class apiHandler {

    private static dataaccesslayer.file.fileAccess fileAccess;

    public apiHandler(){
        fileAccess = new filesystem();
    }
    /*
    public static void main(String[] args) throws IOException, InterruptedException {

        URL url = new URL("https://www.mapquestapi.com/staticmap/v5/map?start=New+York,NY&end=Washington,DC&size=500,400@2x&key=ElnXWAS1plaBUPnCt1pkzSDxAbE4yv5m");
        Image image = ImageIO.read(url);
        ImageIO.write((RenderedImage) image, "jpg", new File("./tourImages/image01.jpg"));

        GetRequestMethod("Tour 1", "Floridsdorf", "Burgenland");

    }*/
    public static void GetRequestMethod(String tourName, String startPoint, String endPoint) throws IOException {

        String key = "ElnXWAS1plaBUPnCt1pkzSDxAbE4yv5m";
        String urlString = "https://www.mapquestapi.com/staticmap/v5/map?";
        String start = "start=" + startPoint +"|flag-start&";
        String end = "end=" + endPoint + "|flag-end&";
        String size = "size=500,400@2x&";
        String keyString = "key=" + key;

        String completeURL = urlString + start + end + size + keyString;

        fileAccess.SaveImageInFile(completeURL, tourName);


    }
}
