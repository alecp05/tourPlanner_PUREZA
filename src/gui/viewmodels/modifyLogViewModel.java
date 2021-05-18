package gui.viewmodels;

import businesslayer.inputValidation.inputValidationImplementation;
import businesslayer.inputValidation.inputValidationManager;
import businesslayer.logManagerFactory;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.TextField;
import lombok.Getter;
import models.logModel;
import models.tourModel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class modifyLogViewModel {

    @Getter
    private final StringProperty logDate = new SimpleStringProperty("");
    @Getter
    private final StringProperty logReport = new SimpleStringProperty("");
    @Getter
    private final StringProperty logDistance = new SimpleStringProperty("");
    @Getter
    private final StringProperty logTotalTime = new SimpleStringProperty("");
    @Getter
    private final StringProperty logRating = new SimpleStringProperty("");
    @Getter
    private final StringProperty logAverageSpeed = new SimpleStringProperty("");
    @Getter
    private final StringProperty logWeather = new SimpleStringProperty("");
    @Getter
    private final StringProperty logBreaks = new SimpleStringProperty("");
    @Getter
    private final StringProperty logStart = new SimpleStringProperty("");
    @Getter
    private final StringProperty logEnd = new SimpleStringProperty("");

    private businesslayer.logManager logManager;

    public inputValidationManager inputValidationManager = new inputValidationImplementation();

    public List<String> gettingLogNames() throws IOException {
        logManager = logManagerFactory.GetLogManager();

        List<String> tourNames = new ArrayList<String>();
        tourNames = logManager.GetLogNames();

        return tourNames;
    }

    public int updatingLog(String indexTourDate) throws IOException {
        logManager = logManagerFactory.GetLogManager();

        String tempDate = logDate.getValue();
        String tempReport = logReport.getValue();
        String tempDistance = logDistance.getValue();
        String tempTotalTime = logTotalTime.getValue();
        Integer tempRating = 0;
        Integer tempBreaks = 0;
        if((logRating.getValue() != "") && (logBreaks.getValue() != "")) {
            tempRating = Integer.parseInt(logRating.getValue());
            tempBreaks = Integer.parseInt(logBreaks.getValue());
        }
        String tempSpeed = logAverageSpeed.getValue();
        String tempWeather = logWeather.getValue();
        String tempStart = logStart.getValue();
        String tempEnd = logEnd.getValue();

        //System.out.println(wow);
        //update in database if correct userInput
        if((indexTourDate != "") && (tempDate != "") && (tempReport != "") && (tempDistance != "") && (tempTotalTime != "")
                && (tempRating != 0) && (tempBreaks) != 0 && (tempSpeed != "") && (tempWeather != "") && (tempStart != "") && (tempEnd != "")) {

            //Check if Formats of Date, Distance and TotalTime is correct
            boolean tempBoolean = inputValidationManager.checkFormat(tempDate,tempDistance,tempTotalTime);
            if(!tempBoolean){
                return 3;
            }

            logManager.UpdateLogItem(indexTourDate, tempDate, tempReport, tempDistance, tempTotalTime,
                    tempRating, tempSpeed, tempWeather, tempBreaks, tempStart, tempEnd);
            return 1;
        }else{
            return 2;
        }
    }

    //set Text in Fields
    public void settingTextFields(String logNameDate,TextField dateStamp, TextField report, TextField distance, TextField time, TextField rating,
                                  TextField speed,TextField weather, TextField breaks, TextField start, TextField end) throws IOException {
            logManager= logManagerFactory.GetLogManager();

        List<logModel> allTours = logManager.GetLogItems();
        logModel specificLog;

        //split logNameDate
        String[] tempName = logNameDate.split("\\|");
        String logName = tempName[0];
        String date = tempName[1];
        //System.out.println(logName + " " + date);

        for(int j = 0; j<allTours.size(); j++){
            if (allTours.get(j).tourName.equals(logName) && allTours.get(j).logDate.equals(date)) {
                dateStamp.setText(allTours.get(j).getLogDate());
                report.setText(allTours.get(j).getLogReport());
                distance.setText(allTours.get(j).getLogDistance());
                time.setText(allTours.get(j).getLogTotalTime());
                rating.setText(Integer.toString(allTours.get(j).getLogRating()));
                speed.setText(allTours.get(j).getLogAverageSpeed());
                weather.setText(allTours.get(j).getLogWeatherCondition());
                breaks.setText(Integer.toString(allTours.get(j).getLogBreaksTaken()));
                start.setText(allTours.get(j).getLogStartingPoint());
                end.setText(allTours.get(j).getLogEndPoint());
            }
        }
    }
    //inputValidation
    public void setFieldRestrictions(TextField logRating, TextField logBreaks, TextField logStart, TextField logEnd){
        inputValidationManager.onlyNumbers(logBreaks);
        inputValidationManager.onlyOneToTen(logRating);
        inputValidationManager.onlyCharacters(logStart);
        inputValidationManager.onlyCharacters(logEnd);
    }
}
