package gui.models;

import be.Guest;
import be.User;
import bll.BLLManager;
import bll.IBLLManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableSet;

public class UserModel {

    private ObservableList<User> admins;
    private ObservableList<User> coordinators;
    private ObservableList<Guest> guests;
    private final IBLLManager bllManager;

    public UserModel() {
        bllManager = new BLLManager();

    }

    public User handleLogin(String username, String password) {

        return bllManager.handleLogin(username, password);
    }

    public ObservableList<User> getAdmins() {
        return admins;
    }

    public ObservableList<User> getCoordinators() {
        return coordinators;
    }

    public ObservableList<Guest> getGuests() {
        return guests;
    }

    public void fetchAllUsers() {

        admins = FXCollections.observableArrayList(bllManager.retrieveAdmins());

        coordinators = FXCollections.observableArrayList(bllManager.retrieveCoordinators());

        guests = FXCollections.observableArrayList(bllManager.retrieveGuests());
    }

}
