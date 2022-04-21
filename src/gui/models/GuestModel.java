package gui.models;

import be.Guest;
import bll.BLLManager;
import bll.IBLLManager;

public class GuestModel {

    private final IBLLManager bllManager;

    public GuestModel() {
        bllManager = new BLLManager();
    }

    /**
     * Sends a new guest to be created
     */
    public Guest createGuest(Guest guest) {
        return bllManager.createGuest(guest);
    }

}
