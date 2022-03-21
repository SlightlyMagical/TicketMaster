package be;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDateTime;

public class TicketEvent
{
    private int id;
    private String ticketEventName;
    private String ticketEventLocation;
    private LocalDateTime ticketEventStartDate;
    private LocalDateTime ticketEventEndDate;
    private String ticketEventDescription;
    private String ticketEventLocationGuide;

    public TicketEvent(String name, String location, LocalDateTime startDate,String description, String locationGuide)
    {
        this.ticketEventName = name;
        this.ticketEventLocation = location;
        this.ticketEventStartDate = startDate;
        this.ticketEventDescription = description;
        this.ticketEventLocationGuide = locationGuide;

        id = 0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String getTicketEventName() {
        return ticketEventName;
    }

    public void setTicketEventName(String ticketEventName) {
        this.ticketEventName = ticketEventName;
    }

    public String getTicketEventLocation() {
        return ticketEventLocation;
    }

    public void setTicketEventLocation(String ticketEventLocation) {
        this.ticketEventLocation = ticketEventLocation;
    }

    public LocalDateTime getTicketEventStartDate() {
        return ticketEventStartDate;
    }

    public void setTicketEventStartDate(LocalDateTime ticketEventStartDate) {
        this.ticketEventStartDate = ticketEventStartDate;
    }

    public LocalDateTime getTicketEventEndDate() {
        return ticketEventEndDate;
    }

    public void setTicketEventEndDate(LocalDateTime ticketEventEndDate) {
        this.ticketEventEndDate = ticketEventEndDate;
    }

    public String getTicketEventDescription() {
        return ticketEventDescription;
    }

    public void setTicketEventDescription(String ticketEventDescription) {
        this.ticketEventDescription = ticketEventDescription;
    }

    public String getTicketEventLocationGuide() {
        return ticketEventLocationGuide;
    }

    public void setTicketEventLocationGuide(String ticketEventLocationGuide) {
        this.ticketEventLocationGuide = ticketEventLocationGuide;

    }
}
