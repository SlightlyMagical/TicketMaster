package gui.controllers;

import gui.SceneManager;
import gui.models.EventModel;
import javafx.event.ActionEvent;

import java.io.IOException;

public class GuestListController {
    private EventModel eventModel;
    private final SceneManager sceneManager;

    public GuestListController() throws IOException {
        sceneManager = SceneManager.getInstance();

    }

    public void setEvent(EventModel eventModel) {
        this.eventModel = eventModel;
    }

    public void removeGuest(ActionEvent actionEvent) {
    }

    public void addGuest(ActionEvent actionEvent) throws IOException {
        sceneManager.createGuest(eventModel);

    }
}
