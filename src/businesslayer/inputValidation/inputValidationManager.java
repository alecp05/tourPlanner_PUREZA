package businesslayer.inputValidation;

import javafx.scene.control.TextField;

public interface inputValidationManager {
    public void onlyNumbers(TextField textfield);
    public void onlyCharacters(TextField textField);
    public void onlyOneToTen(TextField textfield);
    public boolean checkFormat(String logDate, String logDistance, String logTime);
}
