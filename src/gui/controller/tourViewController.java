package gui.controller;

import businesslayer.tourManagerFactory;
import gui.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lombok.SneakyThrows;
import models.tourModel;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class tourViewController implements Initializable {

    private static final Logger logger = LogManager.getLogger(tourViewController.class);

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


    @FXML
    private ImageView mapImageView;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tourManager = tourManagerFactory.GetTourManager();

        //tour TableView
        setUpTourTable();
        formatTourTableColumns();

        SetCurrentTourItem();


        Image defaultImage = new Image(getClass().getResource("/tourImages/mapLogo01.png").toExternalForm());
        mapImageView.setImage(defaultImage);

        logger.info("Initialized TourView");
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

        logger.info("Search Function clicked");
    }

    private void SetCurrentTourItem(){
        tableTourView.getSelectionModel().selectedItemProperty().addListener(((observableValue, oldValue, newValue) -> {
            if((newValue != null) && (oldValue != newValue)){
                currentItem = newValue;
                //System.out.println(currentItem.tourName + "--------");

                logger.info("Current Item is set");
                try {
                    setTourImage(currentItem.tourName);
                } catch (IOException | URISyntaxException e) {
                    e.printStackTrace();
                }
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
        currentItem = null;

        logger.info("Cleared everything");

    }

    public void addTourButton(ActionEvent actionEvent) {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getClassLoader().getResource("gui/views/addTourView.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Adding Tours");
            stage.setScene(new Scene(root, 700, 450));
            stage.show();

            logger.info("Add-Button clicked");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deleteButton(ActionEvent actionEvent) {
        if(currentItem != null) {
            tourManager.DeleteTourItem(currentItem.tourName,currentItem.tourDescription);
            tourManager.DeleteImage(currentItem.tourName);

            //update table
            tableTourItems.clear();
            List<tourModel> TourItems = tourManager.GetTourItems();
            tableTourItems.addAll(TourItems);


            logger.info("Delete-Button clicked");
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

            logger.info("Edit-Button clicked");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setTourImage(String tourName) throws IOException, URISyntaxException {
        String imageName = tourName.replace(" ", "") + ".jpg";
        String pathName = "/tourImages/" + imageName;

        File tmpDir = new File("./src"+pathName);
        boolean exists = tmpDir.exists();
        if(exists){
            Image placeImage;

            if(getClass().getResource(pathName) == null){
                //System.out.println(currentItem.tourName + currentItem.tourStart + currentItem.tourEnd);
                Image tourImage = tourManager.GetTourImage(currentItem.tourName , currentItem.tourStart , currentItem.tourEnd);
                mapImageView.setImage(tourImage);
            }else {
                //ImageIcon trying = new ImageIcon(getClass().getResource(pathName).toURI().toString());
                placeImage = new Image(getClass().getResource(pathName).toURI().toString());
                mapImageView.setImage(placeImage);

                //System.out.println(mapImageView.cacheProperty());
            }

            logger.info("TourImage shown");
        }else {
            Image placeImage;
            placeImage = new Image(getClass().getResource("/tourImages/mapLogo01.png").toExternalForm());
            mapImageView.setImage(placeImage);

            logger.warn("No TourImage found");
        }
    }


    public void printReportButton(ActionEvent actionEvent) throws FileNotFoundException, MalformedURLException {
        if(currentItem != null) {
            tourManager.GetTourNameForReport(currentItem.tourName);
            logger.info("Print Report-Button clicked");
        }else {
            logger.warn("No Tour selected");
        }
        currentItem = null;
    }
}
