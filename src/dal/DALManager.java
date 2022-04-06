package dal;

import be.Guest;
import be.Ticket;
import be.TicketEvent;
import be.User;
import dal.DAO.EventDAO;
import dal.DAO.GuestDAO;
import dal.DAO.TicketDAO;

import dal.DAO.TicketTypeDAO;

import dal.DAO.UserDAO;

import java.io.IOException;
import java.util.List;

public class DALManager implements IDALManager {

    private EventDAO eventDAO;
    private GuestDAO guestDAO;

    private TicketDAO ticketDAO;
    private TicketTypeDAO ticketTypeDAO;

    private UserDAO userDAO;


    public DALManager() {
        try {
            eventDAO = new EventDAO();
            guestDAO = new GuestDAO();

            ticketDAO = new TicketDAO();
            ticketTypeDAO = new TicketTypeDAO();

            userDAO = new UserDAO();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public TicketEvent createEvent(TicketEvent event) {
        return eventDAO.createTicketEvent(event);
    }

    @Override
    public List<TicketEvent> getEvents() {
        List<TicketEvent> events = eventDAO.getEvents();
        for (TicketEvent e : events){
            e.setTicketTypes(ticketTypeDAO.getEventTicketTypes(e.getId()));
        }
        return events;
    }

    @Override
    public void createTicketType(int eventID, String name) {
        ticketTypeDAO.newTicketType(eventID, name);
    }

    @Override
    public void deleteTicketType(int eventID, String name) {
        ticketTypeDAO.deleteTicketType(eventID, name);
    }

    @Override
    public Guest createGuest(Guest guest) {
        Guest checkGuest = guestDAO.checkIfGuestExists(guest);
        if (checkGuest.getId() != -1)
            return checkGuest;
        else
            return guestDAO.createGuest(guest);
    }

    @Override
    public boolean newTicket(Ticket ticket) {
        if (ticketDAO.checkHasTicket(ticket))
            return false;
        else{
            ticketDAO.newTicket(ticket);
            return true;
        }
    }

    @Override
    public User handleLogin(String username, String password) {

        return userDAO.handleLogin(username, password);
    }
}
