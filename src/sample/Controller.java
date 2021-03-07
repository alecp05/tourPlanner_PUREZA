package sample;

import javafx.beans.binding.Bindings;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ObjectPropertyBase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    // create custom viewmodel
    public MainViewModel viewModel = new MainViewModel();

    // add fx:id and use intelliJ to create field in controller
    public TextField InputTextField;
    public Label OutputLabel;
    public Label OutputLabelSmile;

    public Controller()
    {
        System.out.println("Controller created");
    }

    @FXML
    public void calculateOutput(ActionEvent actionEvent) {
        System.out.println("Controller calculate");
        viewModel.calculateOutputString();
    }

    @FXML
    public void putSmiley(ActionEvent actionEvent) {
        System.out.println("Smiley");
        viewModel.setOutputSmiley();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Controller init");

        InputTextField.textProperty().bindBidirectional(viewModel.inputProperty());

        // OutputLabel.textProperty().bindBidirectional(viewModel.outputProperty());
        Bindings.bindBidirectional(OutputLabel.textProperty(), viewModel.outputProperty());
        Bindings.bindBidirectional(OutputLabelSmile.textProperty(), viewModel.outputSmiley());
    }
}
