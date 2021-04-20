package gui.controller;

import businesslayer.tourManager;
import businesslayer.tourManagerFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import models.tourModel;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class modifyTourController implements Initializable {

    private ObservableList<String> tourChoices;

    @FXML
    private ChoiceBox<String> tourChoiceBox;

    private businesslayer.tourManager tourManager;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tourManager = tourManagerFactory.GetTourManager();
        tourChoices = FXCollections.observableArrayList();

        List<String> tourNames = new ArrayList<String>();
        tourNames = tourManager.GetTourNames();

        tourChoices.addAll(tourNames);
        tourChoiceBox.getItems().addAll(tourChoices);

    }

    public void editButton(ActionEvent actionEvent) {
        String now = tourChoiceBox.getValue();
        System.out.println(now);
    }
}
