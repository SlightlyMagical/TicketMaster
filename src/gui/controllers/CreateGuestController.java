package gui.controllers;

import be.User;
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
        String firstName = firstNameInput.getText();
        String lastName = lastNameInput.getText();
        String eMail = eMailInput.getText();

        guestModel.createGuest(firstName, lastName, eMail);

        ((Stage) (firstNameInput.getScene().getWindow())).close();
    }

    public void btnCancel(ActionEvent actionEvent) {
        ((Stage) (firstNameInput.getScene().getWindow())).close();
    }

    public void setInfo(EventModel eventModel){
        this.eventModel = eventModel;
        cbTicketTypes.setItems(eventModel.getTicketTypes());
    }
}
