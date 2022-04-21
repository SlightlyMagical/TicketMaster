package gui.controllers;

import be.TicketEvent;
import bll.util.InputCheck;
import gui.models.DialogHandler;
import gui.models.EventModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

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

    private final EventModel eventModel;

    public CreateEventController() {
        eventModel = new EventModel();
    }

    /**
     * Closes the current stage
     */
    public void cancelAction(ActionEvent actionEvent) {
        ((Stage) (lblTitle.getScene().getWindow())).close();
    }


    /**
     * Attempts to create a new event with the provided inputs from the user.
     * Verifies the inputs and only allows creation if all are correct,
     * otherwise displays the relevant error messages to the user
     */
    public void confirmAction(ActionEvent actionEvent) {
        List<String> errorMessages = new ArrayList<>();

        String name = txtEventName.getText().trim();
        String location = txtLocation.getText().trim();
        LocalDate startDate = dpStartDate.getValue();
        String description = txtDescription.getText().trim();
        String locationGuide = txtLocalGuidance.getText().trim();

        LocalTime startTime = InputCheck.timeCheck(txtStartTime.getText());
        if (startTime == null)
            errorMessages.add("Invalid time input\nTime must be in HH:MM format");

        TicketEvent ticketEvent = new TicketEvent(-1, name, location, startDate, description,startTime);
        ticketEvent.setLocationGuide(locationGuide);
        try {
            String endTimeText = txtEndTime.getText().trim();
            if (!endTimeText.isEmpty()) {
                LocalTime endTime = InputCheck.timeCheck(endTimeText);
                if (endTime != null)
                    ticketEvent.setEndTime(endTime);
                else
                    errorMessages.add("Invalid time input\nTime must be in HH:MM format");
            }
        } catch (Exception ignored) {
        }
        try {
            LocalDate endDate = dpEndDate.getValue();
            ticketEvent.setEndDate(endDate);
        } catch (Exception ignored) {
        }

        if (name.isEmpty() || description.isEmpty() || location.isEmpty() || startDate == null)
            errorMessages.add("Please fill out the required fields");

        if (errorMessages.isEmpty()){
            eventModel.createEvent(ticketEvent);

            ((Stage) (lblTitle.getScene().getWindow())).close();
        }
        else {
            for (String string : errorMessages){
                DialogHandler.informationAlert(string);
            }
        }
    }
}


