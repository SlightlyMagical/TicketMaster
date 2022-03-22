package be;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class TicketEvent
{
    private int id;
    private String ticketEventName;
    private String ticketEventLocation;
    private LocalDate ticketEventStartDate;
    private LocalDate ticketEventEndDate;
    private String ticketEventDescription;
    private String ticketEventLocationGuide;
    private LocalTime ticketEventStartTime;
    private LocalTime ticketEventEndTime;

    public TicketEvent(String name, String location, LocalDate startDate, String description, String locationGuide)
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

    public LocalDate getTicketEventStartDate() {
        return ticketEventStartDate;
    }

    public void setTicketEventStartDate(LocalDate ticketEventStartDate) {
        this.ticketEventStartDate = ticketEventStartDate;
    }

    public LocalDate getTicketEventEndDate() {
        return ticketEventEndDate;
    }

    public void setTicketEventEndDate(LocalDate ticketEventEndDate) {
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

    public LocalTime getTicketEventStartTime() {
        return ticketEventStartTime;
    }

    public void setTicketEventStartTime(LocalTime ticketEventStartTime) {
        this.ticketEventStartTime = ticketEventStartTime;
    }

    public LocalTime getTicketEventEndTime() {
        return ticketEventEndTime;
    }

    public void setTicketEventEndTime(LocalTime ticketEventEndTime) {
        this.ticketEventEndTime = ticketEventEndTime;
    }

    public String getStartTimeAsString(){
        return ticketEventStartTime.format(DateTimeFormatter.ofPattern("HH:mm"));
    }

    public String getStartDateAsString(){
        return ticketEventStartDate.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT));
    }
}
