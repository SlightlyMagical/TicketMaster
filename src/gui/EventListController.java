package gui;

import be.TicketEvent;
import javafx.event.ActionEvent;
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
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ResourceBundle;

public class EventListController implements Initializable {
    public VBox eventVBox;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        TicketEvent ticketEvent = new TicketEvent("Friday Bar", "EASV", LocalDate.now(), "Awesome Event",
                "This is a guide");
        ticketEvent.setTicketEventStartTime(LocalTime.now());
        createEventField(ticketEvent);
    }

    public void newEventAction(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("CreateEventView.fxml"));
        stage.setScene(new Scene(root));
        stage.initOwner(eventVBox.getScene().getWindow());
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Create new event");
        stage.show();
    }

    private void createEventField(TicketEvent ticketEvent){
        HBox hBox = new HBox();
        hBox.setPadding(new Insets(10, 30, 10, 10));
        hBox.setStyle("-fx-border-color: black;-fx-alignment: CENTER_LEFT");
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
        Label eventName = new Label(ticketEvent.getTicketEventName());
        eventName.setStyle("-fx-font-size: 25; -fx-font-weight: bold");
        Label location = new Label(ticketEvent.getTicketEventLocation());
        Button button = new Button("More info");
        button.setOnAction((event) -> {
            try {
                showEventInfo();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        dateBox.getChildren().addAll(date, time);
        eventBox.getChildren().addAll(eventName, location);
        hBox.getChildren().addAll(dateBox, eventBox, button);
        eventVBox.getChildren().add(hBox);
        // TODO: adjust size of items
    }

    private void showEventInfo() throws IOException {
        Stage stage = (Stage) eventVBox.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource(""));
        stage.setScene(new Scene(root));
        stage.setTitle("Create new event");
        stage.show();
        // TODO: insert correct fxml, and make sure it has reference to this stage
    }
}
