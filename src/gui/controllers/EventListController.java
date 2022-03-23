package gui.controllers;

import be.TicketEvent;
import gui.models.EventModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class EventListController implements Initializable {
    @FXML
    private VBox eventVBox;

    private EventModel eventModel;

    public EventListController() {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        eventModel = new EventModel();
        createEventField(eventModel.getEventList());
    }

    public void newEventAction(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("../views/CreateEventView.fxml"));
        stage.setScene(new Scene(root));
        stage.initOwner(eventVBox.getScene().getWindow());
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Create new event");
        stage.showAndWait();
        createEventField(eventModel.getEventList());
    }

    private void createEventField(List<TicketEvent> eventList){
        eventVBox.getChildren().clear();
        for (TicketEvent ticketEvent : eventList) {
            HBox hBox = new HBox();
            hBox.setPadding(new Insets(20, 30, 20, 10));
            VBox dateBox = new VBox();
            dateBox.setSpacing(10);
            dateBox.setPrefWidth(350);
            dateBox.setStyle("-fx-alignment: CENTER_LEFT");
            VBox eventBox = new VBox();
            eventBox.setSpacing(10);
            eventBox.setPrefWidth(350);
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

    private void showEventInfo(TicketEvent ticketEvent) throws IOException {
        Stage stage = (Stage) eventVBox.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource(""));
        stage.setScene(new Scene(root));
        stage.show();
        // TODO: insert correct fxml, and make sure it has reference to this stage
    }
}
