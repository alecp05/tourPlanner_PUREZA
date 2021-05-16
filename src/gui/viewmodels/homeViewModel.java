package gui.viewmodels;

import businesslayer.logManagerFactory;
import businesslayer.tourManagerFactory;
import models.logModel;
import models.tourModel;

import java.io.IOException;
import java.util.List;

public class homeViewModel {

    //businessLayer communication
    private businesslayer.tourManager tourManager;
    private businesslayer.logManager logManager;

    public List<tourModel> gettingTourItems() throws IOException {
        tourManager = tourManagerFactory.GetTourManager();
        return tourManager.GetTourItems();
    }
    public List<logModel> gettingLogItems() throws IOException {
        logManager = logManagerFactory.GetLogManager();
        return logManager.GetLogItems();
    }

    public List<tourModel> searchingTourItems(String searchItem) throws IOException {
        tourManager = tourManagerFactory.GetTourManager();
        return tourManager.SearchTourItems(searchItem, false);
    }

    public List<logModel> searchingLogItems(String searchItem) throws IOException {
        logManager = logManagerFactory.GetLogManager();
        return logManager.SearchLogItems(searchItem, false);
    }
}
