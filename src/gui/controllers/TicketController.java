package gui.controllers;

import be.Ticket;
import be.TicketEvent;
import bll.util.BarcodeMaker;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class TicketController {
    @FXML
    private Label guestName;
    @FXML
    private Label locationText;
    @FXML
    private ImageView qrcode;
    @FXML
    private ImageView barcode;
    @FXML
    private Label name;
    @FXML
    private Label ticketType;
    @FXML
    private Label startDate;
    @FXML
    private Label startTime;
    @FXML
    private HBox endDetailsBox;
    @FXML
    private Label endDate;
    @FXML
    private Label endTime;
    @FXML
    private Label description;
    @FXML
    private VBox locationGuideBox;
    @FXML
    private Label locationGuide;

    private Ticket ticket;
    private String saveLocation;

    /**
     * Fills in the correct information of the ticket
     */
    public void setTicketInfo(Ticket ticket, String saveLocation) {
        this.saveLocation = saveLocation;
        this.ticket = ticket;
        TicketEvent ticketEvent = ticket.getTicketEvent();
        name.setText(ticketEvent.getName());
        locationText.setText(ticketEvent.getLocation());
        startDate.setText(ticketEvent.getStartDateAsString());
        startTime.setText(ticketEvent.getStartTimeAsString());
        description.setText(ticketEvent.getDescription());
        ticketType.setText(ticket.getType());
        guestName.setText(ticket.getOwner().getFullName());

        if (ticketEvent.getEndDate() == null && ticketEvent.getEndTime() == null)
            endDetailsBox.setVisible(false);
        else {
            if (ticketEvent.getEndDate() != null)
                endDate.setText(ticketEvent.getEndDateAsString());
            if (ticketEvent.getEndTime() != null)
                endTime.setText(ticketEvent.getEndTimeAsString());
        }

        if (ticketEvent.getLocationGuide() == null)
            locationGuideBox.setVisible(false);
        else
            locationGuide.setText(ticketEvent.getLocationGuide());

        barcode.setImage(BarcodeMaker.generateBarcodeImage(ticket.getBarCodeID()));

        qrcode.setImage(BarcodeMaker.generateQrCodeImage(ticket.getBarCodeID()));

    }

    /**
     * Takes a snapshot of the ticket and saves it as a .png image.
     * Returns the file of the image
     */
    public File getTicketAsImage() throws IOException {
        WritableImage writableImage = name.getScene().snapshot(null);

        File image = new File(saveLocation + "/" + ticket.getEmail() + ".png");
        ImageIO.write(SwingFXUtils.fromFXImage(writableImage, null), "png",
                image);
        return image;
    }
}
