package gui.controller;

import businesslayer.tourManager;
import businesslayer.tourManagerFactory;
import gui.Main;
import javafx.event.ActionEvent;
import javafx.scene.control.TextField;

import java.io.IOException;

public class addTourController {

    public TextField tourName;
    public TextField tourDescription;
    public TextField tourDistance;
    public TextField tourStart;
    public TextField tourEnd;

    private businesslayer.tourManager tourManager;

    public void addTourButton(ActionEvent actionEvent) throws IOException {

        tourManager = tourManagerFactory.GetTourManager();

        String tempName = tourName.textProperty().getValue();
        String tempDescription = tourDescription.textProperty().getValue();
        Integer tempDistance = Integer.parseInt(tourDistance.textProperty().getValue());
        String tempStart = tourStart.textProperty().getValue();
        String tempEnd = tourEnd.textProperty().getValue();

        //insert to database
        tourManager.InsertTourItem(tempName,tempDescription,tempDistance,tempStart,tempEnd);

        clearFields();

        //update tourView
        Main m = new Main();
        m.changeScene("views/tourView.fxml");
    }

    public void clearFields(){
        tourName.clear();
        tourDescription.clear();
        tourDistance.clear();
        tourStart.clear();
        tourEnd.clear();
    }
}
