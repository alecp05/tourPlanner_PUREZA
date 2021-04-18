package dataaccesslayer;

import models.logModel;
import models.tourModel;

import java.util.List;

public class fileAccess implements dataAccess{

    private String connectionString;

    public fileAccess(){
        //get info from config file
        connectionString = "...";
        //establish DB connection
    };

    @Override
    public List<tourModel> GetTourItems() {
        return null;
    }

    @Override
    public List<logModel> GetLogItems() {
        return null;
    }

    @Override
    public void InsertTourItems(String name, String description, int distance, String start, String end) {

    }
}
