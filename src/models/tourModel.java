package models;

import lombok.Getter;
import lombok.Setter;

public class tourModel {

    @Getter @Setter public String tourName;
    @Getter @Setter public String tourDescription;
    @Getter @Setter public int tourDistance;
    @Getter @Setter public String tourStart;
    @Getter @Setter public String tourEnd;

    public tourModel(String name, String description, int distance, String start, String end){
        tourName = name;
        tourDescription = description;
        tourDistance = distance;
        tourStart = start;
        tourEnd = end;
    }

}
