package dataaccesslayer;

import models.logModel;

import java.util.List;

public class logModelDAO {
    private dataAccess dataAccess;

    public logModelDAO(){
        dataAccess = new database();
    }

    public List<logModel> GetLogItems(){return dataAccess.GetLogItems();}
}
