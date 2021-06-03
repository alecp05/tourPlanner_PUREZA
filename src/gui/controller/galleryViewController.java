package gui.controller;

import businesslayer.tourImplementation;
import gui.Main;
import gui.viewmodels.galleryViewModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.stage.FileChooser;
import models.tourModel;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;

public class galleryViewController implements Initializable {

    private static final Logger logger = LogManager.getLogger(galleryViewController.class);

    public ListView<String> galleryView;
    public ImageView imageViewGallery;

    private ObservableList<String> imageNames;

    public galleryViewModel galleryViewModel = new galleryViewModel();

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
        imageNames.addAll(galleryViewModel.getGalleryNames());
        galleryView.setItems(imageNames);

        //System.out.println(tourManager.getGalleryNames());
    }

    private void setCurrentTourItem(){
        galleryView.getSelectionModel().selectedItemProperty().addListener(((observableValue, oldValue, newValue) -> {
            if((newValue != null) && (oldValue != newValue)){
                currentItem = newValue;

                setImages(currentItem);
                //System.out.println(currentItem);
                logger.info("Current Item is set");
            }
        }));
    }

    public void setImages(String currentName){
        BufferedImage img = galleryViewModel.getGalleryImage(currentName);
        WritableImage image = SwingFXUtils.toFXImage(img, null);
        imageViewGallery.setImage(image);
    }

    public void uploadImageButton(ActionEvent actionEvent) throws IOException {

        galleryViewModel.uploadingButton();
        setUpListView();
        logger.info("Upload-Button clicked");
    }

    public void deleteGalleryImage(ActionEvent actionEvent) {

        galleryViewModel.deletingButton(currentItem);

        //clear View
        setUpListView();
        imageViewGallery.setImage(null);
        logger.info("Upload-Button clicked");
    }
}
