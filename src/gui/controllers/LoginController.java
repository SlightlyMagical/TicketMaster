package gui.controllers;

import be.Admin;
import be.EventCoordinator;
import be.User;
import gui.SceneManager;
import gui.models.DialogHandler;
import gui.models.UserModel;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

public class LoginController {

    private UserModel userModel;
    private SceneManager sceneManager;
    public Label Username;
    public TextField txtUserName;
    public Label Password;
    public PasswordField txtPassword;
    public Button btnLogin;

    public LoginController() throws IOException {

        userModel = new UserModel();
        sceneManager = SceneManager.getInstance();
    }

    public void handleLogin(ActionEvent actionEvent) throws IOException {

        String username = txtUserName.getText().trim();
        String password = txtPassword.getText().trim();

        User user = userModel.handleLogin(username, password);
        if (user != null && user.getClass() == Admin.class) {

            sceneManager.showAdminScreen();

        } else if (user != null && user.getClass() == EventCoordinator.class) {
            sceneManager.showEventList();

        } else {
            DialogHandler.informationAlert("Wrong login credentials");
        }
    }
    

}