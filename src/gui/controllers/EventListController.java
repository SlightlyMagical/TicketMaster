package gui.controllers;

import be.TicketEvent;
import gui.SceneManager;
import gui.models.EventModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class EventListController implements Initializable {
    @FXML
    private VBox eventVBox;

    private EventModel eventModel;
    private SceneManager sceneManager;

    public EventListController() throws IOException {
        sceneManager = SceneManager.getInstance();
    }

    /**
     * Initializes the event list
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        eventModel = new EventModel();
        createEventFields(eventModel.getEventList());
    }

    /**
     * Opens a window for creating a new event, and updates the event list when the new window is closed
     */
    public void newEventAction(ActionEvent actionEvent) throws IOException {
        sceneManager.showNewEventWindow();
        createEventFields(eventModel.getEventList());

    }

    /**
     * Creates a field for each individual event that is to be shown
     */
    private void createEventFields(List<TicketEvent> eventList){
        eventVBox.getChildren().clear();
        for (TicketEvent ticketEvent : eventList) {
            HBox hBox = new HBox();
            hBox.setPadding(new Insets(20, 30, 20, 10));
            VBox dateBox = new VBox();
            dateBox.setSpacing(10);
            dateBox.setPrefWidth(300);
            dateBox.setStyle("-fx-alignment: CENTER_LEFT");
            VBox eventBox = new VBox();
            eventBox.setSpacing(10);
            eventBox.setPrefWidth(400);
            eventBox.setStyle("-fx-alignment: CENTER_LEFT");
            Label date = new Label(ticketEvent.getStartDateAsString());
            Label time = new Label(ticketEvent.getStartTimeAsString());
            Label eventName = new Label(ticketEvent.getName());
            eventName.setStyle("-fx-font-size: 25; -fx-font-weight: bold");
            Label location = new Label(ticketEvent.getLocation());
            Button button = new Button("More info");
            button.setOnAction((event) -> {
                try {
                    showEventInfo(ticketEvent);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            dateBox.getChildren().addAll(date, time);
            eventBox.getChildren().addAll(eventName, location);
            hBox.getChildren().addAll(dateBox, eventBox, button);
            eventVBox.getChildren().add(hBox);
        }
    }

    /**
     * Change the scene to show details of the selected event
     */
    private void showEventInfo(TicketEvent ticketEvent) throws IOException {
        sceneManager.showEventDetailScene(ticketEvent);
    }
}
