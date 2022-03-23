package be;

import java.util.ArrayList;

public class Admin {

    private String name;
    private int id;
    private ArrayList<User>listOfUsers;
    private ArrayList<TicketEvent>listOfEvents;

    public Admin(String name, int id, ArrayList<User> listOfUsers, ArrayList<TicketEvent> listOfEvents) {
        this.name = name;
        this.id = id;
        this.listOfUsers = listOfUsers;
        this.listOfEvents = listOfEvents;
    }
}
