package configurationTests;

import dataaccesslayer.configuration.configurationHandler;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class configOperation {
    private configurationHandler configurationHandler;

    @Before
    public void setup() throws IOException {
        configurationHandler = new configurationHandler();
    }

    @Test
    public void testURL_shouldBeSetFromConfigFile(){
        assertEquals(configurationHandler.getUrl(), "jdbc:postgresql://localhost:5432/tourPlanner");
    }

    @Test
    public void testUser_shouldBeSetFromConfigFile(){
        assertEquals(configurationHandler.getUser(), "postgres");
    }

    @Test
    public void testPassword_shouldBeSetFromConfigFile(){
        assertEquals(configurationHandler.getPassword(), "alecUser");
    }


}
