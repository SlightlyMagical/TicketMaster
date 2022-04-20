package gui.controllers;

import be.Guest;
import be.Ticket;
import be.TicketEvent;
import gui.SceneManager;
import gui.models.DialogHandler;
import gui.models.EventModel;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

import java.io.IOException;
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
    private EventModel eventModel;
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
    public void setInfo(TicketEvent event, EventModel eventModel) {
        this.ticketEvent = event;
        this.eventModel = eventModel;
        eventModel.setCurrentEvent(ticketEvent);
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
    public void showSampleTicket(ActionEvent actionEvent) throws IOException {
        Guest guest = new Guest(-1, "Test", "Tester", "Test@email.com");
        Ticket ticket = new Ticket(UUID.randomUUID().toString(),"Standard",ticketEvent, guest);
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

    private void setTicketTypes(ObservableList<String> ticketTypes){
        ticketTypeBox.getChildren().clear();
        ticketTypeBox.getChildren().add(standardBtn); //Makes sure "Standard" is first
        for (String type : ticketTypes){
            if(!type.equals("Standard")) {
                Button button = new Button(type);
                button.setOnAction(e -> deleteTicketType(button, type));
                ticketTypeBox.getChildren().add(button);
            }
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


    public void manageGuests(ActionEvent actionEvent) throws IOException {
        sceneManager.showGuestManager(eventModel);
    }

    public void logout(ActionEvent actionEvent) throws IOException {
        sceneManager.showLogin();
    }

    public void deleteEvent(ActionEvent actionEvent) throws IOException {
        if(!ticketEvent.getListOfTickets().isEmpty())
            DialogHandler.informationAlert("This event cannot be deleted while tickets for it exists");
        else if (DialogHandler.confirmationAlert("Are you sure you want to delete this event?")){
            eventModel.deleteEvent(ticketEvent);
            sceneManager.goBack();
        }
    }

}
