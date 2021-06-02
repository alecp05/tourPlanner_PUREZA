package modelTests;

import org.junit.Test;
import models.tourModel;
import models.logModel;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class models {
    @Test
    public void testTourModel_AttributesShouldBeSet() throws IOException {
        tourModel tourModel = new tourModel("TourTest 01", "From Test01 to Test01", 15,"Test01","Test02" );

        //assertTrue(tourModel.getTourName() = "TourTest 01", "TourName is correctly set");
        assertEquals(tourModel.getTourName(),"TourTest 01");
        assertEquals(tourModel.getTourDescription(),"From Test01 to Test01");
        assertEquals(tourModel.getTourDistance(),15);
        assertEquals(tourModel.getTourStart(),"Test01");
        assertEquals(tourModel.getTourEnd(),"Test02");
    }

    @Test
    public void testLogModel_AttributesShouldBeSet() throws IOException{
        logModel logModel = new logModel("TourTest 02", "01-06-2021", "The Tour was awesome", "100 km", "12 h",
        10, "15 km/h", "No clouds and always Sunny",10 , "Test01", "Test02");

        assertEquals(logModel.getTourName(),"TourTest 02");
        assertEquals(logModel.getLogDate(),"01-06-2021");
        assertEquals(logModel.getLogReport(), "The Tour was awesome");
        assertEquals(logModel.getLogDistance(), "100 km");
        assertEquals(logModel.getLogTotalTime(), "12 h");
        assertEquals(logModel.getLogRating(), 10);
        assertEquals(logModel.getLogAverageSpeed(),"15 km/h");
        assertEquals(logModel.getLogWeatherCondition(),"No clouds and always Sunny");
        assertEquals(logModel.getLogBreaksTaken(), 10);
        assertEquals(logModel.getLogStartingPoint(),"Test01");
        assertEquals(logModel.getLogEndPoint(), "Test02");

    }
}
