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
    }

    public List<TicketEvent> getEventList(){
        fetchEventList();
        return eventList;
    }

}
