package dataaccesslayer;

import models.logModel;
import models.tourModel;

import java.util.List;

public class tourModelDAO {

    private dataAccess dataAccess;

    public tourModelDAO(){
        dataAccess = new database();
    }

    public List<tourModel> GetTourItems(){return dataAccess.GetTourItems();}
    public List<logModel> GetLogItems(){return dataAccess.GetLogItems();}
}
