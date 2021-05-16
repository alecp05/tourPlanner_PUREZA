package gui.controller;

import businesslayer.tourManager;
import businesslayer.tourManagerFactory;
import gui.Main;
import gui.viewmodels.modifyTourViewModel;
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


    public modifyTourViewModel modifyTourViewModel = new modifyTourViewModel();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Binding to modifyTourViewModel
        tourDescription.textProperty().bindBidirectional(modifyTourViewModel.getTourDescription());
        tourDistance.textProperty().bindBidirectional(modifyTourViewModel.getTourDistance());
        tourStart.textProperty().bindBidirectional(modifyTourViewModel.getTourStart());
        tourEnd.textProperty().bindBidirectional(modifyTourViewModel.getTourEnd());


        tourChoices = FXCollections.observableArrayList();

        List<String> tourNames = new ArrayList<String>();
        tourNames = modifyTourViewModel.gettingTourNames();

        tourChoices.addAll(tourNames);
        tourChoiceBox.getItems().addAll(tourChoices);

        logger.info("Initialized modifyTourView");

    }

    public void editButton(ActionEvent actionEvent) throws IOException {
        String chosenTour = tourChoiceBox.getValue();
        int indexOfTour = tourChoices.indexOf(chosenTour);
        if(chosenTour!=null){
            modifyTourViewModel.editingTour(chosenTour);

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
