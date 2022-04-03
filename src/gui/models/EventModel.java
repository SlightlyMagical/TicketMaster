package gui.models;

import be.TicketEvent;
import bll.BLLManager;
import bll.IBLLManager;

import java.util.ArrayList;
import java.util.List;

public class EventModel {
    private List<TicketEvent> eventList;

    private final IBLLManager bllManager;

    public EventModel() {
        bllManager = new BLLManager();

    }

    public TicketEvent createEvent(TicketEvent event){
        return bllManager.createEvent(event);
    }

    private void fetchEventList(){ // TODO: Add "user" parameter when login has been implemented
        eventList = bllManager.getEvents();
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

}
