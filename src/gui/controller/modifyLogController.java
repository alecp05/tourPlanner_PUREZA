package gui.controller;

import businesslayer.logManagerFactory;
import businesslayer.tourManagerFactory;
import gui.Main;
import gui.viewmodels.modifyLogViewModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import lombok.SneakyThrows;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class modifyLogController implements Initializable {

    private static final Logger logger = LogManager.getLogger(modifyLogController.class);

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

    private ObservableList<String> logChoices;

    @FXML
    private ChoiceBox<String> logChoiceBox;

    public modifyLogViewModel modifyLogViewModel = new modifyLogViewModel();

    @SneakyThrows
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Bindings to modifyLogViewModel
        logDate.textProperty().bindBidirectional(modifyLogViewModel.getLogDate());
        logReport.textProperty().bindBidirectional(modifyLogViewModel.getLogReport());
        logDistance.textProperty().bindBidirectional(modifyLogViewModel.getLogDistance());
        logTotalTime.textProperty().bindBidirectional(modifyLogViewModel.getLogTotalTime());
        logRating.textProperty().bindBidirectional(modifyLogViewModel.getLogRating());
        logAverageSpeed.textProperty().bindBidirectional(modifyLogViewModel.getLogAverageSpeed());
        logWeather.textProperty().bindBidirectional(modifyLogViewModel.getLogWeather());
        logBreaks.textProperty().bindBidirectional(modifyLogViewModel.getLogBreaks());
        logStart.textProperty().bindBidirectional(modifyLogViewModel.getLogStart());
        logEnd.textProperty().bindBidirectional(modifyLogViewModel.getLogEnd());


        updateChoiceBox();

        logger.info("Initialized modifyLogView");
    }

    public void updateChoiceBox() throws IOException {
        logChoices = FXCollections.observableArrayList();
        List<String> tourNames = modifyLogViewModel.gettingLogNames();

        logChoices.addAll(tourNames);
        logChoiceBox.setItems(logChoices);
    }

    public void editLogButton(ActionEvent actionEvent) throws IOException {
        //String now = logChoiceBox.getValue();
        int indexNumber = logChoices.indexOf(logChoiceBox.getValue());
        //System.out.println(indexNumber);

        String indexTourDate = logChoiceBox.getValue();
        //System.out.println(indexTourDate);

        if(indexTourDate!=null) {
            modifyLogViewModel.updatingLog(indexTourDate);

            //clear choiceBox and fields
            clearFields();
            updateChoiceBox();

            logger.info("Edit-Button clicked");

            //update tourView
            Main m = new Main();
            m.changeScene("views/logView.fxml");
        }
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
}
