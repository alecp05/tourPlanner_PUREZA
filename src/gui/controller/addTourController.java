package gui.controller;

import businesslayer.inputValidation.inputValidationImplementation;
import businesslayer.inputValidation.inputValidationManager;
import businesslayer.tourManager;
import businesslayer.tourManagerFactory;
import gui.Main;
import gui.viewmodels.addTourViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
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

        int tempNumber = addTourViewModel.addingTour();

        if(tempNumber == 2){
            Parent root;
            try {
                root = FXMLLoader.load(getClass().getClassLoader().getResource("gui/views/emptyFieldsView.fxml"));
                Stage stage = new Stage();
                stage.setTitle("Error on Input");
                stage.setScene(new Scene(root, 700, 450));
                stage.show();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }else if(tempNumber == 3)
        {
            Parent root;
            try {
                root = FXMLLoader.load(getClass().getClassLoader().getResource("gui/views/tourNameTakenView.fxml"));
                Stage stage = new Stage();
                stage.setTitle("Taken Name");
                stage.setScene(new Scene(root, 700, 450));
                stage.show();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }else
            {
            clearFields();
            //update tourView
            Main m = new Main();
            m.changeScene("views/tourView.fxml");
        }

        logger.info("Add-Button clicked");
    }

    public void clearFields(){
        tourName.clear();
        tourDescription.clear();
        tourDistance.clear();
        tourStart.clear();
        tourEnd.clear();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //Bindings to addTourViewModel
        tourName.textProperty().bindBidirectional(addTourViewModel.getTourName());
        tourDescription.textProperty().bindBidirectional(addTourViewModel.getTourDescription());
        tourDistance.textProperty().bindBidirectional(addTourViewModel.getTourDistance());
        tourStart.textProperty().bindBidirectional(addTourViewModel.getTourStart());
        tourEnd.textProperty().bindBidirectional(addTourViewModel.getTourEnd());

        //set input Validations
        addTourViewModel.setFieldRestrictions(tourDistance,tourStart,tourEnd);

        logger.info("Bindings are made");
    }
}
