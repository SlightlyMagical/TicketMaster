package be;

import java.util.ArrayList;

public class EventCoordinator extends User {
    private ArrayList<TicketEvent>listOfEvents;
    private ArrayList<User>listOfUsers;

    public EventCoordinator(String name, int id, ArrayList<TicketEvent> listOfEvents, ArrayList<User> listOfUsers) {
        super(id, name);
        this.listOfEvents = listOfEvents;
        this.listOfUsers = listOfUsers;
    }
}
