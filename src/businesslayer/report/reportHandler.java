package businesslayer.report;

import dataaccesslayer.daos.logModelDAO;
import dataaccesslayer.daos.tourModelDAO;
import gui.controller.tourViewController;
import models.logModel;
import models.tourModel;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

public class reportHandler {

    private static final Logger logger = LogManager.getLogger(reportHandler.class);

    tourModelDAO tourModelDAO = new tourModelDAO();
    logModelDAO logModelDao = new logModelDAO();

    public reportHandler() throws IOException {
    }

    public void GetTourNameForReport(String tourName) throws FileNotFoundException, MalformedURLException {
        //System.out.println("report " + tourName);

        List<tourModel> tourItems = tourModelDAO.GetTourItems();
        List<logModel> logItems = logModelDao.GetLogItems();

        List<tourModel> chosenTourItems = new ArrayList<>();
        List<logModel> chosenLogItems = new ArrayList<>();

        for(int i= 0; i<tourItems.size();i++){
            if(tourItems.get(i).tourName.contains(tourName)){
                chosenTourItems.add(tourItems.get(i));
            }
        }
        for(int i= 0; i<logItems.size();i++){
            if(logItems.get(i).tourName.contains(tourName)){
                chosenLogItems.add(logItems.get(i));
            }
        }

        logger.info("Getting Tours and Logs for PDF");
        tourModelDAO.SavePdfReport(chosenTourItems,chosenLogItems);

    }
}
