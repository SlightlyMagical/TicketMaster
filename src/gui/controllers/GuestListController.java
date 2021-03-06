package gui.controllers;

import be.Ticket;
import gui.SceneManager;
import gui.models.DialogHandler;
import gui.models.EventModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.DirectoryChooser;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class GuestListController implements Initializable {
    @FXML
    private TableView<Ticket> tvGuestList;
    @FXML
    private TableColumn<Ticket, String> tcFirstName;
    @FXML
    private TableColumn<Ticket, String> tcLastName;
    @FXML
    private TableColumn<Ticket, String> tcEmail;
    @FXML
    private TableColumn<Ticket, String> tcTicketType;

    private EventModel eventModel;
    private SceneManager sceneManager;

    public GuestListController() throws IOException {
    }

    /**
     * Initializes the table views
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            sceneManager = SceneManager.getInstance();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.eventModel = sceneManager.getEventModel();

        this.tcFirstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        this.tcLastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        this.tcEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        this.tcTicketType.setCellValueFactory(new PropertyValueFactory<>("type"));

        this.tvGuestList.setItems(eventModel.getTickets());
    }

    /**
     * Removes the selected guest from the guest list
     * Prompts the user for confirmation first
     */
    public void removeGuest(ActionEvent actionEvent) {
        Ticket ticket = tvGuestList.getSelectionModel().getSelectedItem();
        if(ticket == null)
            DialogHandler.informationAlert("Select a guest first");
        else if (DialogHandler.confirmationAlert("Are you sure you want to remove this guest? \nThis will delete their ticket")){
            eventModel.deleteTicket(ticket);
            eventModel.getCurrentEvent().getListOfTickets().remove(ticket);
        }
    }

    /**
     * Opens the window for adding new guests
     */
    public void addGuest(ActionEvent actionEvent) throws IOException {
        sceneManager.createGuest(eventModel);
    }

    /**
     * Exports all tickets for the current event. Prompts the user to choose destination directory
     */
    public void exportTickets(ActionEvent actionEvent) throws IOException {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Choose saving destination");
        File selectedDirectory = directoryChooser.showDialog(tvGuestList.getScene().getWindow());
        String path = selectedDirectory.getPath();

        for (Ticket ticket : eventModel.getCurrentEvent().getListOfTickets()){
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(sceneManager.getClass().getResource("views/Ticket.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            TicketController controller = fxmlLoader.getController();
            controller.setTicketInfo(ticket, path);
            controller.getTicketAsImage();
        }
        DialogHandler.informationAlert("Tickets for this event have been exported to the selected directory");
    }
}
