package gui.models;

import be.Guest;
import be.User;
import bll.BLLManager;
import bll.IBLLManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class UserModel {

    private ObservableList<User> admins;
    private ObservableList<User> coordinators;
    private ObservableList<Guest> guests;
    private final IBLLManager bllManager;

    public UserModel() {
        bllManager = new BLLManager();
        admins = FXCollections.observableArrayList();
        coordinators = FXCollections.observableArrayList();
        guests = FXCollections.observableArrayList();
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

    public void deleteUser(User user, String userType){
        if ((userType.equals("Admin") && admins.size() <= 1) || (userType.equals("Coordinator") && coordinators.size() <= 1)) {
            DialogHandler.informationAlert("You cannot delete the last user of this type");
        }
        else {
            bllManager.deleteUser(user);
            if (userType.equals("Admin"))
                admins.remove(user);
            else if (userType.equals("Coordinator"))
                coordinators.remove(user);
        }
    }

    public boolean createUser(String username, String password, String userType){
        int id = bllManager.createUser(username, password, userType);
        if (id == -1)
            return false;
        else {
            if (userType.equals("Coordinator"))
                coordinators.add(new User(id, username));
            else if (userType.equals("Admin"))
                admins.add(new User(id, username));
            return true;
        }
    }

    public void deleteGuest(Guest guest) {
        bllManager.deleteGuest(guest);
        guests.remove(guest);
    }

}
