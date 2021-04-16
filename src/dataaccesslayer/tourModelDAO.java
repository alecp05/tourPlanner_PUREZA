package dataaccesslayer;

import models.tourModel;

import java.util.List;

public class tourModelDAO {

    private dataAccess dataAccess;

    public tourModelDAO(){
        dataAccess = new database();
    }

    public List<tourModel> GetTourItems(){return dataAccess.GetTourItems();}
}
