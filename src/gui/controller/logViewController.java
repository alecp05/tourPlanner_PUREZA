package gui.controller;

import businesslayer.logManagerFactory;
import gui.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import models.logModel;
import models.tourModel;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class logViewController implements Initializable {

    public TextField searchingField;

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

    //businessLayer communication
    private businesslayer.logManager logManager;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        logManager = logManagerFactory.GetLogManager();

        //log TableView
        setUpLogTable();
        formatLogTableColumns();

        SetCurrentLogItem();
    }

    private void setUpLogTable(){
        tableLogItems = FXCollections.observableArrayList();
        tableLogItems.addAll(logManager.GetLogItems());
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

    public void searchingAction(ActionEvent actionEvent) {
        tableLogItems.clear();

        List<logModel> logItems = logManager.SearchLogItems(searchingField.textProperty().getValue(), false);
        tableLogItems.addAll(logItems);
    }

    public void clearAction(ActionEvent actionEvent) {
        tableLogItems.clear();

        searchingField.textProperty().setValue("");


        List<logModel> LogItems = logManager.GetLogItems();
        tableLogItems.addAll(LogItems);
    }


    public void tourButtonAction(ActionEvent actionEvent) throws IOException {
        Main m = new Main();
        m.changeScene("views/tourView.fxml");
    }

    public void homeButtonAction(ActionEvent actionEvent) throws IOException {
        Main m = new Main();
        m.changeScene("views/homeView.fxml");
    }

    public void addLogButton(ActionEvent actionEvent) {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getClassLoader().getResource("gui/views/addLogView.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Adding Logs");
            stage.setScene(new Scene(root, 850, 500));
            stage.show();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void SetCurrentLogItem(){
        tableLogView.getSelectionModel().selectedItemProperty().addListener(((observableValue, oldValue, newValue) -> {
            if((newValue != null) && (oldValue != newValue)){
                currentItem = newValue;
                System.out.println(currentItem.tourName);
            }
        }));
    }

    public void deleteLogButton(ActionEvent actionEvent) {
        if(currentItem != null) {
            logManager.DeleteLogItem(currentItem.logDate, currentItem.logReport);

            //update table
            tableLogItems.clear();
            List<logModel> LogItems = logManager.GetLogItems();
            tableLogItems.addAll(LogItems);
        }
        currentItem = null;
    }
}
