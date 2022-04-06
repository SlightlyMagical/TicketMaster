package be;

public class Ticket {

    private final TicketEvent ticketEvent;
    private final String barCodeID;
    private final String type;
    private Guest owner;


    public Ticket(String barCodeID, String type, TicketEvent ticketEvent, Guest owner) {
        this.barCodeID = barCodeID;
        this.type = type;
        this.ticketEvent = ticketEvent;
        this.owner = owner;
    }

    public TicketEvent getTicketEvent() {
        return ticketEvent;
    }

    public String getBarCodeID() {
        return barCodeID;
    }

    public String getType() {
        return type;
    }

    public Guest getOwner() {
        return owner;
    }

    public String getFirstName(){
        return owner.getFirstName();
    }
    public String getLastName(){
        return owner.getLastName();
    }
    public String getEmail(){
        return owner.getEmail();
    }
}
