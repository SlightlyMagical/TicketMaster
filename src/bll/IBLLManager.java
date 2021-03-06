package bll;

import be.Guest;
import be.Ticket;
import be.TicketEvent;
import be.User;

import java.util.List;

public interface IBLLManager {
    TicketEvent createEvent(TicketEvent event);

    List<TicketEvent> getEvents();

    void createTicketType(int eventID, String name);

    void deleteTicketType(int eventID, String name);


    Guest createGuest(Guest guest);

    /**
     * Returns true if a new ticket was created in the database
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

    void deleteEvent(TicketEvent ticketEvent);
}
