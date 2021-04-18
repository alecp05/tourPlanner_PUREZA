package dataaccesslayer;

import models.tourModel;

import java.util.List;

public class tourModelDAO {

    private dataAccess dataAccess;

    public tourModelDAO(){
        dataAccess = new database();
    }

    public List<tourModel> GetTourItems(){return dataAccess.GetTourItems();}

    public void InsertTourItems(String name, String description, int distance, String start, String end){
        dataAccess.InsertTourItems(name, description, distance, start, end);
    }
}
