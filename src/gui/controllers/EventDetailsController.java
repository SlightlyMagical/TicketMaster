package gui.controllers;

import be.TicketEvent;
import gui.SceneManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class EventDetailsController implements Initializable {
    @FXML
    private Label lblEventName;
    @FXML
    private Label lblLocation;
    @FXML
    private Label lblStartDate;
    @FXML
    private Label lblEndDate;
    @FXML
    private Label lblStartTime;
    @FXML
    private Label lblEndTime;
    @FXML
    private Label lblDescription;
    @FXML
    private Label lblLocationGuide;

    private final SceneManager sceneManager;

    public EventDetailsController() throws IOException {
        sceneManager = SceneManager.getInstance();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public void backButton(ActionEvent actionEvent) throws IOException {
        sceneManager.goBack();
    }

    public void setInfo(TicketEvent event) {
        lblEventName.setText(event.getName());
        lblStartDate.setText(event.getStartDateAsString());
        lblStartTime.setText(event.getStartTimeAsString());
        lblDescription.setText(event.getDescription());
        lblLocation.setText(event.getLocation());
        try{
            lblEndDate.setText(event.getEndDateAsString());
        } catch (Exception ignored) {
        }
        try{
            lblEndTime.setText(event.getEndTimeAsString());
        } catch (Exception ignored) {
        }
        try{
            lblLocationGuide.setText(event.getLocationGuide());
        } catch (Exception ignored) {
        }
    }


}
