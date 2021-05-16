package businesslayer.inputValidation;

import javafx.scene.control.TextFormatter;

import javafx.scene.control.TextField;

public class inputValidationImplementation implements inputValidationManager{

    public inputValidationImplementation(){};

    @Override
    public void onlyNumbers(TextField textfield) {
        textfield.setTextFormatter(new TextFormatter<>(change ->
                (change.getControlNewText().matches("([1-9][0-9]*)?")) ? change : null));
    }

    @Override
    public void onlyCharacters(TextField textField){
        textField.setTextFormatter(new TextFormatter<>(change ->
                (change.getControlNewText().matches("[a-zA-Z]*")) ? change : null));
    }

    @Override
    public void onlyOneToTen(TextField textfield) {
        textfield.setTextFormatter(new TextFormatter<>(change ->
                (change.getControlNewText().matches("[1-9]?[0]?")) ? change : null));
    }

    @Override
    public boolean checkFormat(String logDate, String logDistance, String logTime) {
        if(logDate.matches("\\d{2}-\\d{2}-\\d{4}") && logDistance.matches("[1-9][0-9]* km")
                && logTime.matches("[1-9][0-9]* h")){
            return true;
        } else {
            return false;
        }
    }

}
