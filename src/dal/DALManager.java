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
        ticketTypeDAO.getEventTicketTypes(events);
        ticketDAO.getTicketsOfEvents(events);
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

    /**
     * First checks if the given guests already exists in the database, then creates a new one if it doesn't
     * @return the given guest with a correct ID
     */
    @Override
    public Guest createGuest(Guest guest) {
        Guest checkGuest = guestDAO.checkIfGuestExists(guest);
        if (checkGuest.getId() != -1)
            return checkGuest;
        else
            return guestDAO.createGuest(guest);
    }

    /**
     * First checks if a ticket for this user already exists in the database, and creates it if not
     * @return true if a new ticket was created
     */
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

    @Override
    public void deleteTicket(Ticket ticket) {
        ticketDAO.deleteTicket(ticket);
    }

    @Override
    public List<User> retrieveAdmins() {
        return userDAO.getAdmins();
    }

    @Override
    public List<User> retrieveCoordinators() {
        return userDAO.getCoordinators();
    }

    @Override
    public List<Guest> retrieveGuests() {
        return guestDAO.getGuests();
    }

    /**
     * First checks if a user with the given username already exists in the database,
     * and creates a new one if it does not
     * @return the new ID of the user if successful, or -1 if failed
     */
    @Override
    public int createUser(String username, String password, String usertype) {
        if(!userDAO.checkIfUsernameTaken(username))
            return userDAO.createUser(username, password, usertype);
        else
            return -1;
    }

    @Override
    public void deleteGuest(Guest guest) {
        guestDAO.deleteGuest(guest);
    }

    @Override
    public void deleteUser(User user) {
        userDAO.deleteUser(user);
    }

    @Override
    public void deleteEvent(TicketEvent ticketEvent) {
        eventDAO.deleteEvent(ticketEvent);
    }


}
