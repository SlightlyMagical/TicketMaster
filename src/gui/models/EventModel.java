package gui.models;

import be.Ticket;
import be.TicketEvent;
import bll.BLLManager;
import bll.IBLLManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

public class EventModel {
    private List<TicketEvent> eventList;
    private ObservableList<String> ticketTypes;
    private ObservableList<Ticket> tickets;
    private TicketEvent currentEvent;

    private final IBLLManager bllManager;

    public EventModel() {
        bllManager = new BLLManager();
        fetchEventList();
    }

    public TicketEvent createEvent(TicketEvent event){
        eventList.add(event);
        return bllManager.createEvent(event);
    }

    private void fetchEventList(){ // TODO: Add "user" parameter when login has been implemented
        this.eventList = bllManager.getEvents();
    } // TODO: check when this is being called

    public List<TicketEvent> getEventList(){
        fetchEventList();
        return eventList;
    }

    public void createTicketType(int eventID, String name){
        bllManager.createTicketType(eventID, name);
    }

    public void deleteTicketType(int eventID, String name){
        bllManager.deleteTicketType(eventID, name);
    }

    public void setCurrentEvent(TicketEvent ticketEvent){
        this.currentEvent = ticketEvent;
        ticketTypes = ticketEvent.getTicketTypes();
        tickets = ticketEvent.getListOfTickets();

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

    public void deleteTicket(Ticket ticket) {
        bllManager.deleteTicket(ticket);
        tickets.remove(ticket);
    }

    public void deleteEvent(TicketEvent ticketEvent) {
        bllManager.deleteEvent(ticketEvent);
        eventList.remove(ticketEvent);
    }
}
