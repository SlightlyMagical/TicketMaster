package dal;

import be.TicketEvent;

import java.io.IOException;
import java.sql.*;

public class EventDAO {
    private DBConnector DC = new DBConnector();

    public EventDAO() throws IOException {
    }

    public void createTicketEvent(TicketEvent ticketEvent) {
        try (Connection connection = DC.getConnection()) {
            String sql = "INSERT INTO Events(EventName, Location, StartDate, EndDate, EventDescripton, LocationGuide) VALUES (?, ?, ?, ?, ?, ?);";
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, ticketEvent.getTicketEventName());
            ps.setString(2, ticketEvent.getTicketEventLocation());
            ps.setDate(3, Date.valueOf(ticketEvent.getTicketEventStartDate()));
            ps.setDate(4, Date.valueOf(ticketEvent.getTicketEventEndDate()));
            ps.setString(5, ticketEvent.getTicketEventDescription());
            ps.setString(6, ticketEvent.getTicketEventLocationGuide());
            int affectedRows = ps.executeUpdate();
            if (affectedRows == 1) {
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    int id = rs.getInt(1);

                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}