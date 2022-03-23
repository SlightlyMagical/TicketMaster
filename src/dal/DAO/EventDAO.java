package dal.DAO;

import be.TicketEvent;
import com.microsoft.sqlserver.jdbc.SQLServerException;

import dal.DBConnector;


import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class EventDAO {
    private DBConnector DC = new DBConnector();

    public EventDAO() throws IOException {
    }

    public TicketEvent createTicketEvent(TicketEvent ticketEvent) {
        try (Connection connection = DC.getConnection()) {
            String sql = "INSERT INTO Events(EventName, Location, StartDate, StartTime, EndDate, EndTime, EventDescription , LocationGuide) VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, ticketEvent.getTicketEventName());
            ps.setString(2, ticketEvent.getTicketEventLocation());
            ps.setDate(3, Date.valueOf(ticketEvent.getTicketEventStartDate()));
            ps.setTime(4, Time.valueOf(ticketEvent.getTicketEventStartTime()));
            ps.setDate(5, Date.valueOf(ticketEvent.getTicketEventEndDate()));
            ps.setTime(6, Time.valueOf(ticketEvent.getTicketEventEndTime()));
            ps.setString(7, ticketEvent.getTicketEventDescription());
            ps.setString(8, ticketEvent.getTicketEventLocationGuide());
            int affectedRows = ps.executeUpdate();
            if (affectedRows == 1) {
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    int id = rs.getInt(1);
                    ticketEvent.setId(id);

                    return ticketEvent;
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
        return null;
    }

    public List<TicketEvent> getEvents(){
        List<TicketEvent> eventList = new ArrayList<>();
        try(Connection connection = DC.getConnection()){
            String sql = "SELECT * FROM Events WHERE EventID > ?;"; // TODO: Add "WHERE" condition
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, 0);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                int id = rs.getInt("EventID");
                String name = rs.getString("EventName");
                String location = rs.getString("Location");
                LocalDate startDate = rs.getDate("StartDate").toLocalDate();
                LocalTime startTime = rs.getTime("StartTime").toLocalTime();
                //LocalDate endDate = rs.getDate("EndDate").toLocalDate();
                //LocalTime endTime = rs.getTime("EndTime").toLocalTime();
                String description = rs.getString("EventDescription");
                String locationGuide = rs.getString("LocationGuide");
                TicketEvent ticketEvent = new TicketEvent(id, name, location, startDate, description, startTime);

                eventList.add(ticketEvent);
            }

        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return eventList;
    }

    public void testConnection() throws SQLException {
        try(Connection connection = DC.getConnection()){
            System.out.println("Success");
        }
    }
}