package gui.controllers;

import be.Ticket;
import be.TicketEvent;
import gui.models.EventModel;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

public class CreateEventController {

    @FXML
    private Label lblTitle;
    @FXML
    private TextField txtEventName;
    @FXML
    private TextField txtLocation;
    @FXML
    private DatePicker dpStartDate;
    @FXML
    private TextField txtStartTime;
    @FXML
    private DatePicker dpEndDate;
    @FXML
    private TextField txtEndTime;
    @FXML
    private TextArea txtDescription;
    @FXML
    private TextArea txtLocalGuidance;
    @FXML
    private Button btnConfirm;

    private EventModel eventModel;

    public CreateEventController() {
        eventModel = new EventModel();
    }

    public void cancelAction(ActionEvent actionEvent) {
    }

    public void confirmAction(ActionEvent actionEvent) {
        String name = txtEventName.getText();
        String location = txtLocation.getText();
        LocalDate startDate = dpStartDate.getValue();
        String description = txtDescription.getText();
        String locationGuide = txtLocalGuidance.getText();
        String[] stringList= txtStartTime.getText().split(":");
        LocalTime startTime = LocalTime.of(Integer.parseInt(stringList[0]),Integer.parseInt(stringList[1]),0);



        String[] stringList1= txtStartTime.getText().split(":");
        LocalTime endTime = LocalTime.of(Integer.parseInt(stringList1[0]),Integer.parseInt(stringList1[1]),0);


        LocalDate endDate = dpStartDate.getValue();
        TicketEvent ticketEvent = new TicketEvent(-1, name, location, startDate, description,startTime);
        ticketEvent.setTicketEventLocationGuide(locationGuide);
        ticketEvent.setTicketEventEndDate(endDate);
        ticketEvent.setTicketEventEndTime(endTime);
        //send ned til DB - find ud af hvordan den lukker vinduet
        eventModel.createEvent(ticketEvent);

    }
}

