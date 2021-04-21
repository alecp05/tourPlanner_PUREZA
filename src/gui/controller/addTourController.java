package gui.controller;

import businesslayer.tourManager;
import businesslayer.tourManagerFactory;
import gui.Main;
import javafx.event.ActionEvent;
import javafx.scene.control.TextField;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.IOException;

public class addTourController {

    private static final Logger logger = LogManager.getLogger(addTourController.class);

    public TextField tourName;
    public TextField tourDescription;
    public TextField tourDistance;
    public TextField tourStart;
    public TextField tourEnd;

    private businesslayer.tourManager tourManager;

    public void addTourButton(ActionEvent actionEvent) throws IOException {

        tourManager = tourManagerFactory.GetTourManager();

        String tempName = tourName.textProperty().getValue();
        //System.out.println(tempName);
        String tempDescription = tourDescription.textProperty().getValue();
        Integer tempDistance = 0;
        if(tempName != "") {
            tempDistance = Integer.parseInt(tourDistance.textProperty().getValue());
        }
        String tempStart = tourStart.textProperty().getValue();
        String tempEnd = tourEnd.textProperty().getValue();

        //insert to database
        if(tempName != ""){

            tourManager.InsertTourItem(tempName,tempDescription,tempDistance,tempStart,tempEnd);
            addMapImage(tempName,tempStart,tempEnd);
        }

        clearFields();

        //update tourView
        Main m = new Main();
        m.changeScene("views/tourView.fxml");

        logger.info("Add-Button clicked");
    }

    public void clearFields(){
        tourName.clear();
        tourDescription.clear();
        tourDistance.clear();
        tourStart.clear();
        tourEnd.clear();
    }

    public void addMapImage(String tourName, String start, String end) throws IOException {
        tourManager.GetImageRequest(tourName,start,end);
    }
}
