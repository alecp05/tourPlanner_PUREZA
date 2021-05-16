package gui.viewmodels;

import businesslayer.tourManagerFactory;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import lombok.Getter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class modifyTourViewModel {
    @Getter
    private final StringProperty tourDescription = new SimpleStringProperty("");
    @Getter
    private final StringProperty tourDistance = new SimpleStringProperty("");
    @Getter
    private final StringProperty tourStart = new SimpleStringProperty("");
    @Getter
    private final StringProperty tourEnd = new SimpleStringProperty("");

    private businesslayer.tourManager tourManager;

    public List<String> gettingTourNames() throws IOException {
        tourManager = tourManagerFactory.GetTourManager();

        List<String> tourNames = new ArrayList<String>();
        tourNames = tourManager.GetTourNames();

        return tourNames;
    }

    public void editingTour(String chosenTour) throws IOException {
        tourManager = tourManagerFactory.GetTourManager();
        //get inputs of textFields
        String tempDescription = tourDescription.getValue();
        int tempDistance = Integer.parseInt(tourDistance.getValue());
        String tempStart = tourStart.getValue();
        String tempEnd = tourEnd.getValue();

        //update in database
        tourManager.UpdateTourItem(chosenTour,tempDescription,tempDistance,tempStart,tempEnd);

        //delete and create nur jpg-image
        tourManager.DeleteImage(chosenTour);
        tourManager.GetImageRequest(chosenTour,tempStart,tempEnd);
    }
}
