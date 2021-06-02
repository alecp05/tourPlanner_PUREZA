package gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.IOException;

public class Main extends Application {

    private static Stage stg;
    private static final Logger logger = LogManager.getLogger(Main.class);

    @Override
    public void start(Stage primaryStage) throws Exception{
        stg = primaryStage;
        primaryStage.setResizable(true);

        // fxml created with SceneBuilder
        Parent root = FXMLLoader.load(getClass().getResource("views/homeView.fxml"));
        //System.out.println("fxml loaded");

        // bootstrap "window" named stage
        primaryStage.setTitle("Welcome to TourPlanner 1.0");
        //System.out.println("set title");

        // set scene into stage in defined size
        primaryStage.setScene(new Scene(root, 900, 600));
        //System.out.println("set scene");

        // let's go
        primaryStage.show();
        //System.out.println("show stage");

        logger.info("First Scene has been created");
    }

    public void changeScene(String fxml) throws IOException{
        Parent pane = FXMLLoader.load(getClass().getResource(fxml));
        stg.getScene().setRoot(pane);

        logger.info("The Scene has been changed/updated");
    }

    public static void main(String[] args) {
        launch(args);
    }
}
