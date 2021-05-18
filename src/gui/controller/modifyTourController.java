package gui.controller;

import businesslayer.tourManager;
import businesslayer.tourManagerFactory;
import gui.Main;
import gui.viewmodels.modifyTourViewModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lombok.SneakyThrows;
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


    @SneakyThrows
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

        //fill the TextFields with chosen TourInfos
        setTextInFields();

        //set input ValidationsRestrictions
        modifyTourViewModel.setFieldRestrictions(tourDistance,tourStart,tourEnd);

        logger.info("Initialized modifyTourView");

    }

    public void editButton(ActionEvent actionEvent) throws IOException {
        String chosenTour = tourChoiceBox.getValue();
        int indexOfTour = tourChoices.indexOf(chosenTour);
        if(modifyTourViewModel.editingTour(chosenTour) == 2){
            Parent root;
            try {
                root = FXMLLoader.load(getClass().getClassLoader().getResource("gui/views/emptyFieldsView.fxml"));
                Stage stage = new Stage();
                stage.setTitle("Error on Input");
                stage.setScene(new Scene(root, 400, 200));
                stage.show();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            clearFields();

            //update tourView
            Main m = new Main();
            m.changeScene("views/tourView.fxml");

            logger.info("Edit-Button clicked");
        }
    }

    public void setTextInFields() throws IOException {
        tourChoiceBox.getSelectionModel().selectedItemProperty().addListener(((observableValue, oldValue, newValue) -> {
            if ((newValue != null) && (oldValue != newValue)) {
                try {
                    //fill the textFields
                    modifyTourViewModel.settingTextFields(tourChoiceBox.getValue(), tourDescription, tourDistance, tourStart, tourEnd);
                    logger.info("TextFields are being filled");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }));
    }

    public void clearFields(){
        tourDescription.clear();
        tourDistance.clear();
        tourStart.clear();
        tourEnd.clear();
    }
}
