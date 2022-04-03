package bll;

import be.TicketEvent;
import dal.DALManager;
import dal.IDALManager;

import java.util.List;

public class BLLManager implements IBLLManager {

    private IDALManager dalManager;

    public BLLManager() {
        dalManager = new DALManager();
    }

    @Override
    public TicketEvent createEvent(TicketEvent event) {
        return dalManager.createEvent(event);
    }

    @Override
    public List<TicketEvent> getEvents() {
        return dalManager.getEvents();
    }

    @Override
    public void createTicketType(int eventID, String name) {
        dalManager.createTicketType(eventID, name);
    }

    @Override
    public void deleteTicketType(int eventID, String name) {
        dalManager.deleteTicketType(eventID, name);
    }
}
