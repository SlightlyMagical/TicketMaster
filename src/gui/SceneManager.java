package gui;

import be.Ticket;
import be.TicketEvent;
import gui.controllers.CreateGuestController;
import gui.controllers.EventDetailsController;
import gui.controllers.TicketController;
import gui.models.EventModel;
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

    /**
     * The first scene to be shown on program launch
     */
    public void showStartScene(Stage primaryStage) throws IOException {
        this.primaryStage = primaryStage;
        showLogin();
        primaryStage.setTitle("Ticket Master");
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public void showLogin() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("views/LoginView.fxml"));
        primaryStage.setScene(new Scene(root));
    }


    /**
     * Returns to the previous screen
     */
    public void goBack() throws IOException {
        primaryStage.setScene(eventList);
        // TODO: actually implement this
    }

    /**
     * Shows the event list screen
     */
    public void showEventList() throws IOException {
        if (eventList == null)
            createEventListScene();
        primaryStage.setScene(eventList);
        primaryStage.centerOnScreen();
    }

    /**
     * Opens a new window for creating a new event
     */
    public void showNewEventWindow() throws IOException {
        Stage stage = new Stage();
        stage.setScene(createCreateEventScene());
        stage.setTitle("Create new event");
        stage.initOwner(primaryStage);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        stage.showAndWait();
    }

    /**
     * Creates the event list scene
     */
    private void createEventListScene() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("views/EventListView.fxml"));
        eventList = new Scene(root);
    }

    /**
     * Creates the scene for creating new events
     */
    private Scene createCreateEventScene() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("views/CreateEventView.fxml"));
        return new Scene(root);
        // TODO: Edit method so that it can be used for both new event and edit event
    }

    /**
     * Changes the current scene to display event details
     */
    public void showEventDetailScene(TicketEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("views/EventDetails.fxml"));
        primaryStage.setScene(new Scene(fxmlLoader.load()));
        EventDetailsController controller = fxmlLoader.getController();
        controller.setInfo(event);
    }

    /**
     * Opens a new window to show the ticket
     */
    public void showTicket(Ticket ticket) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("views/Ticket.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(fxmlLoader.load()));
        stage.setTitle("Ticket");
        stage.initOwner(primaryStage);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);

        TicketController controller = fxmlLoader.getController();
        controller.setTicketInfo(ticket);

        stage.show();
    }

    public void createGuest (EventModel eventModel) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("views/CreateGuest.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(fxmlLoader.load()));
        stage.setTitle("Ticket");
        stage.initOwner(primaryStage);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        CreateGuestController controller = fxmlLoader.getController();
        controller.setInfo(eventModel);
        stage.show();
    }
}
