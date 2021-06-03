package databaseTests;

import dataaccesslayer.database.database;
import models.logModel;
import models.tourModel;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class databaseOperations {

    private database database;

    @Before
    public void setup() throws IOException {
        database = new database();
    }

    @Test
    public void testGettingTourItems_shouldGetFromDatabase(){
        List<tourModel> tours = new ArrayList<tourModel>();
        int checkCounter = 0;
        try{
            tours = database.GetTourItems();
            checkCounter = 1;
        }catch (Exception e){
            System.out.println("Could not get Items from Database");
        }

        assertTrue("The connection to the Database could not been set, or the" +
                "Items could not be recieved properly", checkCounter == 1);
    }

    @Test
    public void testGettingLogItems_shouldGetFromDatabase(){
        List<logModel> logs = new ArrayList<>();
        int checkNumber = 0;
        try{
            logs = database.GetLogItems();
            checkNumber = 1;
        }catch(Exception e){
            System.out.println("Could not get the Logs"+ e.getMessage());
        }
        assertTrue("The connection to the Database could not been set, or the" +
                "Items could not be received properly", checkNumber == 1);

    }

    @Test
    public void testGetTourNames_ShouldGetFromDatabase(){
        List<String> allTourNames= new ArrayList<>();
        allTourNames = database.GetTourNames();
        int checkNumber = 0;
        if(allTourNames != null){
            checkNumber = 1;
        }
        assertTrue("The conncetion to the Database could not been set, or the"+
                "Names could not be received properly", checkNumber == 1);
    }

    @Test
    public void testGetLogNames_ShouldGetFromDatabase(){
        List<String> allLogNames = new ArrayList<>();
        allLogNames = database.GetLogNames();

        int checkNumber = 0;
        if(allLogNames != null){
            checkNumber = 1;
        }
        assertTrue("The connection to the Database could not been set, or the"+
                "Names could not be received properly", checkNumber == 1);
    }

    @Test
    public void testInsertingNewTour_shouldInsertInDatabase(){
        try {
            database.InsertTourItems("TestingTour 01","Testing this Tour",100,"Test01", "Test02");
        }catch (Exception e){
            System.out.println("Problems with Inserting into Database");
        }
        List<String> allTourNames= new ArrayList<>();
        allTourNames = database.GetTourNames();
        int checkNumber = 0;
        for(int i = 0; i<allTourNames.size();i++){
            if(allTourNames.get(i).contains("TestingTour 01")){
                checkNumber = 1;
            }
        }
        assertTrue("The Item has not been properly inserted into the Database", checkNumber == 1);
    }

    @Test
    public void testInsertingNewLog_shouldInsertInDatabase(){
        try{
            database.InsertLogItems("TestingLog 01","01-06-2021","TestTour was super",
                    "120 km", "20 h", 9, "15 km/h", "The weather was super",20,
                    "Test01", "Test02");
        }catch (Exception e){
            System.out.println("Problems with Inserting into Database");
        }
        List<String> allLogNames = new ArrayList<>();
        allLogNames = database.GetLogNames();
        int checkCount = 0;
        for(int j = 0; j<allLogNames.size();j++){
            if(allLogNames.get(j).contains("TestingLog 01")){
                checkCount = 1;
            }
        }
        assertTrue("The Item has not been properly inserted into the Database", checkCount == 1);
    }

    @Test
    public void testDeletingATour_shouldDeleteInDatabase(){
        try {
            database.InsertTourItems("TestingTour 02","Testing this Tour02",100,"Test01", "Test02");
        }catch (Exception e){
            System.out.println("Problems with Inserting into Database");
        }
        int checkCounter = 0;
        try {
            database.DeleteTourItem("TestingTour 02", "Testing this Tour02");
            checkCounter = 1;
        }catch (Exception e){
            System.out.println("Could not Delete in Database");
        }

        assertTrue("The Item can not be deleted in Database",checkCounter == 1);
    }

    @Test
    public void testDeletingALog_shouldDeleteInDatabase(){
        try{
            database.InsertLogItems("TestingLog 02","02-02-2021","TestTour02 was super",
                    "120 km", "20 h", 9, "15 km/h", "The weather was super",20,
                    "Test01", "Test02");
        }catch (Exception e){
            System.out.println("Problems with Inserting into Database");
        }
        int checkCounter = 0;
        try {
            database.DeleteLogItem("02-02-2021","TestTour02 was super");
            checkCounter = 1;
        }catch (Exception e){
            System.out.println("Could not Delete in Database");
        }

        assertTrue("The Item can not be deleted in Database",checkCounter == 1);

    }
}
