package gui.viewmodels;

import businesslayer.logManagerFactory;
import businesslayer.tourManagerFactory;
import models.logModel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class logViewModel {

    //businessLayer communication
    private businesslayer.logManager logManager;
    private businesslayer.tourManager tourManager;

    public List<logModel> gettingLogItems() throws IOException {
        logManager = logManagerFactory.GetLogManager();

        return logManager.GetLogItems();
    }

    public List<logModel> searchingLogItems(String selectedTour) throws IOException {
        logManager = logManagerFactory.GetLogManager();
        return logManager.SearchLogItems(selectedTour, false);
    }

    public void deletingLog(String date, String report) throws IOException {
        logManager = logManagerFactory.GetLogManager();
        logManager.DeleteLogItem(date, report);
    }

    public List<String> gettingTours() throws IOException {
        tourManager = tourManagerFactory.GetTourManager();

        List<String> tourNames = new ArrayList<String>();
        tourNames = tourManager.GetTourNames();

        return tourNames;
    }


    public List<logModel> gettingTheLogs(String tourName) throws IOException {
        logManager = logManagerFactory.GetLogManager();
        List<logModel> allLogs = logManager.GetLogItems();
        ArrayList<logModel> specifiedLogs = new ArrayList<logModel>();

        for(int i = 0; i<allLogs.size();i++){
            if (allLogs.get(i).tourName.equals(tourName)) {
                specifiedLogs.add(allLogs.get(i));
            }
        }

        return specifiedLogs;
    }
}
