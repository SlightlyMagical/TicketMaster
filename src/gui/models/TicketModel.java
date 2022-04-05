package gui.models;

import be.Ticket;
import bll.BLLManager;
import bll.IBLLManager;

public class TicketModel {
    private final IBLLManager bllManager;

    public TicketModel() {
        bllManager = new BLLManager();
    }

    public boolean newTicket(Ticket ticket){
        return bllManager.newTicket(ticket);
    }
}
