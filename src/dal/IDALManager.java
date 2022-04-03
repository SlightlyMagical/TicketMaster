package dal;

import be.TicketEvent;

import java.util.List;

public interface IDALManager {
    TicketEvent createEvent(TicketEvent event);

    List<TicketEvent> getEvents();

    void createTicketType(int eventID, String name);

    void deleteTicketType(int eventID, String name);
}
