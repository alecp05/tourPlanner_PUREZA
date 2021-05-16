package gui.viewmodels;

import businesslayer.logManagerFactory;
import businesslayer.tourManagerFactory;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import lombok.Getter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class addLogViewModel {
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
    private businesslayer.tourManager tourManager;

    public void addingLog(String logNameTemp) throws IOException {
        logManager = logManagerFactory.GetLogManager();

        //set choiceBox
        String logName = logNameTemp;

        String tempDate = logDate.getValue();
        String tempReport = logReport.getValue();
        String tempDistance = logDistance.getValue();
        String tempTotalTime = logTotalTime.getValue();
        Integer tempRating = 0;
        Integer tempBreaks = 0;
        if(logName != null){
            tempRating = Integer.parseInt(logRating.getValue());
            tempBreaks = Integer.parseInt(logBreaks.getValue());
        }
        String tempSpeed = logAverageSpeed.getValue();
        String tempWeather = logWeather.getValue();
        String tempStart = logStart.getValue();
        String tempEnd = logEnd.getValue();


        //System.out.println(logName + "test");
        //insert into database
        if(logName != null) {
            logManager.InsertLogItem(logName, tempDate, tempReport, tempDistance, tempTotalTime,
                    tempRating, tempSpeed, tempWeather, tempBreaks, tempStart, tempEnd);
        }
    }

    public List<String> gettingTours() throws IOException {
        tourManager = tourManagerFactory.GetTourManager();

        List<String> tourNames = new ArrayList<String>();
        tourNames = tourManager.GetTourNames();

        return tourNames;
    }
}
