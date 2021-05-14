package gui.controller;

import businesslayer.logManager;
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

public class addLogController implements Initializable {

    private static final Logger logger = LogManager.getLogger(addLogController.class);

    //public String logName;
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

    private ObservableList<String> tourChoices;
    @FXML
    private ChoiceBox<String> tourChoiceBox;

    private businesslayer.logManager logManager;
    private businesslayer.tourManager tourManager;

    public void addLogButton(ActionEvent actionEvent) throws IOException {
        logManager = logManagerFactory.GetLogManager();

        //set choiceBox
        String logName = tourChoiceBox.getValue();

        String tempDate = logDate.textProperty().getValue();
        String tempReport = logReport.textProperty().getValue();
        String tempDistance = logDistance.textProperty().getValue();
        String tempTotalTime = logTotalTime.textProperty().getValue();
        Integer tempRating = 0;
        Integer tempBreaks = 0;
        if(logName != null){
            tempRating = Integer.parseInt(logRating.textProperty().getValue());
            tempBreaks = Integer.parseInt(logBreaks.textProperty().getValue());
        }
        String tempSpeed = logAverageSpeed.textProperty().getValue();
        String tempWeather = logWeather.textProperty().getValue();
        String tempStart = logStart.textProperty().getValue();
        String tempEnd = logEnd.textProperty().getValue();


        //System.out.println(logName + "test");
        //insert into database
        if(logName != null) {
            logManager.InsertLogItem(logName, tempDate, tempReport, tempDistance, tempTotalTime,
                   tempRating, tempSpeed, tempWeather, tempBreaks, tempStart, tempEnd);

            clearFields();
        }

        logger.info("Add-Button clicked");

        //update tourView
        Main m = new Main();
        m.changeScene("views/logView.fxml");

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tourManager = tourManagerFactory.GetTourManager();
        tourChoices = FXCollections.observableArrayList();

        List<String> tourNames = new ArrayList<String>();
        tourNames = tourManager.GetTourNames();

        tourChoices.addAll(tourNames);
        tourChoiceBox.getItems().addAll(tourChoices);
        //System.out.println(tourChoiceBox);

        logger.info("Initialized addLogView");
    }
}
