package gui.viewmodels;

import businesslayer.logManagerFactory;
import models.logModel;

import java.io.IOException;
import java.util.List;

public class logViewModel {

    //businessLayer communication
    private businesslayer.logManager logManager;

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
}
