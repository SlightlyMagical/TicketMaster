package gui.controllers;

import be.Ticket;
import be.TicketEvent;
import com.google.zxing.WriterException;
import gui.SceneManager;
import gui.models.DialogHandler;
import gui.models.EventModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

import java.io.IOException;
import java.util.ArrayList;
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
    private final EventModel eventModel;
    private TicketEvent ticketEvent;

    public EventDetailsController() throws IOException {
        sceneManager = SceneManager.getInstance();
        eventModel = new EventModel();
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
        String name = DialogHandler.inputDialog("Create new ticket type").trim();

        if (name.equalsIgnoreCase("standard") || ticketEvent.getTicketTypes().contains(name))
            DialogHandler.informationAlert("That ticket type already exists");
        else if (!name.equals("")) {
            ticketEvent.getTicketTypes().add(name);
            eventModel.createTicketType(ticketEvent.getId(), name);

            Button button = new Button(name);
            button.setOnAction(e -> deleteTicketType(button, name));
            ticketTypeBox.getChildren().add(button);
        }

        ticketTypeBox.getChildren().remove(newTypeBtn);
        ticketTypeBox.getChildren().add(newTypeBtn); // Makes sure the "new" button is last
    }

    private void setTicketTypes(ArrayList<String> ticketTypes){
        ticketTypeBox.getChildren().clear();
        ticketTypeBox.getChildren().add(standardBtn); //Makes sure "Standard" is first
        for (String type : ticketTypes){
            Button button = new Button(type);
            button.setOnAction( e -> deleteTicketType(button, type));
            ticketTypeBox.getChildren().add(button);
        }
        ticketTypeBox.getChildren().add(newTypeBtn); //Makes sure the "new" button is last
    }

    private void deleteTicketType(Button button, String name){
        if (DialogHandler.confirmationAlert("Do you want to delete this ticket type?")) {
            ticketEvent.getTicketTypes().remove(name);
            eventModel.deleteTicketType(ticketEvent.getId(), name);
            ticketTypeBox.getChildren().remove(button);
        }
    }
}
