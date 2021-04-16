package gui.controller;

import businesslayer.tourManager;
import businesslayer.tourManagerFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import models.tourModel;

import java.net.URL;
import java.util.ResourceBundle;

public class homeViewController implements Initializable {

    public ListView<tourModel> tourLogModels;

    //automatic binding to the ListView Object above
    private ObservableList<tourModel> tourItems;

    private tourModel currentTour;
    private businesslayer.tourManager tourManager;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tourManager = tourManagerFactory.GetTourManager();

        setUpTourView();
        FormatTourCells();
    }

    private void setUpTourView(){
        tourItems = FXCollections.observableArrayList();
        tourItems.addAll(tourManager.GetTourItems());
        tourLogModels.setItems(tourItems);
    }

    private void FormatTourCells(){
        //format tour cells to show name
        tourLogModels.setCellFactory(parameter -> new ListCell<tourModel>(){
            @Override
            protected void updateItem(tourModel item, boolean empty){
                super.updateItem(item, empty);

                if(empty || (item == null) || (item.getTourName() == null)){
                    setText(null);
                } else{
                    String stringThis = "";
                    stringThis = item.getTourName() + " | " + item.getTourDescription();
                    setText(stringThis);
                }
            }
        });
    }
}
