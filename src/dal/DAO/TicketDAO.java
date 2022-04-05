package dal.DAO;

import be.Guest;
import be.Ticket;
import dal.DBConnector;

import java.io.IOException;
import java.sql.*;

public class TicketDAO {
    private final DBConnector DC = new DBConnector();

    public TicketDAO() throws IOException {
    }

    public void newTicket(Ticket ticket){
        try (Connection connection = DC.getConnection()){
            String sql = "INSERT INTO Ticket(EventID, GuestID, Barcode, TicketType) VALUES (?, ?, ?, ?);";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, ticket.getTicketEvent().getId());
            ps.setInt(2, ticket.getOwner().getId());
            ps.setString(3, ticket.getBarCodeID());
            ps.setString(4, ticket.getType());
            ps.executeUpdate();
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * Returns true if the guest already has a ticket to the event, else returns false
     */
    public boolean checkHasTicket(Ticket ticket){
        try (Connection connection = DC.getConnection()){
            String sql = "SELECT * FROM Ticket WHERE EventID = (?) AND GuestID = (?);";
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, ticket.getTicketEvent().getId());
            ps.setInt(2, ticket.getOwner().getId());
            ResultSet rs = ps.executeQuery();
            if (rs.next())
                return true;
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }
}
