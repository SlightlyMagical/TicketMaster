package gui.controllers;

import be.User;
import gui.models.DialogHandler;
import gui.models.UserModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CreateUserController {
    @FXML
    private Label userType;
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private PasswordField passwordConfirm;

    private UserModel userModel;

    public CreateUserController() {
    }

    public void cancel(ActionEvent actionEvent) {
        ((Stage) (username.getScene().getWindow())).close();
    }

    public void confirm(ActionEvent actionEvent) {
        String un = username.getText().trim().toLowerCase();
        String pw = password.getText().trim();
        String pwc = passwordConfirm.getText().trim();

        if (un.equals("") || pw.equals("") || pwc.equals("")){
            DialogHandler.informationAlert("Fields cannot be empty!");
        }
        else if (!pw.equals(pwc)){
            DialogHandler.informationAlert("Passwords do not match");
        }
        else {
            if (userModel.createUser(un, pw, userType.getText()))
                ((Stage) (username.getScene().getWindow())).close();
            else
                DialogHandler.informationAlert("This username is already taken, please choose another");
        }
    }

    public void setInfo(String string, UserModel userModel){
        userType.setText(string);
        this.userModel = userModel;
    }
}
