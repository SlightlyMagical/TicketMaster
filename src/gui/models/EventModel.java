package gui.models;

import be.Ticket;
import be.TicketEvent;
import bll.BLLManager;
import bll.IBLLManager;
import javafx.collections.ObservableList;

import java.util.List;

public class EventModel {
    private List<TicketEvent> eventList;
    private ObservableList<String> ticketTypes;
    private ObservableList<Ticket> tickets;
    private TicketEvent currentEvent;

    private final IBLLManager bllManager;

    public EventModel() {
        bllManager = new BLLManager();
        getEventList();
    }

    /**
     * Adds the new event to the list of events and sends it to be created in the database
     */
    public void createEvent(TicketEvent event){
        event = bllManager.createEvent(event);
        eventList.add(event);
    }

    /**
     * Refreshes the list of events from the database and returns the updated list
     */
    public List<TicketEvent> getEventList(){
        this.eventList = bllManager.getEvents();
        return eventList;
    }

    /**
     * Send a new ticket type to be created
     */
    public void createTicketType(int eventID, String name){
        bllManager.createTicketType(eventID, name);
    }

    /**
     * Send a ticket type to be deleted
     */
    public void deleteTicketType(int eventID, String name){
        bllManager.deleteTicketType(eventID, name);
    }

    /**
     * Sets the current event and the tickets and ticket types associated with it
     */
    public void setCurrentEvent(TicketEvent ticketEvent){
        this.currentEvent = ticketEvent;
        ticketTypes = ticketEvent.getTicketTypes();
        tickets = ticketEvent.getListOfTickets();
    }

    /**
     * Sends a ticket to be deleted and removes it from the list of tickets
     */
    public void deleteTicket(Ticket ticket) {
        bllManager.deleteTicket(ticket);
        tickets.remove(ticket);
    }

    /**
     * Sends an event to be deleted and removes it from the list of events
     */
    public void deleteEvent(TicketEvent ticketEvent) {
        bllManager.deleteEvent(ticketEvent);
        eventList.remove(ticketEvent);
    }

    public ObservableList<String> getTicketTypes(){
        return ticketTypes;
    }

    public TicketEvent getCurrentEvent() {
        return currentEvent;
    }

    public ObservableList<Ticket> getTickets() {
        return tickets;
    }
}
