package bll;

import be.Guest;
import be.Ticket;
import be.TicketEvent;
import dal.DAO.TicketDAO;

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
}
