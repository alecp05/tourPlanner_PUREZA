package gui.viewmodels;

import businesslayer.tourManagerFactory;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import lombok.Getter;

import java.io.IOException;


public class addTourViewModel {

    private businesslayer.tourManager tourManager;

    @Getter
    private final StringProperty tourName = new SimpleStringProperty("");
    @Getter
    private final StringProperty tourDescription = new SimpleStringProperty("");
    @Getter
    private final StringProperty tourDistance = new SimpleStringProperty("");
    @Getter
    private final StringProperty tourStart = new SimpleStringProperty("");
    @Getter
    private final StringProperty tourEnd = new SimpleStringProperty("");

    public void addingTour() throws IOException {
        tourManager = tourManagerFactory.GetTourManager();

        String tempName = tourName.getValue();
        //System.out.println(tempName);
        String tempDescription = tourDescription.getValue();
        Integer tempDistance = 0;

        if(tempName != "") {
            tempDistance = Integer.parseInt(tourDistance.getValue());
        }
        String tempStart = tourStart.getValue();
        String tempEnd = tourEnd.getValue();

        //insert to database
        if(tempName != ""){
            tourManager.InsertTourItem(tempName,tempDescription,tempDistance,tempStart,tempEnd);
            addMapImage(tempName,tempStart,tempEnd);
        }
    }

    public void addMapImage(String tourName, String start, String end) throws IOException {
        tourManager.GetImageRequest(tourName,start,end);
    }
}
