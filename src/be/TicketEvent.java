package be;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;

public class TicketEvent
{
    private int id;
    private String name;
    private String location;
    private LocalDate startDate;
    private LocalDate endDate;
    private String description;
    private String locationGuide;
    private LocalTime startTime;
    private LocalTime endTime;
    private ObservableList<String> ticketTypes;
    private ArrayList<Ticket> listOfTickets;

    public TicketEvent(int id, String name, String location, LocalDate startDate, String description, LocalTime startTime) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.startDate = startDate;
        this.description = description;
        this.startTime = startTime;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }

    public LocalDate getStartDate() {
        return startDate;
    }
    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }
    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocationGuide() {
        return locationGuide;
    }
    public void setLocationGuide(String locationGuide) {
        this.locationGuide = locationGuide;
    }

    public LocalTime getStartTime() {
        return startTime;
    }
    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }
    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public ObservableList<String> getTicketTypes() {
        return ticketTypes;
    }

    public void setTicketTypes(ArrayList<String> ticketTypes) {
        this.ticketTypes = FXCollections.observableArrayList("Standard");
        this.getTicketTypes().addAll(ticketTypes);
    }




    public String getStartTimeAsString(){
        return startTime.format(DateTimeFormatter.ofPattern("HH:mm"));
    }

    public String getStartDateAsString(){
        return startDate.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT));
    }
    public String getEndTimeAsString(){
        return endTime.format(DateTimeFormatter.ofPattern("HH:mm"));
    }

    public String getEndDateAsString(){
        return endDate.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT));
    }


}
