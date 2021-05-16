package gui.controller;

import businesslayer.logManager;
import businesslayer.logManagerFactory;
import businesslayer.tourManagerFactory;
import gui.Main;
import gui.viewmodels.addLogViewModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lombok.SneakyThrows;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class addLogController implements Initializable {

    private static final Logger logger = LogManager.getLogger(addLogController.class);

    private addLogViewModel addLogViewModel = new addLogViewModel();

    //public String logName;
    public TextField logDate;
    public TextField logReport;
    public TextField logDistance;
    public TextField logTotalTime;
    public TextField logRating;
    public TextField logAverageSpeed;
    public TextField logWeather;
    public TextField logBreaks;
    public TextField logStart;
    public TextField logEnd;

    private ObservableList<String> tourChoices;
    @FXML
    private ChoiceBox<String> tourChoiceBox;


    public void addLogButton(ActionEvent actionEvent) throws IOException {

        String logName = tourChoiceBox.getValue();

        int tempNumber = addLogViewModel.addingLog(logName);

        if(tempNumber == 2){
            Parent root;
            try {
                root = FXMLLoader.load(getClass().getClassLoader().getResource("gui/views/emptyFieldsView.fxml"));
                Stage stage = new Stage();
                stage.setTitle("Error on Input");
                stage.setScene(new Scene(root, 700, 450));
                stage.show();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            logger.warn("Empty UserInput-Fields");
        }else if(tempNumber == 3){
            Parent root;
            try {
                root = FXMLLoader.load(getClass().getClassLoader().getResource("gui/views/wrongFormatView.fxml"));
                Stage stage = new Stage();
                stage.setTitle("Error on Format");
                stage.setScene(new Scene(root, 700, 450));
                stage.show();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            logger.warn("Wrong Format from UserInput");
        }else{
            clearFields();

            //update tourView
            Main m = new Main();
            m.changeScene("views/logView.fxml");
        }
        logger.info("Add-Button clicked");
    }

    public void clearFields(){
        logDate.clear();
        logReport.clear();
        logDistance.clear();
        logTotalTime.clear();
        logRating.clear();
        logAverageSpeed.clear();
        logWeather.clear();
        logBreaks.clear();
        logStart.clear();
        logEnd.clear();
    }

    @SneakyThrows
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //Bindings to addLogViewModel
        logDate.textProperty().bindBidirectional(addLogViewModel.getLogDate());
        logReport.textProperty().bindBidirectional(addLogViewModel.getLogReport());
        logDistance.textProperty().bindBidirectional(addLogViewModel.getLogDistance());
        logTotalTime.textProperty().bindBidirectional(addLogViewModel.getLogTotalTime());
        logRating.textProperty().bindBidirectional(addLogViewModel.getLogRating());
        logAverageSpeed.textProperty().bindBidirectional(addLogViewModel.getLogAverageSpeed());
        logWeather.textProperty().bindBidirectional(addLogViewModel.getLogWeather());
        logBreaks.textProperty().bindBidirectional(addLogViewModel.getLogBreaks());
        logStart.textProperty().bindBidirectional(addLogViewModel.getLogStart());
        logEnd.textProperty().bindBidirectional(addLogViewModel.getLogEnd());


        //tourManager = tourManagerFactory.GetTourManager();
        tourChoices = FXCollections.observableArrayList();

        List<String> tourNames = new ArrayList<String>();
        tourNames = addLogViewModel.gettingTours();

        tourChoices.addAll(tourNames);
        tourChoiceBox.getItems().addAll(tourChoices);
        //System.out.println(tourChoiceBox);

        //set TextField restrictions
        addLogViewModel.setFieldRestrictions(logRating,logBreaks,logStart,logEnd);


        logger.info("Initialized addLogView");
    }
}
