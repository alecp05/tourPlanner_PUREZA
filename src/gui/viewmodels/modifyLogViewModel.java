package gui.viewmodels;

import businesslayer.inputValidation.inputValidationImplementation;
import businesslayer.inputValidation.inputValidationManager;
import businesslayer.logManagerFactory;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.TextField;
import lombok.Getter;

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

    //inputValidation
    public void setFieldRestrictions(TextField logRating, TextField logBreaks, TextField logStart, TextField logEnd){
        inputValidationManager.onlyNumbers(logBreaks);
        inputValidationManager.onlyOneToTen(logRating);
        inputValidationManager.onlyCharacters(logStart);
        inputValidationManager.onlyCharacters(logEnd);
    }
}
