package bll;

import be.TicketEvent;
import dal.DALManager;
import dal.IDALManager;

public class BLLManager implements IBLLManager {

    private IDALManager dalManager;

    public BLLManager() {
        dalManager = new DALManager();
    }

    @Override
    public TicketEvent createEvent(TicketEvent event) {
        return dalManager.createEvent(event);
    }
}
