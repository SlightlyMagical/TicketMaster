package gui.controllers;

import be.TicketEvent;
import gui.models.EventModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.time.LocalTime;

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

    private final EventModel eventModel;

    public CreateEventController() {
        eventModel = new EventModel();
    }

    public void cancelAction(ActionEvent actionEvent) {
        ((Stage) (lblTitle.getScene().getWindow())).close();
    }

    public void confirmAction(ActionEvent actionEvent) { // TODO: 04-04-2022 implement input checks
        String name = txtEventName.getText();
        String location = txtLocation.getText();
        LocalDate startDate = dpStartDate.getValue();
        String description = txtDescription.getText();
        String locationGuide = txtLocalGuidance.getText();
        String[] stringList= txtStartTime.getText().split(":");
        LocalTime startTime = LocalTime.of(Integer.parseInt(stringList[0]),Integer.parseInt(stringList[1]),0);
        TicketEvent ticketEvent = new TicketEvent(-1, name, location, startDate, description,startTime);
        ticketEvent.setLocationGuide(locationGuide);
        try {
            String[] stringList1= txtEndTime.getText().split(":");
            LocalTime endTime = LocalTime.of(Integer.parseInt(stringList1[0]),Integer.parseInt(stringList1[1]),0);
            ticketEvent.setEndTime(endTime);
        } catch (Exception ignored) {
        }
        try {
            LocalDate endDate = dpEndDate.getValue();
            ticketEvent.setEndDate(endDate);
        } catch (Exception ignored) {
        }

        eventModel.createEvent(ticketEvent);

        ((Stage) (lblTitle.getScene().getWindow())).close();
    }
}


