package gui.viewmodels;

import businesslayer.logManagerFactory;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import lombok.Getter;

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

    public List<String> gettingLogNames(){
        logManager = logManagerFactory.GetLogManager();

        List<String> tourNames = new ArrayList<String>();
        tourNames = logManager.GetLogNames();

        return tourNames;
    }

    public void updatingLog(String indexTourDate){
        logManager = logManagerFactory.GetLogManager();

        String tempDate = logDate.getValue();
        String tempReport = logReport.getValue();
        String tempDistance = logDistance.getValue();
        String tempTotalTime = logTotalTime.getValue();
        int tempRating = Integer.parseInt(logRating.getValue());
        int tempBreaks = Integer.parseInt(logBreaks.getValue());
        String tempSpeed = logAverageSpeed.getValue();
        String tempWeather = logWeather.getValue();
        String tempStart = logStart.getValue();
        String tempEnd = logEnd.getValue();

        //System.out.println(wow);
        logManager.UpdateLogItem(indexTourDate, tempDate, tempReport, tempDistance, tempTotalTime,
                tempRating, tempSpeed, tempWeather, tempBreaks, tempStart, tempEnd);
    }
}
