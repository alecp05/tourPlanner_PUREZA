package models;

import lombok.Getter;
import lombok.Setter;

public class tourModel {

    @Getter @Setter public String tourName;
    @Getter @Setter public String tourDescription;
    @Getter @Setter public String tourRouteInformation;
    @Getter @Setter public int tourDistance;

    public tourModel(String name, String description, String routeInformation, int distance){
        tourName = name;
        tourDescription = description;
        tourRouteInformation = routeInformation;
        tourDistance = distance;
    }

}
