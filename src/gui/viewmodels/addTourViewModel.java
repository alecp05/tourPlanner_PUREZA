package gui.viewmodels;

import businesslayer.inputValidation.inputValidationImplementation;
import businesslayer.inputValidation.inputValidationManager;
import businesslayer.tourManagerFactory;
import gui.controller.addTourController;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.scene.control.TextField;
import lombok.Getter;
import models.tourModel;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.List;


public class addTourViewModel {

    private static final Logger logger = LogManager.getLogger(addTourViewModel.class);

    private businesslayer.tourManager tourManager;

    @Getter
    private final StringProperty tourName = new SimpleStringProperty("");
    @Getter
    private final StringProperty tourDescription = new SimpleStringProperty("");
    @Getter
    private final StringProperty tourDistance = new SimpleStringProperty("");
    @Getter
    private final StringProperty tourStart = new SimpleStringProperty("");
    @Getter
    private final StringProperty tourEnd = new SimpleStringProperty("");

    public inputValidationManager inputValidationManager = new inputValidationImplementation();

    public int addingTour() throws IOException {
        tourManager = tourManagerFactory.GetTourManager();

        String tempName = tourName.getValue();
        //System.out.println(tempName);
        String tempDescription = tourDescription.getValue();
        Integer tempDistance = 0;
        if(tourDistance.getValue() != "") {
            tempDistance = Integer.parseInt(tourDistance.getValue());
        }
        String tempStart = tourStart.getValue();
        String tempEnd = tourEnd.getValue();

        //Check if TourName is already taken
        List<tourModel> allTours = tourManager.GetTourItems();
        for(int i = 0; i < allTours.size();i++){
            if(allTours.get(i).tourName.equals(tempName)){
                logger.warn("TourName is already been taken");
                return 3;
            }
        }

        //insert to database if Fields are not empty
        if((tempName != "") && (tempDistance != 0) && (tempStart != "") && (tempEnd != "")){
            tourManager.InsertTourItem(tempName,tempDescription,tempDistance,tempStart,tempEnd);
            addMapImage(tempName,tempStart,tempEnd);
            //System.out.println(tempDescription+tempDistance+tempStart+tempEnd);
            return 1;
        }
        logger.warn("Empty UserInput");
        return 2;
    }

    public void addMapImage(String tourName, String start, String end) throws IOException {
        tourManager = tourManagerFactory.GetTourManager();
        tourManager.GetImageRequest(tourName,start,end);
    }

    //inputValidation
    public void setFieldRestrictions(TextField tourDistance, TextField tourStart, TextField tourEnd){
        inputValidationManager.onlyNumbers(tourDistance);
        inputValidationManager.onlyCharacters(tourStart);
        inputValidationManager.onlyCharacters(tourEnd);
    }
}
