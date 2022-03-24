package gui;

import be.TicketEvent;
import gui.controllers.EventDetailsController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class SceneManager {
    private static SceneManager instance;
    private Stage primaryStage;

    private Scene lastScene;
    private Scene currentScene;
    private Scene eventList;


    private SceneManager() throws IOException {
    }

    /**
     * Returns the instance of the scene manager. creates one if it does not exist yet
     */
    public static SceneManager getInstance() throws IOException {
        if(instance == null)
            instance = new SceneManager();

        return instance;
    }

    public void setStartScene(Stage primaryStage) throws IOException {
        this.primaryStage = primaryStage;
        showEventList();
        primaryStage.setTitle("Ticket Master");
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public void goBack() throws IOException {
        primaryStage.setScene(eventList);
    }

    public void showEventList() throws IOException {
        if (eventList == null)
            createEventListScene();
        primaryStage.setScene(eventList);
    }

    public void showNewEventWindow() throws IOException {
        Stage stage = new Stage();
        stage.setScene(createCreateEventScene());
        stage.setTitle("Create new event");
        stage.initOwner(primaryStage);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        stage.showAndWait();
    }

    private void createEventListScene() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("views/EventListView.fxml"));
        eventList = new Scene(root);
    }

    private Scene createCreateEventScene() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("views/CreateEventView.fxml"));
        return new Scene(root);
        // TODO: Edit method so that it can be used for both new event and edit event
    }

    public void showEventDetailScene(TicketEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("views/EventDetails.fxml"));
        primaryStage.setScene(new Scene(fxmlLoader.load()));
        EventDetailsController controller = fxmlLoader.getController();
        controller.setInfo(event);
    }
}
