package gui.controller;

import gui.Main;
import javafx.event.ActionEvent;

import java.io.IOException;

public class tourViewController {
    public void logButtonAction(ActionEvent actionEvent) throws IOException {
        Main m = new Main();
        m.changeScene("views/logView.fxml");
    }

    public void homeButtonAction(ActionEvent actionEvent) throws IOException {
        Main m = new Main();
        m.changeScene("views/homeView.fxml");
    }
}
