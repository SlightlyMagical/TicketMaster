package be;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class Guest {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private ObservableList<Ticket> listOfTickets;

    public Guest(int id, String firstName, String lastName, String email){
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.id = id;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setListOfTickets(ObservableList<Ticket> listOfTickets) {
        this.listOfTickets = listOfTickets;
    }
    public ObservableList<Ticket> getListOfTickets() {
        return listOfTickets;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }
}
