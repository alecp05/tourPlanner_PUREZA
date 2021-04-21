package gui.controller;

import businesslayer.logManagerFactory;
import businesslayer.tourManagerFactory;
import gui.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class modifyLogController implements Initializable {

    private static final Logger logger = LogManager.getLogger(modifyLogController.class);

    public TextField logDate;
    public TextField logReport;
    public TextField logDistance;
    public TextField logTotalTime;
    public TextField logRating;
    public TextField logAverageSpeed;
    public TextField logWeather;
    public TextField logBreaks;
    public TextField logStart;
    public TextField logEnd;

    private ObservableList<String> logChoices;

    @FXML
    private ChoiceBox<String> logChoiceBox;

    private businesslayer.logManager logManager;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        logManager = logManagerFactory.GetLogManager();
        updateChoiceBox();

        logger.info("Initialized modifyLogView");
    }

    public void updateChoiceBox(){
        logChoices = FXCollections.observableArrayList();
        List<String> tourNames = new ArrayList<String>();
        tourNames = logManager.GetLogNames();

        logChoices.addAll(tourNames);
        logChoiceBox.setItems(logChoices);
    }

    public void editLogButton(ActionEvent actionEvent) throws IOException {
        //String now = logChoiceBox.getValue();
        int indexNumber = logChoices.indexOf(logChoiceBox.getValue());
        //System.out.println(indexNumber);

        String indexTourDate = logChoiceBox.getValue();
        //System.out.println(indexTourDate);

        if(indexTourDate!=null) {
            String tempDate = logDate.textProperty().getValue();
            String tempReport = logReport.textProperty().getValue();
            String tempDistance = logDistance.textProperty().getValue();
            String tempTotalTime = logTotalTime.textProperty().getValue();
            Integer tempRating = Integer.parseInt(logRating.textProperty().getValue());
            Integer tempBreaks = Integer.parseInt(logBreaks.textProperty().getValue());
            String tempSpeed = logAverageSpeed.textProperty().getValue();
            String tempWeather = logWeather.textProperty().getValue();
            String tempStart = logStart.textProperty().getValue();
            String tempEnd = logEnd.textProperty().getValue();

            //System.out.println(wow);
            logManager.UpdateLogItem(indexTourDate, tempDate, tempReport, tempDistance, tempTotalTime,
                    tempRating, tempSpeed, tempWeather, tempBreaks, tempStart, tempEnd);

            //clear choiceBox and fields
            clearFields();
            updateChoiceBox();

            logger.info("Edit-Button clicked");

            //update tourView
            Main m = new Main();
            m.changeScene("views/logView.fxml");
        }
    }

    public void clearFields(){
        logDate.clear();
        logReport.clear();
        logDistance.clear();
        logTotalTime.clear();
        logRating.clear();
        logAverageSpeed.clear();
        logWeather.clear();
        logBreaks.clear();
        logStart.clear();
        logEnd.clear();
    }
}
