package be;

import java.time.LocalDate;

public class Ticket {

    private String name;
    private String location;
    private LocalDate startDate;
    private LocalDate endDate;
    private String description;
    private String locationGuide;
    private int ticketId;
    private String qrCode;
    private String barCode;
    private String type;
    private int eventId;

    public Ticket(String name, String location, LocalDate startDate, LocalDate endDate, String description, String locationGuide, int ticketId, String qrCode, String barCode, String type, int eventId) {
        this.name = name;
        this.location = location;
        this.startDate = startDate;
        this.endDate = endDate;
        this.description = description;
        this.locationGuide = locationGuide;
        this.ticketId = ticketId;
        this.qrCode = qrCode;
        this.barCode = barCode;
        this.type = type;
        this.eventId = eventId;
    }
}
