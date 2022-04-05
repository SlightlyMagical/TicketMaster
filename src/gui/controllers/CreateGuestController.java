package gui.controllers;

import be.Guest;
import be.Ticket;
import gui.models.DialogHandler;
import gui.models.EventModel;
import gui.models.GuestModel;
import gui.models.TicketModel;
import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.UUID;

public class CreateGuestController {

    private final GuestModel guestModel;
    private final TicketModel ticketModel;

    private EventModel eventModel;
    public TextField firstNameInput;
    public TextField lastNameInput;
    public TextField eMailInput;
    public ComboBox<String> cbTicketTypes;

    public CreateGuestController() {
        guestModel = new GuestModel();
        ticketModel = new TicketModel();
    }

    public void btnCreateNewGuest(ActionEvent actionEvent) {
        if (firstNameInput.getText().trim().equals("") || lastNameInput.getText().trim().equals("") || eMailInput.getText().trim().equals(""))
            DialogHandler.informationAlert("Fields cannot be empty!");
        else if (cbTicketTypes.getSelectionModel().getSelectedItem() == null)
            DialogHandler.informationAlert("Select a ticket type first!");
        else {
            String firstName = firstNameInput.getText().trim();
            String lastName = lastNameInput.getText().trim();
            String eMail = eMailInput.getText().trim().toLowerCase();

            Guest guest = new Guest(-1, firstName, lastName, eMail);
            guest = guestModel.createGuest(guest);
            Ticket ticket = new Ticket(UUID.randomUUID().toString(), cbTicketTypes.getSelectionModel().getSelectedItem(), eventModel.getCurrentEvent(), guest);

            if (ticketModel.newTicket(ticket)) {
                DialogHandler.informationAlert("Ticket was created!");
                resetFields();
            }
            else
                DialogHandler.informationAlert("This guest already has a ticket to this event");
        }
    }

    public void btnCancel(ActionEvent actionEvent) {
        ((Stage) (firstNameInput.getScene().getWindow())).close();
    }

    public void setInfo(EventModel eventModel){
        this.eventModel = eventModel;
        cbTicketTypes.setItems(eventModel.getTicketTypes());
    }

    private void resetFields(){
        firstNameInput.clear();
        lastNameInput.clear();
        eMailInput.clear();
    }
}
