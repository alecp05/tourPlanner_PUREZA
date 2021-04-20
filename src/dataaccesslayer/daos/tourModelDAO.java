package dataaccesslayer.daos;

import dataaccesslayer.database.dataAccess;
import dataaccesslayer.database.database;
import models.tourModel;

import java.util.List;

public class tourModelDAO {

    private dataaccesslayer.database.dataAccess dataAccess;

    public tourModelDAO(){
        dataAccess = new database();
    }

    public List<tourModel> GetTourItems(){return dataAccess.GetTourItems();}

    public void InsertTourItems(String name, String description, int distance, String start, String end){
        dataAccess.InsertTourItems(name, description, distance, start, end);
    }

    public void DeleteTourItems(String name, String description){
        dataAccess.DeleteTourItem(name, description);
    };
    public List<String> GetTourNames(){
        return dataAccess.GetTourNames();
    };

    public void UpdateTourItem(String chosenTourName, String description, int distance, String start, String end){
        dataAccess.UpdateTourItem(chosenTourName,description,distance,start,end);
    };
}
