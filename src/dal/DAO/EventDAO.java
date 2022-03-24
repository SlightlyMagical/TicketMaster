package dal.DAO;

import be.TicketEvent;

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
            ps.setString(1, ticketEvent.getName());
            ps.setString(2, ticketEvent.getLocation());
            ps.setDate(3, Date.valueOf(ticketEvent.getStartDate()));
            ps.setTime(4, Time.valueOf(ticketEvent.getStartTime()));
            if (ticketEvent.getEndDate() != null)
                ps.setDate(5, Date.valueOf(ticketEvent.getEndDate()));
            else
                ps.setDate(5, null);

            if (ticketEvent.getEndTime() != null)
                ps.setTime(6, Time.valueOf(ticketEvent.getEndTime()));
            else
                ps.setTime(6, null);

            ps.setString(7, ticketEvent.getDescription());
            ps.setString(8, ticketEvent.getLocationGuide());
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
                String description = rs.getString("EventDescription");

                TicketEvent ticketEvent = new TicketEvent(id, name, location, startDate, description, startTime);
                try{
                    LocalDate endDate = rs.getDate("EndDate").toLocalDate();
                    ticketEvent.setEndDate(endDate);
                } catch (Exception ignored) {}
                try{
                    LocalTime endTime = rs.getTime("EndTime").toLocalTime();
                    ticketEvent.setEndTime(endTime);
                } catch (Exception ignored) {}
                try{
                    String locationGuide = rs.getString("LocationGuide");
                    ticketEvent.setLocationGuide(locationGuide);
                } catch (SQLException ignored) {}

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