package gui.viewmodels;

import businesslayer.logManagerFactory;
import models.logModel;

import java.util.List;

public class logViewModel {

    //businessLayer communication
    private businesslayer.logManager logManager;

    public List<logModel> gettingLogItems(){
        logManager = logManagerFactory.GetLogManager();

        return logManager.GetLogItems();
    }

    public List<logModel> searchingLogItems(String selectedTour){
        logManager = logManagerFactory.GetLogManager();
        return logManager.SearchLogItems(selectedTour, false);
    }

    public void deletingLog(String date, String report){
        logManager = logManagerFactory.GetLogManager();
        logManager.DeleteLogItem(date, report);
    }
}
