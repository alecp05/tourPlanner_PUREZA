package businesslayer.report;

import dataaccesslayer.daos.logModelDAO;
import dataaccesslayer.daos.tourModelDAO;
import models.logModel;
import models.tourModel;

import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

public class reportHandler {

    tourModelDAO tourModelDAO = new tourModelDAO();
    logModelDAO logModelDao = new logModelDAO();

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

        tourModelDAO.SavePdfReport(chosenTourItems,chosenLogItems);
    }
}
