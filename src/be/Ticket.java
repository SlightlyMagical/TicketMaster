package be;

public class Ticket {

    private final TicketEvent ticketEvent;
    private final String barCodeID;
    private final String type;
    private int ticketId;


    public Ticket(int ticketId, String barCodeID, String type, TicketEvent ticketEvent) {
        this.ticketId = ticketId;
        this.barCodeID = barCodeID;
        this.type = type;
        this.ticketEvent = ticketEvent;
    }

    public TicketEvent getTicketEvent() {
        return ticketEvent;
    }

    public int getTicketId() {
        return ticketId;
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }

    public String getBarCodeID() {
        return barCodeID;
    }

    public String getType() {
        return type;
    }
}
