package gui.controller;

import businesslayer.tourManager;
import businesslayer.tourManagerFactory;
import gui.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import models.tourModel;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class homeViewController implements Initializable {

    public ListView<tourModel> tourLogModels;

    //tableView tests
    @FXML
    private TableView<tourModel> tabletest;
    @FXML
    private TableColumn<tourModel, String> test1;
    @FXML
    private TableColumn<tourModel, String> test2;
    @FXML
    private TableColumn<tourModel, String> test3;
    private ObservableList<tourModel> testTourItems;

    private tourModel currentItem;

    //automatic binding to the ListView Object above
    private ObservableList<tourModel> tourItems;

    private tourModel currentTour;
    private businesslayer.tourManager tourManager;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tourManager = tourManagerFactory.GetTourManager();

        setUpTourView();
        FormatTourCells();

        testSetUpTable();
        testFormatTableColumns();
        //SetCurrentItem();
    }


    private void testSetUpTable(){
        testTourItems = FXCollections.observableArrayList();
        testTourItems.addAll(tourManager.GetTourItems());
    }

    private void testFormatTableColumns(){
        test1.setCellValueFactory(new PropertyValueFactory<tourModel, String>("tourName"));
        test2.setCellValueFactory(new PropertyValueFactory<tourModel, String>("tourDescription"));
        test3.setCellValueFactory(new PropertyValueFactory<tourModel, String>("tourRouteInformation"));

        tabletest.setItems(testTourItems);
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

    private void SetCurrentItem(){
        tabletest.getSelectionModel().selectedItemProperty().addListener(((observableValue, oldValue, newValue) -> {
            if((newValue != null) && (oldValue != newValue)){
                currentItem = newValue;
                System.out.println(currentItem.tourName);
            }
        }));
    }

    public void tourButtonAction(ActionEvent actionEvent) throws IOException {
        Main m = new Main();
        m.changeScene("views/tourView.fxml");
    }

    public void logButtonAction(ActionEvent actionEvent) throws IOException {
        Main m = new Main();
        m.changeScene("views/logView.fxml");
    }
}
