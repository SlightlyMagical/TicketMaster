package gui.models;

import be.User;
import bll.BLLManager;
import bll.IBLLManager;

public class UserModel {

    private final IBLLManager bllManager;

    public UserModel() {
        bllManager = new BLLManager();

    }

    public User handleLogin(String username, String password) {

        return bllManager.handleLogin(username, password);
    }


}
