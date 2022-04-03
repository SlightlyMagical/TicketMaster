package dal;

import be.TicketEvent;
import dal.DAO.EventDAO;
import dal.DAO.TicketDAO;

import java.io.IOException;
import java.util.List;

public class DALManager implements IDALManager {

    private EventDAO eventDAO;
    private TicketDAO ticketDAO;

    public DALManager() {
        try {
            eventDAO = new EventDAO();
            ticketDAO = new TicketDAO();

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
            e.setTicketTypes(ticketDAO.getEventTicketTypes(e.getId()));
        }
        return events;
    }

    @Override
    public void createTicketType(int eventID, String name) {
        ticketDAO.newTicketType(eventID, name);
    }

    @Override
    public void deleteTicketType(int eventID, String name) {
        ticketDAO.deleteTicketType(eventID, name);
    }
}
