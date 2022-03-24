package gui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SceneManager {
    private static SceneManager instance;
    private Stage primaryStage;

    private Scene lastScene;
    private Scene currentScene;
    private Scene eventList;


    private SceneManager() throws IOException {
        createEventListScene();
    }

    public static SceneManager getInstance() throws IOException {
        if(instance == null)
            instance = new SceneManager();

        return instance;
    }

    public void setScene(Stage primaryStage) throws IOException {
        this.primaryStage = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("views/EventListView.fxml"));
        currentScene = new Scene(root);
        primaryStage.setScene(currentScene);
        primaryStage.setTitle("Ticket Master");
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public void goBack() throws IOException {
        primaryStage.setScene(eventList);
    }

    public void showEventList(){
        primaryStage.setScene(eventList);
    }

    private void createEventListScene() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("views/EventListView.fxml"));
        eventList = new Scene(root);
    }

    private Scene createCreateEventScene() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("views/CreateEventView.fxml"));
        return new Scene(root);
    }
}
