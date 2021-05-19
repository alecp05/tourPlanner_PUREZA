package gui.controller;

import businesslayer.logManagerFactory;
import gui.Main;
import gui.viewmodels.logViewModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import lombok.SneakyThrows;
import models.logModel;
import models.tourModel;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class logViewController implements Initializable {

    private static final Logger logger = LogManager.getLogger(logViewController.class);

    public TextField searchingField;

    public ChoiceBox tourChoiceBox;
    //binding to choiceBox
    private ObservableList<String> tourChoices;

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


    private logModel currentItem;

    public logViewModel logViewModel = new logViewModel();

    @SneakyThrows
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //log TableView
        setUpLogTable();
        formatLogTableColumns();

        SetCurrentLogItem();

        //setting choiceBox
        setChoiceBox();

        logger.info("Initialized LogView");
    }

    private void setUpLogTable() throws IOException {
        tableLogItems = FXCollections.observableArrayList();
        tableLogItems.addAll(logViewModel.gettingLogItems());
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

    public void searchingAction(ActionEvent actionEvent) throws IOException {
        tableLogItems.clear();

        List<logModel> logItems = logViewModel.searchingLogItems(searchingField.textProperty().getValue());
        tableLogItems.addAll(logItems);

        logger.info("Search Function clicked");
    }

    public void clearAction(ActionEvent actionEvent) throws IOException {
        tableLogItems.clear();

        searchingField.textProperty().setValue("");

        List<logModel> LogItems = logViewModel.gettingLogItems();
        tableLogItems.addAll(LogItems);

        logger.info("Items cleared");
    }


    public void tourButtonAction(ActionEvent actionEvent) throws IOException {
        Main m = new Main();
        m.changeScene("views/tourView.fxml");
    }

    public void homeButtonAction(ActionEvent actionEvent) throws IOException {
        Main m = new Main();
        m.changeScene("views/homeView.fxml");
    }
    public void galleryButtonAction(ActionEvent actionEvent) throws IOException {
        Main m = new Main();
        m.changeScene("views/galleryView.fxml");
    }

    public void addLogButton(ActionEvent actionEvent) {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getClassLoader().getResource("gui/views/addLogView.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Adding Logs");
            stage.setScene(new Scene(root, 850, 500));
            stage.show();

            logger.info("Add-Button clicked");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void SetCurrentLogItem(){
        tableLogView.getSelectionModel().selectedItemProperty().addListener(((observableValue, oldValue, newValue) -> {
            if((newValue != null) && (oldValue != newValue)){
                currentItem = newValue;
                //System.out.println(currentItem.tourName);

                logger.info("Current Item is set");
            }
        }));
    }

    public void deleteLogButton(ActionEvent actionEvent) throws IOException {
        if(currentItem != null) {
            //logManager.DeleteLogItem(currentItem.logDate, currentItem.logReport);
            logViewModel.deletingLog(currentItem.logDate, currentItem.logReport);

            //update table
            tableLogItems.clear();
            List<logModel> LogItems = logViewModel.gettingLogItems();
            tableLogItems.addAll(LogItems);

            logger.info("Add-Button clicked");
        }
        currentItem = null;
    }

    public void editButton(ActionEvent actionEvent) {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getClassLoader().getResource("gui/views/modifyLogView.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Edit Logs");
            stage.setScene(new Scene(root, 700, 450));
            stage.show();

            logger.info("Edit-Button clicked");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setChoiceBox() throws IOException {
        tourChoices = FXCollections.observableArrayList();

        List<String> tourNames = new ArrayList<String>();
        tourNames = logViewModel.gettingTours();

        tourChoices.addAll(tourNames);
        tourChoiceBox.getItems().addAll(tourChoices);
    }

    public void setToursLogsButton(ActionEvent actionEvent) throws IOException {
        if(tourChoiceBox.getValue() != null){
            //get logs with the specific tour
            List<logModel> specifiedLogs = logViewModel.gettingTheLogs((String) tourChoiceBox.getValue());
            tableLogItems.clear();
            tableLogItems.addAll(specifiedLogs);

            logger.info("Logs with specified Tour has been set in TableView");
        }else{
            logger.warn("No Tour has been selected in ChoiceBox");
        }
    }


}
