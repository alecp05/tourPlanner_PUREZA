package dataaccesslayer.file;

import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.*;
import dataaccesslayer.file.fileAccess;
import gui.controller.addLogController;
import models.logModel;
import models.tourModel;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.Image;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.util.List;

public class filesystem implements fileAccess {

    private static final Logger logger = LogManager.getLogger(filesystem.class);

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

        logger.info("TourImage hast been saved");
    }

    @Override
    public void DeleteImage(String tourName) {

        tourName = tourName.replace(" ","");
        String path = "src/tourImages/"+tourName + ".jpg";

        File tmpDir = new File(path);
        boolean exists = tmpDir.exists();
        if(exists){
            tmpDir.delete();
            logger.info("Image has been deleted");
        }else {
            logger.warn("Image not found");
        }
    }

    @Override
    public void SavePdfReport(List<tourModel> tours, List<logModel> logs) throws FileNotFoundException, MalformedURLException {
        String tourName = tours.get(0).tourName;
        //System.out.println(tourName);

        String path = "pdfs/"+ tourName.replace(" ","") +".pdf";

        //set Title
        Text title = new Text(tours.get(0).tourName).setBold().setFontSize(18f);
        Text newLine = new Text("\n   ");
        Text titleTour = new Text("\nTour").setUnderline().setFontSize(14f).setBold();
        Text titleLog = new Text("\nLogs").setUnderline().setFontSize(14f).setBold();

        //set Image
        tourName = tourName.replace(" ","");
        String imagePath = "src/tourImages/"+tourName + ".jpg";

        com.itextpdf.layout.element.Image img= null;
        File tmpDir = new File(imagePath);
        boolean exists = tmpDir.exists();
        if(exists){
            ImageData imageData = ImageDataFactory.create(imagePath);
            img= new com.itextpdf.layout.element.Image(imageData);
            img.setHeight(300f);
            img.setWidth(400f);
            logger.info("Image has been set into PDF");
        }else {
            logger.warn("Image not found");
        }

        //set TourTable
        float columnWidthTour[] = {90f, 150f, 50f,100f,100f};
        Table tableTour = new Table(columnWidthTour);
        tableTour.addCell("Tour-Name").addCell("Description").addCell("Distance (km)").addCell("Start-Point").addCell("End-Point");
        tableTour.addCell(tours.get(0).tourName).addCell(tours.get(0).tourDescription).addCell(Integer.toString(tours.get(0).tourDistance))
            .addCell(tours.get(0).tourStart)
            .addCell(tours.get(0).tourEnd);

        //Set LogTable Part 1
        float columnWidthLog01[] = {90f, 100f, 160f,60f,80f};
        Table tableLog1 = new Table(columnWidthLog01);
        tableLog1.addCell("Tour-Name").addCell("Date").addCell("Report").addCell("Distance (km)").addCell("Total Time");

        //Set LogTable Part 2
        float columnWidthLog02[] = {40f, 80f, 120f,40f,105f,105f};
        Table tableLog2 = new Table(columnWidthLog02);
        tableLog2.addCell("Rating").addCell("Average-Speed").addCell("Weather").addCell("Breaks").addCell("Start-Point").addCell("End-Point");

        //Set Log
        if(logs.size()>0){
            for(int i =0; i<logs.size();i++){
                tableLog1.addCell(logs.get(i).tourName).addCell(logs.get(i).logDate).addCell(logs.get(i).logReport).addCell(logs.get(i).logDistance)
                        .addCell(logs.get(i).logTotalTime);
                tableLog2.addCell(Integer.toString(logs.get(i).logRating)).addCell(logs.get(i).logAverageSpeed).addCell(logs.get(i).logWeatherCondition)
                        .addCell(Integer.toString(logs.get(i).logBreaksTaken)).addCell(logs.get(i).logStartingPoint).addCell(logs.get(i).logEndPoint);
            }
            createStatisticsReport(tourName,logs);
        }else {
            logger.warn("No Logs have been found");
        }

        //set pdf
        Paragraph paragraph1 = new Paragraph(title).add(newLine);
        Paragraph newLineParagraph = new Paragraph(newLine);
        Paragraph paragraphTour = new Paragraph(titleTour);
        Paragraph paragraphLog = new Paragraph(titleLog);

        PdfWriter pdfWriter = new PdfWriter(path);
        PdfDocument pdfDocument = new PdfDocument(pdfWriter);
        pdfDocument.addNewPage();

        Document document = new Document(pdfDocument);
        document.add(paragraph1);
        if(img != null)
            document.add(img);
        document.add(paragraphTour);
        document.add(tableTour);
        document.add(paragraphLog);
        document.add(tableLog1);
        document.add(newLineParagraph);
        document.add(tableLog2);

        logger.info("Tour-PDF has been created");

        document.close();
    }

    private void createStatisticsReport(String tourName,List<logModel> logs) throws FileNotFoundException {

        Text titleLog = new Text("\nLogs").setFontSize(18f).setBold();
        Text summarizedTime = new Text("\nSummarized Total-Time:\n").setUnderline().setFontSize(14f);
        Text summarizedDistance = new Text("\nSummarized Distance:\n").setUnderline().setFontSize(14f);

        String path = "pdfs/"+ tourName + "-statistics.pdf";


        //Set LogTable Part 2
        float columnWidth[] = {100f, 100f};
        Table tableLog = new Table(columnWidth);
        tableLog.addCell("Total-Time").addCell("Distance");

        int sumTime = 0;
        int sumDistance = 0;

        for(int j =0; j<logs.size();j++){
            tableLog.addCell(logs.get(j).logTotalTime).addCell(logs.get(j).logDistance);
            String totalTime [] = logs.get(j).logTotalTime.split(" ");
            String totalDistance [] = logs.get(j).logDistance.split(" ");

            sumTime = sumTime + Integer.parseInt(totalTime[0]);
            sumDistance = sumDistance + Integer.parseInt(totalDistance[0]);
        }

        Text totalTimes = new Text(Integer.toString(sumTime));
        Text totalDistance = new Text(Integer.toString(sumDistance));

        //paragraphs
        Paragraph paragraphTime = new Paragraph(summarizedTime).add(totalTimes);
        Paragraph paragraphDistance = new Paragraph(summarizedDistance).add(totalDistance);


        //set pdf statistics
        Paragraph paragraph1 = new Paragraph(titleLog);
        PdfWriter pdfWriter = new PdfWriter(path);
        PdfDocument pdfDocument = new PdfDocument(pdfWriter);
        pdfDocument.addNewPage();

        Document document = new Document(pdfDocument);
        document.add(paragraph1);
        document.add(tableLog);
        document.add(paragraphTime);
        document.add(paragraphDistance);

        logger.info("Statistic-PDF has been created");
        document.close();

    }
}
