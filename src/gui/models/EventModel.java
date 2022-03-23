package gui.models;

import be.TicketEvent;
import bll.BLLManager;
import bll.IBLLManager;

public class EventModel {

    private IBLLManager bllManager;

    public EventModel() {
        bllManager = new BLLManager();
    }

    public TicketEvent createEvent(TicketEvent event){
        return bllManager.createEvent(event);
    }

}
