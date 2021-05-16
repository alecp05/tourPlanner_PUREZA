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
}
