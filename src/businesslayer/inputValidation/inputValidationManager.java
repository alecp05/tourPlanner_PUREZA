package businesslayer.inputValidation;

import javafx.scene.control.TextField;

public interface inputValidationManager {
    public void onlyNumbers(TextField textfield);
    public void onlyCharacters(TextField textField);
}
