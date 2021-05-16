package gui.controller;

import businesslayer.logManagerFactory;
import businesslayer.tourManagerFactory;
import gui.Main;
import gui.viewmodels.homeViewModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import models.logModel;
import models.tourModel;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class homeViewController implements Initializable {

    private static final Logger logger = LogManager.getLogger(homeViewController.class);

    public TextField searchingField;

    //public ListView<tourModel> tourLogModels;

    //Tour tableView
    @FXML
    private TableView<tourModel> tableTourView;
    @FXML
    private TableColumn<tourModel, String> tourNameId;
    @FXML
    private TableColumn<tourModel, String> tourDescriptionId;
    @FXML
    private TableColumn<tourModel, Integer> tourDistanceId;
    @FXML
    private TableColumn<tourModel, String> tourStartId;
    @FXML
    private TableColumn<tourModel, String> tourEndId;

    //automatic binding to the TableView Object above
    private ObservableList<tourModel> tableTourItems;

    //Log tableView
    @FXML
    private TableView<logModel> tableLogView;
    @FXML
    private TableColumn<logModel, String> logNameId;
    @FXML
    private TableColumn<logModel, String> logDescriptionId;
    @FXML
    private TableColumn<logModel, String> logInformationId;
    @FXML
    private TableColumn<logModel, String> logDistanceId;
    @FXML
    private TableColumn<logModel, String> logTotalTimeId;
    @FXML
    private TableColumn<logModel, Integer> logRatingId;
    @FXML
    private TableColumn<logModel, String> logSpeedId;
    @FXML
    private TableColumn<logModel, String> logWeatherId;
    @FXML
    private TableColumn<logModel, Integer> logBreaksId;
    @FXML
    private TableColumn<logModel, String> logStartId;
    @FXML
    private TableColumn<logModel, String> logEndId;

    //automatic binding to the TableView Object above
    private ObservableList<logModel> tableLogItems;


    private tourModel currentItem;

    //automatic binding to the ListView Object above
    //private ObservableList<tourModel> tourItems;

    private tourModel currentTour;

    //businessLayer communication
    private businesslayer.tourManager tourManager;
    private businesslayer.logManager logManager;

    public homeViewModel homeViewModel = new homeViewModel();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //setUpTourView();
        //FormatTourCells();

        //tour TableView
        setUpTourTable();
        formatTourTableColumns();
        //SetCurrentTourItem();

        //log TableView
        setUpLogTable();
        formatLogTableColumns();

        //search Function

        logger.info("Initialized HomeView");

    }


    private void setUpTourTable(){
        tableTourItems = FXCollections.observableArrayList();
        tableTourItems.addAll(homeViewModel.gettingTourItems());
    }

    private void formatTourTableColumns(){
        tourNameId.setCellValueFactory(new PropertyValueFactory<tourModel, String>("tourName"));
        tourDescriptionId.setCellValueFactory(new PropertyValueFactory<tourModel, String>("tourDescription"));
        tourDistanceId.setCellValueFactory(new PropertyValueFactory<tourModel, Integer>("tourDistance"));
        tourStartId.setCellValueFactory(new PropertyValueFactory<tourModel, String>("tourStart"));
        tourEndId.setCellValueFactory(new PropertyValueFactory<tourModel, String>("tourEnd"));

        tableTourView.setItems(tableTourItems);
    }

    private void setUpLogTable(){
        tableLogItems = FXCollections.observableArrayList();
        tableLogItems.addAll(homeViewModel.gettingLogItems());
    }

    private void formatLogTableColumns(){
        logNameId.setCellValueFactory(new PropertyValueFactory<logModel, String>("tourName"));
        logDescriptionId.setCellValueFactory(new PropertyValueFactory<logModel, String>("logDate"));
        logInformationId.setCellValueFactory(new PropertyValueFactory<logModel, String>("logReport"));
        logDistanceId.setCellValueFactory(new PropertyValueFactory<logModel, String>("logDistance"));
        logTotalTimeId.setCellValueFactory(new PropertyValueFactory<logModel, String>("logTotalTime"));
        logRatingId.setCellValueFactory(new PropertyValueFactory<logModel, Integer>("logRating"));
        logSpeedId.setCellValueFactory(new PropertyValueFactory<logModel, String>("logAverageSpeed"));
        logWeatherId.setCellValueFactory(new PropertyValueFactory<logModel, String>("logWeatherCondition"));
        logBreaksId.setCellValueFactory(new PropertyValueFactory<logModel, Integer>("logBreaksTaken"));
        logStartId.setCellValueFactory(new PropertyValueFactory<logModel, String>("logStartingPoint"));
        logEndId.setCellValueFactory(new PropertyValueFactory<logModel, String>("logEndPoint"));

        tableLogView.setItems(tableLogItems);
    }


/*    private void SetCurrentTourItem(){
        tableTourView.getSelectionModel().selectedItemProperty().addListener(((observableValue, oldValue, newValue) -> {
            if((newValue != null) && (oldValue != newValue)){
                currentItem = newValue;
                System.out.println(currentItem.tourName);
            }
        }));
    }*/

    public void tourButtonAction(ActionEvent actionEvent) throws IOException {
        Main m = new Main();
        m.changeScene("views/tourView.fxml");
    }

    public void logButtonAction(ActionEvent actionEvent) throws IOException {
        Main m = new Main();
        m.changeScene("views/logView.fxml");
    }

    public void searchingAction(ActionEvent actionEvent) {
        tableTourItems.clear();
        tableLogItems.clear();

        List<tourModel> tourItems = homeViewModel.searchingTourItems(searchingField.textProperty().getValue());
        tableTourItems.addAll(tourItems);
        List<logModel> logItems = homeViewModel.searchingLogItems(searchingField.textProperty().getValue());
        tableLogItems.addAll(logItems);

        logger.info("Search Function clicked");
    }

    public void clearAction(ActionEvent actionEvent) {
        tableTourItems.clear();
        tableLogItems.clear();

        searchingField.textProperty().setValue("");

        List<tourModel> TourItems = homeViewModel.searchingTourItems(searchingField.textProperty().getValue());
        tableTourItems.addAll(TourItems);

        List<logModel> LogItems = homeViewModel.searchingLogItems(searchingField.textProperty().getValue());
        tableLogItems.addAll(LogItems);

        logger.info("Clear Function clicked");
    }
}
