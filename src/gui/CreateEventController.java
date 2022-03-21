package gui;

import be.TicketEvent;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

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

    public void cancelAction(ActionEvent actionEvent) {
    }

    public void confirmAction(ActionEvent actionEvent) {
    }
}
