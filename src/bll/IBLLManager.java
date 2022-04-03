package bll;

import be.TicketEvent;

import java.util.List;

public interface IBLLManager {
    TicketEvent createEvent(TicketEvent event);

    List<TicketEvent> getEvents();

    void createTicketType(int eventID, String name);

    void deleteTicketType(int eventID, String name);
}
