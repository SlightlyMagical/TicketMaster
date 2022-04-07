package dal;

import be.Guest;
import be.Ticket;
import be.TicketEvent;
import be.User;

import java.util.List;

public interface IDALManager {
    TicketEvent createEvent(TicketEvent event);

    List<TicketEvent> getEvents();

    void createTicketType(int eventID, String name);

    void deleteTicketType(int eventID, String name);

    Guest createGuest(Guest guest);

    /**
     * Checks if the ticket already exists returns false if it does.
     * Else creates a new ticket and returns true
     */
    boolean newTicket(Ticket ticket);

    User handleLogin(String username, String password);

    void deleteTicket(Ticket ticket);

    List<User> retrieveAdmins();
    List<User> retrieveCoordinators();
    List<Guest> retrieveGuests();

    int createUser(String username, String password,String usertype);

    void deleteGuest(Guest guest);

    void deleteUser(User user);
}
