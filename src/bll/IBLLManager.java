package bll;

import be.Guest;
import be.TicketEvent;

import java.util.List;

public interface IBLLManager {
    TicketEvent createEvent(TicketEvent event);

    List<TicketEvent> getEvents();

    void createTicketType(int eventID, String name);

    void deleteTicketType(int eventID, String name);

    Guest createGuest(Guest guest);
}
