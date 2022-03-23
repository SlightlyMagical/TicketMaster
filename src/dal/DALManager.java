package dal;

import be.TicketEvent;
import dal.DAO.EventDAO;

import java.io.IOException;
import java.util.List;

public class DALManager implements IDALManager {

    private EventDAO eventDAO;

    public DALManager() {
        try {
            eventDAO = new EventDAO();
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
        return eventDAO.getEvents();
    }
}
