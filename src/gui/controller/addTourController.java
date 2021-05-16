package gui.controller;

import businesslayer.tourManager;
import businesslayer.tourManagerFactory;
import gui.Main;
import gui.viewmodels.addTourViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class addTourController implements Initializable {

    private static final Logger logger = LogManager.getLogger(addTourController.class);

    public TextField tourName;
    public TextField tourDescription;
    public TextField tourDistance;
    public TextField tourStart;
    public TextField tourEnd;

    public addTourViewModel addTourViewModel = new addTourViewModel();

    public void addTourButton(ActionEvent actionEvent) throws IOException {

        addTourViewModel.addingTour();
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

    //Bindings to addTourViewModel
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tourName.textProperty().bindBidirectional(addTourViewModel.getTourName());
        tourDescription.textProperty().bindBidirectional(addTourViewModel.getTourDescription());
        tourDistance.textProperty().bindBidirectional(addTourViewModel.getTourDistance());
        tourStart.textProperty().bindBidirectional(addTourViewModel.getTourStart());
        tourEnd.textProperty().bindBidirectional(addTourViewModel.getTourEnd());

        logger.info("Bindings are made");
    }
}
