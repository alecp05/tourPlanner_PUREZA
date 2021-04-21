package gui.controller;

import businesslayer.tourManager;
import businesslayer.tourManagerFactory;
import gui.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import models.tourModel;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class modifyTourController implements Initializable {
    private static final Logger logger = LogManager.getLogger(modifyTourController.class);

    private ObservableList<String> tourChoices;

    @FXML
    private ChoiceBox<String> tourChoiceBox;

    public TextField tourDescription;
    public TextField tourDistance;
    public TextField tourStart;
    public TextField tourEnd;

    private businesslayer.tourManager tourManager;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tourManager = tourManagerFactory.GetTourManager();
        tourChoices = FXCollections.observableArrayList();

        List<String> tourNames = new ArrayList<String>();
        tourNames = tourManager.GetTourNames();

        tourChoices.addAll(tourNames);
        tourChoiceBox.getItems().addAll(tourChoices);

        logger.info("Initialized modifyTourView");

    }

    public void editButton(ActionEvent actionEvent) throws IOException {
        String chosenTour = tourChoiceBox.getValue();
        int indexOfTour = tourChoices.indexOf(chosenTour);
        if(chosenTour!=null){
            System.out.println(chosenTour + " " +  indexOfTour);

            //get inputs of textFields
            String tempDescription = tourDescription.textProperty().getValue();
            int tempDistance = Integer.parseInt(tourDistance.textProperty().getValue());
            String tempStart = tourStart.textProperty().getValue();
            String tempEnd = tourEnd.textProperty().getValue();

            //update in database
            tourManager.UpdateTourItem(chosenTour,tempDescription,tempDistance,tempStart,tempEnd);

            //delete and create nur jpg-image
            tourManager.DeleteImage(chosenTour);
            tourManager.GetImageRequest(chosenTour,tempStart,tempEnd);

            clearFields();

            //update tourView
            Main m = new Main();
            m.changeScene("views/tourView.fxml");

            logger.info("Edit-Button clicked");

        }
    }

    public void clearFields(){
        tourDescription.clear();
        tourDistance.clear();
        tourStart.clear();
        tourEnd.clear();
    }
}
