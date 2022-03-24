package gui.controllers;

import gui.SceneManager;
import javafx.event.ActionEvent;

import java.io.IOException;

public class EventDetailsController {
    private SceneManager sceneManager;

    public EventDetailsController() throws IOException {
        sceneManager = SceneManager.getInstance();
    }


    public void backButton(ActionEvent actionEvent) throws IOException {
        sceneManager.goBack();
    }
}
