package gui.viewmodels;

import businesslayer.inputValidation.inputValidationImplementation;
import businesslayer.inputValidation.inputValidationManager;
import businesslayer.tourManagerFactory;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.scene.control.TextField;
import lombok.Getter;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class modifyTourViewModel {

    private static final Logger logger = LogManager.getLogger(modifyTourViewModel.class);

    @Getter
    private final StringProperty tourDescription = new SimpleStringProperty("");
    @Getter
    private final StringProperty tourDistance = new SimpleStringProperty("");
    @Getter
    private final StringProperty tourStart = new SimpleStringProperty("");
    @Getter
    private final StringProperty tourEnd = new SimpleStringProperty("");

    private businesslayer.tourManager tourManager;

    public inputValidationManager inputValidationManager = new inputValidationImplementation();

    public List<String> gettingTourNames() throws IOException {
        tourManager = tourManagerFactory.GetTourManager();

        List<String> tourNames = new ArrayList<String>();
        tourNames = tourManager.GetTourNames();

        return tourNames;
    }

    public int editingTour(String chosenTour) throws IOException {
        tourManager = tourManagerFactory.GetTourManager();
        //get inputs of textFields
        String tempDescription = tourDescription.getValue();
        Integer tempDistance = 0;
        if(tourDistance.getValue() != "") {
            tempDistance = Integer.parseInt(tourDistance.getValue());
        }
        String tempStart = tourStart.getValue();
        String tempEnd = tourEnd.getValue();

        if((chosenTour!=null) && (tempDistance != 0) && (tempStart != "") && (tempEnd != "")){
            //update in database
            tourManager.UpdateTourItem(chosenTour,tempDescription,tempDistance,tempStart,tempEnd);

            //delete and create nur jpg-image
            tourManager.DeleteImage(chosenTour);
            tourManager.GetImageRequest(chosenTour,tempStart,tempEnd);
            return 1;
        }
        logger.warn("Empty UserInput");
        return 2;

    }

    //inputValidation
    public void setFieldRestrictions(TextField tourDistance, TextField tourStart, TextField tourEnd){
        inputValidationManager.onlyNumbers(tourDistance);
        inputValidationManager.onlyCharacters(tourStart);
        inputValidationManager.onlyCharacters(tourEnd);
    }
}
