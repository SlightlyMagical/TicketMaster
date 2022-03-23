package be;

import java.util.ArrayList;

public class EventCoordinator {
    private String name;
    private int id;
    private ArrayList<TicketEvent>listOfEvents;
    private ArrayList<User>listOfUsers;

    public EventCoordinator(String name, int id, ArrayList<TicketEvent> listOfEvents, ArrayList<User> listOfUsers) {
        this.name = name;
        this.id = id;
        this.listOfEvents = listOfEvents;
        this.listOfUsers = listOfUsers;
    }
}
