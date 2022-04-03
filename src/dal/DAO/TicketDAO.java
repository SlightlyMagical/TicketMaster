package dal.DAO;

import be.TicketEvent;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import dal.DBConnector;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

public class TicketDAO {
    private final DBConnector DC = new DBConnector();

    public TicketDAO() throws IOException {
    }

    /**
     * Creates a new ticket type for the given event in the database
     */
    public void newTicketType(int eventID, String name) {
        try (Connection connection = DC.getConnection()){
            String sql = "INSERT INTO TicketType(EventID, TicketType) VALUES (?, ?);";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, eventID);
            ps.setString(2, name);
            ps.executeUpdate();
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * Deletes a specific ticket type for the given event in the database
     */
    public void deleteTicketType(int eventID, String name) {
        try (Connection connection = DC.getConnection()){
            String sql = "DELETE FROM TicketType WHERE EventID = (?) AND TicketType = (?);";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, eventID);
            ps.setString(2, name);
            ps.executeUpdate();
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * Retrieves all ticket types for the given event and returns them as a list
     */
    public ArrayList<String> getEventTicketTypes(int eventID) {
        ArrayList<String> ticketTypes = new ArrayList<>();
        try (Connection connection = DC.getConnection()){
            String sql = "SELECT TicketType from TicketType WHERE EventID = (?);";
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, eventID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                String string = rs.getString("TicketType");
                ticketTypes.add(string);
            }
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return ticketTypes;
    }
}
