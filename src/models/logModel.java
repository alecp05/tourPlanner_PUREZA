package models;

import lombok.Getter;
import lombok.Setter;

import java.text.DateFormat;

public class logModel {

    @Getter @Setter public String tourName;
    @Getter @Setter public DateFormat logDate;
    @Getter @Setter public String logReport;
    @Getter @Setter public String logDistance;
    @Getter @Setter public String logTotalTime;
    @Getter @Setter public int logRating;
    @Getter @Setter public String logAverageSpeed;
    @Getter @Setter public String logWeatherCondition;
    @Getter @Setter public int logBreaksTaken;
    @Getter @Setter public String logStartingPoint;
    @Getter @Setter public String logEndPoint;

    public logModel(String name, DateFormat date, String report, String distance, String totalTime,
                    int rating, String averageSpeed, String weather, int breaks, String start, String end){

        tourName = name;
        logDate = date;
        logReport = report;
        logDistance = distance;
        logTotalTime = totalTime;
        logRating = rating;
        logAverageSpeed = averageSpeed;
        logWeatherCondition = weather;
        logBreaksTaken = breaks;
        logStartingPoint = start;
        logEndPoint = end;
    }
}
