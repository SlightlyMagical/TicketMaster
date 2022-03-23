package be;

import java.util.ArrayList;

public class Customer {

    private String name;
    private int id;
    private ArrayList<Ticket>listOfTickets;
    private ArrayList<TicketEvent>listOfEvents;

    public Customer(String name, int id, ArrayList<Ticket> listOfTickets, ArrayList<TicketEvent> listOfEvents) {
        this.name = name;
        this.id = id;
        this.listOfTickets = listOfTickets;
        this.listOfEvents = listOfEvents;
    }
}
