package gui.controller;

import gui.Main;
import javafx.event.ActionEvent;

import java.io.IOException;

public class logViewController {
    public void tourButtonAction(ActionEvent actionEvent) throws IOException {
        Main m = new Main();
        m.changeScene("views/tourView.fxml");
    }

    public void homeButtonAction(ActionEvent actionEvent) throws IOException {
        Main m = new Main();
        m.changeScene("views/homeView.fxml");
    }
}
