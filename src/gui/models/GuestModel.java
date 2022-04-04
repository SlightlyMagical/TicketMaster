package gui.models;

import be.Guest;
import bll.BLLManager;
import bll.IBLLManager;

public class GuestModel {

    private final IBLLManager bllManager;

    public GuestModel() {
        bllManager = new BLLManager();

    }

    public void createGuest(Guest guest) {
        bllManager.createGuest(guest);
    }
}
