package gui.controller;

import businesslayer.logManager;
import businesslayer.logManagerFactory;
import gui.Main;
import javafx.event.ActionEvent;
import javafx.scene.control.TextField;

import java.io.IOException;

public class addLogController {

    public TextField logName;
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

    private businesslayer.logManager logManager;

    public void addLogButton(ActionEvent actionEvent) throws IOException {
        logManager = logManagerFactory.GetLogManager();

        String tempName = logName.textProperty().getValue();
        String tempDate = logDate.textProperty().getValue();
        String tempReport = logReport.textProperty().getValue();
        String tempDistance = logDistance.textProperty().getValue();
        String tempTotalTime = logTotalTime.textProperty().getValue();
        Integer tempRating = Integer.parseInt(logRating.textProperty().getValue());
        String tempSpeed = logAverageSpeed.textProperty().getValue();
        String tempWeather = logWeather.textProperty().getValue();
        Integer tempBreaks = Integer.parseInt(logBreaks.textProperty().getValue());
        String tempStart = logStart.textProperty().getValue();
        String tempEnd = logEnd.textProperty().getValue();

        //insert into database
        logManager.InsertLogItem(tempName,tempDate,tempReport,tempDistance,tempTotalTime,
                tempRating,tempSpeed,tempWeather,tempBreaks,tempStart,tempEnd);

        clearFields();

        //update tourView
        Main m = new Main();
        m.changeScene("views/logView.fxml");

    }

    public void clearFields(){
        logName.clear();
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
