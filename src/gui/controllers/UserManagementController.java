package gui.controllers;

import be.Guest;
import be.User;
import gui.SceneManager;
import gui.models.DialogHandler;
import gui.models.GuestModel;
import gui.models.UserModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class UserManagementController implements Initializable {

    @FXML
    private TableView<User> tvAdmin;
    @FXML
    private TableColumn<User, String> tcAdminUsername;
    @FXML
    private TableView<User> tvCoordinator;
    @FXML
    private TableColumn<User, String> tcCoordinatorUsername;
    @FXML
    private TableView<Guest> tvGuest;
    @FXML
    private TableColumn<Guest, String> tcGuestFirstName;
    @FXML
    private TableColumn<Guest, String> tcGuestLastName;
    @FXML
    private TableColumn<Guest, String> tcGuestEmail;

    private UserModel userModel;
    private SceneManager sceneManager;
   

    public UserManagementController() throws IOException {
        sceneManager = SceneManager.getInstance();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        userModel = new UserModel();
        userModel.fetchAllUsers();

        this.tcAdminUsername.setCellValueFactory(new PropertyValueFactory<>("name"));
        this.tvAdmin.setItems(userModel.getAdmins());

        this.tcCoordinatorUsername.setCellValueFactory(new PropertyValueFactory<>("name"));
        this.tvCoordinator.setItems(userModel.getCoordinators());

        this.tcGuestFirstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        this.tcGuestLastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        this.tcGuestEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        this.tvGuest.setItems(userModel.getGuests());

    }

    public void handleDeleteAdmin(ActionEvent actionEvent) {
        userModel.deleteUser(tvAdmin.getSelectionModel().getSelectedItem(), "Admin");
    }

    public void handleCreateAdmin(ActionEvent actionEvent) throws IOException {
        sceneManager.showCreateUser("Admin", userModel);
    }

    public void handleDeleteCoordinator(ActionEvent actionEvent) {
        if(DialogHandler.confirmationAlert("Are you sure you want to delete this user?"))
            userModel.deleteUser(tvCoordinator.getSelectionModel().getSelectedItem(), "Coordinator");
    }

    public void handleCreateNewCoordinator(ActionEvent actionEvent) throws IOException {
        sceneManager.showCreateUser("Coordinator", userModel);
    }

    public void handleDeleteGuest(ActionEvent actionEvent) {
        if(DialogHandler.confirmationAlert("Are you sure you want to delete this user?"))
            userModel.deleteGuest(tvGuest.getSelectionModel().getSelectedItem());
    }

}
