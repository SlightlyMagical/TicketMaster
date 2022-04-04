package gui.models;

import bll.BLLManager;
import bll.IBLLManager;

public class GuestModel {

    private final IBLLManager bllManager;

    public GuestModel() {
        bllManager = new BLLManager();

    }

    public void createGuest(String firstName, String lastName, String eMail) {

        bllManager.createGuest(firstName, lastName, eMail);
    }
}
