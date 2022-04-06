package bll;

import be.Guest;
import be.Ticket;
import be.TicketEvent;
import be.User;
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

    @Override
    public Guest createGuest(Guest guest) {
        return dalManager.createGuest(guest);
    }

    @Override
    public boolean newTicket(Ticket ticket) {
        return dalManager.newTicket(ticket);
    }

    @Override
    public User handleLogin(String username, String password)
    {
        return dalManager.handleLogin(username, password);
    }

}
