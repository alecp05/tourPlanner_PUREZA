package inputValidationTests;

import businesslayer.inputValidation.inputValidationImplementation;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class validationOperations {
    private inputValidationImplementation validationHandler;

    @Before
    public void setUp(){
        validationHandler = new inputValidationImplementation();
    }

    @Test
    public void testCheckFormatForDateDistanceTime_shouldBeInFormat(){
        assertTrue("Should follow the FormatRules",validationHandler.checkFormat("01-01-2021","100 km", "20 h"));
    }

    @Test
    public void testCheckFormatOnDate_shouldBeNotInFormat(){
        assertFalse("Should follow the FormatRules",validationHandler.checkFormat("0101-202","100 km", "20 h"));
    }

    @Test
    public void testCheckFormatOnDistance_shouldBeNotInFormat(){
        assertFalse("Should follow the FormatRules",validationHandler.checkFormat("01-01-2021","100km", "20 h"));
    }

    @Test
    public void testCheckFormatOnTime_shouldBeNotInFormat(){
        assertFalse("Should follow the FormatRules",validationHandler.checkFormat("01-01-2021","100 km", "22Hours"));
    }
}
