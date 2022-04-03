package gui.controllers;

import be.EventCoordinator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.ResourceBundle;

public class ManageCoordinatorsController implements Initializable {
    @FXML
    private TableView<EventCoordinator> tvAssigned;
    @FXML
    private TableColumn<EventCoordinator, String> tcAssigned;
    @FXML
    private TableView<EventCoordinator> tvAvailable;
    @FXML
    private TableColumn<EventCoordinator, String> tcAvailable;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void removeCoordinator(ActionEvent actionEvent) {
    }

    public void addCoordinator(ActionEvent actionEvent) {
    }

    public void cancel(ActionEvent actionEvent) {
    }

    public void saveChanges(ActionEvent actionEvent) {
    }


}
