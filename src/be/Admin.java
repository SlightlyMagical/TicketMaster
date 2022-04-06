package be;

import java.util.ArrayList;

public class Admin extends User {

    private ArrayList<User>listOfUsers;
    private ArrayList<TicketEvent>listOfEvents;

    public Admin(String name, int id, ArrayList<User> listOfUsers, ArrayList<TicketEvent> listOfEvents) {
        super (id, name);
        this.listOfUsers = listOfUsers;
        this.listOfEvents = listOfEvents;
    }
}
