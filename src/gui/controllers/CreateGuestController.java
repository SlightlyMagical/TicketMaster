package gui.controllers;

import be.Guest;
import be.User;
import gui.models.DialogHandler;
import gui.models.EventModel;
import gui.models.GuestModel;
import gui.models.UserModel;
import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CreateGuestController {

    private final GuestModel guestModel;
    private EventModel eventModel;
    public TextField firstNameInput;
    public TextField lastNameInput;
    public TextField eMailInput;
    public ComboBox<String> cbTicketTypes;

    public CreateGuestController() {
        guestModel = new GuestModel();
    }
    public void btnCreateNewGuest(ActionEvent actionEvent) {
        if (firstNameInput.getText().trim().equals("") || lastNameInput.getText().trim().equals("") || eMailInput.getText().trim().equals(""))
            DialogHandler.informationAlert("Fields cannot be empty!");
        else {
            String firstName = firstNameInput.getText().trim();
            String lastName = lastNameInput.getText().trim();
            String eMail = eMailInput.getText().trim();

            Guest guest = new Guest(-1, firstName, lastName, eMail);
            guest = guestModel.createGuest(guest);
            ((Stage) (firstNameInput.getScene().getWindow())).close();
        }
    }

    public void btnCancel(ActionEvent actionEvent) {
        ((Stage) (firstNameInput.getScene().getWindow())).close();
    }

    public void setInfo(EventModel eventModel){
        this.eventModel = eventModel;
        cbTicketTypes.setItems(eventModel.getTicketTypes());
    }
}
