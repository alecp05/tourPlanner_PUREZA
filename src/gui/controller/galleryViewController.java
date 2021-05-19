package gui.controller;

import businesslayer.tourImplementation;
import gui.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import models.tourModel;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;

public class galleryViewController implements Initializable {

    public ListView<String> galleryView;
    public ImageView imageViewGallery;

    private ObservableList<String> imageNames;

    private businesslayer.tourManager tourManager = new tourImplementation();

    //select current
    private String currentItem = null;

    public galleryViewController() throws IOException {
    }

    public void tourButtonAction(ActionEvent actionEvent) throws IOException {
        Main m = new Main();
        m.changeScene("views/tourView.fxml");
    }

    public void logButtonAction(ActionEvent actionEvent) throws IOException {
        Main m = new Main();
        m.changeScene("views/logView.fxml");
    }
    public void galleryButtonAction(ActionEvent actionEvent) throws IOException {
        Main m = new Main();
        m.changeScene("views/galleryView.fxml");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setUpListView();
        setCurrentTourItem();
    }

    public void setUpListView(){
        imageNames = FXCollections.observableArrayList();
        imageNames.addAll(tourManager.getGalleryNames());
        galleryView.setItems(imageNames);

        //System.out.println(tourManager.getGalleryNames());
    }

    private void setCurrentTourItem(){
        galleryView.getSelectionModel().selectedItemProperty().addListener(((observableValue, oldValue, newValue) -> {
            if((newValue != null) && (oldValue != newValue)){
                currentItem = newValue;
                //System.out.println(currentItem.tourName + "--------");

                //logger.info("Current Item is set");

                setImages(currentItem);
                System.out.println(currentItem);
            }
        }));
    }

    public void setImages(String currentName){
        BufferedImage img = tourManager.getGalleryImages(currentName);
        WritableImage image = SwingFXUtils.toFXImage(img, null);
        imageViewGallery.setImage(image);
    }
}
