package dal.DAO;

import be.TicketEvent;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import dal.DBConnector;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TicketTypeDAO {
    private final DBConnector DC = new DBConnector();

    public TicketTypeDAO() throws IOException {
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
     * Retrieves all ticket types for all events on the given list, then returns the event list
     */
    public List<TicketEvent> getEventTicketTypes(List<TicketEvent> eventList) {
        try (Connection connection = DC.getConnection()){
            for (TicketEvent t : eventList) {
                ArrayList<String> ticketTypes = new ArrayList<>();
                String sql = "SELECT TicketType from TicketType WHERE EventID = (?);";
                PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setInt(1, t.getId());
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    String string = rs.getString("TicketType");
                    ticketTypes.add(string);
                }
                t.setTicketTypes(ticketTypes);
            }
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return eventList;
    }
}
