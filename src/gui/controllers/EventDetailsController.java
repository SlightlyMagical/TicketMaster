package gui.controllers;

import be.Ticket;
import be.TicketEvent;
import com.google.zxing.WriterException;
import gui.SceneManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.UUID;

public class EventDetailsController{
    @FXML
    private HBox ticketTypeBox;
    @FXML
    private Button standardBtn;
    @FXML
    private Button newTypeBtn;
    @FXML
    private Label lblEventName;
    @FXML
    private Label lblLocation;
    @FXML
    private Label lblStartDate;
    @FXML
    private Label lblEndDate;
    @FXML
    private Label lblStartTime;
    @FXML
    private Label lblEndTime;
    @FXML
    private Label lblDescription;
    @FXML
    private Label lblLocationGuide;

    private final SceneManager sceneManager;
    private TicketEvent ticketEvent;

    public EventDetailsController() throws IOException {
        sceneManager = SceneManager.getInstance();
    }

    /**
     * Returns to the previous scene
     */
    public void backButton(ActionEvent actionEvent) throws IOException {
        sceneManager.goBack();
    }

    /**
     * Fills in the event info
     */
    public void setInfo(TicketEvent event) {
        this.ticketEvent = event;
        lblEventName.setText(event.getName());
        lblStartDate.setText(event.getStartDateAsString());
        lblStartTime.setText(event.getStartTimeAsString());
        lblDescription.setText(event.getDescription());
        lblLocation.setText(event.getLocation());
        try{
            lblEndDate.setText(event.getEndDateAsString());
        } catch (Exception ignored) {
        }
        try{
            lblEndTime.setText(event.getEndTimeAsString());
        } catch (Exception ignored) {
        }
        try{
            lblLocationGuide.setText(event.getLocationGuide());
        } catch (Exception ignored) {
        }

        setTicketTypes(event.getTicketTypes());
    }

    /**
     * Shows a sample ticket for the current event
     */
    public void showTicket(ActionEvent actionEvent) throws IOException, WriterException {
        Ticket ticket = new Ticket(1, UUID.randomUUID().toString(),"Standard",ticketEvent);
        sceneManager.showTicket(ticket);
    }

    /**
     * Adds a new ticket type to this event
     */
    public void addTicketType(ActionEvent actionEvent) {
        ticketTypeBox.getChildren().remove(newTypeBtn);

        // TODO: open input dialog to get input for ticket type name

        ticketTypeBox.getChildren().add(newTypeBtn); // Makes sure the "new" button is last
    }

    private void setTicketTypes(ArrayList<String> ticketTypes){
        ticketTypeBox.getChildren().clear();
        ticketTypeBox.getChildren().add(standardBtn); //Makes sure "Standard" is first
        for (String type : ticketTypes){
            Button button = new Button(type);
            button.setOnAction( e -> deleteTicketType(button));
            ticketTypeBox.getChildren().add(button);
        }
        ticketTypeBox.getChildren().add(newTypeBtn); //
    }

    private void deleteTicketType(Button button){
        // TODO: implement warning
        ticketTypeBox.getChildren().remove(button);
    }
}
