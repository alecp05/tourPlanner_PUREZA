package mapQuestApiTests;

import businesslayer.mapQuestApi.apiHandler;
import javafx.scene.image.Image;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertTrue;

public class apiOperation {
    private apiHandler apiHandler;

    @Before
    public void setUp() {
        apiHandler = new apiHandler();
    }

    @Test
    public void testApiGetImageFromMapQuest_shouldReturnImage() throws IOException {
        Image tempImage;
        int checkNumber = 0;
        try {
            tempImage = apiHandler.GetImageTour("TestTour","Vienna","Burgenland");
            if(tempImage != null){
                checkNumber = 1;
            }
        }catch (Exception e){
            System.out.println("Could not convert the Tour from the API");
        }

        assertTrue("The Api has a problem to convert the Tour into an Image", checkNumber == 1);

    }
}
