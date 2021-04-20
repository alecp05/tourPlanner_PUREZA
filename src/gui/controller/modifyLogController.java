package gui.controller;

import businesslayer.logManagerFactory;
import businesslayer.tourManagerFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class modifyLogController implements Initializable {

    private ObservableList<String> logChoices;

    @FXML
    private ChoiceBox<String> logChoiceBox;

    private businesslayer.logManager logManager;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        logManager = logManagerFactory.GetLogManager();
        logChoices = FXCollections.observableArrayList();

        List<String> tourNames = new ArrayList<String>();
        tourNames = logManager.GetLogNames();

        logChoices.addAll(tourNames);
        logChoiceBox.getItems().addAll(logChoices);
    }

    public void editLogButton(ActionEvent actionEvent) {
        //String now = logChoiceBox.getValue();
        int wow = logChoices.indexOf(logChoiceBox.getValue());

        int wowie = logChoices.lastIndexOf(logChoiceBox.getValue());
        System.out.println(wowie);

        //System.out.println(wow);
    }

}
