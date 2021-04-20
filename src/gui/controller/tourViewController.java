package gui.controller;

import businesslayer.tourManagerFactory;
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

public class tourViewController implements Initializable {

    public TextField searchingField;

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


    //businessLayer communication
    private businesslayer.tourManager tourManager;

    //select current
    private tourModel currentItem = null;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tourManager = tourManagerFactory.GetTourManager();

        //tour TableView
        setUpTourTable();
        formatTourTableColumns();

        SetCurrentTourItem();

    }

    private void setUpTourTable(){
        tableTourItems = FXCollections.observableArrayList();
        tableTourItems.addAll(tourManager.GetTourItems());
    }

    private void formatTourTableColumns(){
        tourNameId.setCellValueFactory(new PropertyValueFactory<tourModel, String>("tourName"));
        tourDescriptionId.setCellValueFactory(new PropertyValueFactory<tourModel, String>("tourDescription"));
        tourDistanceId.setCellValueFactory(new PropertyValueFactory<tourModel, Integer>("tourDistance"));
        tourStartId.setCellValueFactory(new PropertyValueFactory<tourModel, String>("tourStart"));
        tourEndId.setCellValueFactory(new PropertyValueFactory<tourModel, String>("tourEnd"));

        tableTourView.setItems(tableTourItems);
    }

    public void searchingAction(ActionEvent actionEvent) {
        tableTourItems.clear();

        List<tourModel> tourItems = tourManager.SearchTourItems(searchingField.textProperty().getValue(), false);
        tableTourItems.addAll(tourItems);
    }

    private void SetCurrentTourItem(){
        tableTourView.getSelectionModel().selectedItemProperty().addListener(((observableValue, oldValue, newValue) -> {
            if((newValue != null) && (oldValue != newValue)){
                currentItem = newValue;
                System.out.println(currentItem.tourName);
            }
        }));
    }



    public void logButtonAction(ActionEvent actionEvent) throws IOException {
        Main m = new Main();
        m.changeScene("views/logView.fxml");
    }

    public void homeButtonAction(ActionEvent actionEvent) throws IOException {
        Main m = new Main();
        m.changeScene("views/homeView.fxml");
    }

    public void clearAction(ActionEvent actionEvent) {
        tableTourItems.clear();

        searchingField.textProperty().setValue("");

        List<tourModel> TourItems = tourManager.GetTourItems();
        tableTourItems.addAll(TourItems);

    }


    public void addTourButton(ActionEvent actionEvent) {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getClassLoader().getResource("gui/views/addTourView.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Adding Tours");
            stage.setScene(new Scene(root, 700, 450));
            stage.show();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deleteButton(ActionEvent actionEvent) {
        if(currentItem != null) {
            tourManager.DeleteTourItem(currentItem.tourName,currentItem.tourDescription);

            //update table
            tableTourItems.clear();
            List<tourModel> TourItems = tourManager.GetTourItems();
            tableTourItems.addAll(TourItems);
        }
        currentItem = null;
    }

    public void editButton(ActionEvent actionEvent) {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getClassLoader().getResource("gui/views/modifyTourView.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Edit Tours");
            stage.setScene(new Scene(root, 700, 450));
            stage.show();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
