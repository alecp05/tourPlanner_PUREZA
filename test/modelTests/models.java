package modelTests;

import org.junit.Before;
import org.junit.Test;
import models.tourModel;
import models.logModel;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class models {
    private tourModel tourModel;
    private logModel logModel;

    @Before
    public void setUp(){
        tourModel = new tourModel("TourTest 01", "From Test01 to Test01", 15,"Test01","Test02" );
        logModel = new logModel("TourTest 02", "01-06-2021", "The Tour was awesome", "100 km", "12 h",
                10, "15 km/h", "No clouds and always Sunny",10 , "Test01", "Test02");
    }

    @Test
    public void testTourModelName_AttributeShouldBeSet() throws IOException {
        assertEquals(tourModel.getTourName(),"TourTest 01");
    }

    @Test
    public void testTourModelDescription_AttributeShouldBeSet() throws IOException {
        assertEquals(tourModel.getTourDescription(),"From Test01 to Test01");
    }

    @Test
    public void testTourModelDistance_AttributeShouldBeSet() throws IOException {
        assertEquals(tourModel.getTourDistance(),15);
    }


    @Test
    public void testTourModelStart_AttributeShouldBeSet() throws IOException {
        assertEquals(tourModel.getTourStart(),"Test01");
    }

    @Test
    public void testTourModelEnd_AttributeShouldBeSet() throws IOException {
        assertEquals(tourModel.getTourEnd(),"Test02");
    }

    @Test
    public void testLogModelName_AttributeShouldBeSet() throws IOException{
        assertEquals(logModel.getTourName(),"TourTest 02");
    }

    @Test
    public void testLogModelLogDate_AttributeShouldBeSet() throws IOException{
        assertEquals(logModel.getLogDate(),"01-06-2021");
    }

    @Test
    public void testLogModelLReport_AttributeShouldBeSet() throws IOException{
        assertEquals(logModel.getLogReport(), "The Tour was awesome");
    }

    @Test
    public void testLogModelLDistance_AttributeShouldBeSet() throws IOException{
        assertEquals(logModel.getLogDistance(), "100 km");
    }
    @Test
    public void testLogModelLTotalTime_AttributeShouldBeSet() throws IOException{
        assertEquals(logModel.getLogTotalTime(), "12 h");
    }
    @Test
    public void testLogModelLRating_AttributeShouldBeSet() throws IOException{
        assertEquals(logModel.getLogRating(), 10);
    }
    @Test
    public void testLogModelLAverageSpeed_AttributeShouldBeSet() throws IOException{
        assertEquals(logModel.getLogAverageSpeed(),"15 km/h");
    }
    @Test
    public void testLogModelLWeatherCondition_AttributeShouldBeSet() throws IOException{
        assertEquals(logModel.getLogWeatherCondition(),"No clouds and always Sunny");
    }
    @Test
    public void testLogModelLBreaks_AttributeShouldBeSet() throws IOException{
        assertEquals(logModel.getLogBreaksTaken(), 10);
    }
    @Test
    public void testLogModelLStart_AttributeShouldBeSet() throws IOException{
        assertEquals(logModel.getLogStartingPoint(),"Test01");
    }
    @Test
    public void testLogModelLEnd_AttributeShouldBeSet() throws IOException{
        assertEquals(logModel.getLogEndPoint(), "Test02");

    }
}
