package gui.controllers;

import be.User;
import gui.models.EventModel;
import gui.models.GuestModel;
import gui.models.UserModel;
import javafx.event.ActionEvent;
import javafx.scene.control.TextField;

public class CreateGuestController {

    private final GuestModel guestModel;
    public TextField firstNameInput;
    public TextField lastNameInput;
    public TextField eMailInput;

    public CreateGuestController() {

        guestModel = new GuestModel();
    }

    public void btnCreateNewGuest(ActionEvent actionEvent) {

        String firstName = firstNameInput.getText();
        String lastName = lastNameInput.getText();
        String eMail = eMailInput.getText();

        guestModel.createGuest(firstName, lastName, eMail);
    }
}
