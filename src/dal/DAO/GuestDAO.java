package dal.DAO;

import be.Guest;
import dal.DBConnector;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GuestDAO {
    private final DBConnector DC = new DBConnector();

    public GuestDAO() throws IOException {
    }

    /**
     * Inserts the given guest into the database
     */
    public Guest createGuest(Guest guest) {
        try (Connection connection = DC.getConnection()){
            String sql = "INSERT INTO dbo.Guests(FirstName, LastName, Email) VALUES (?, ?, ?);";
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, guest.getFirstName());
            ps.setString(2, guest.getLastName());
            ps.setString(3, guest.getEmail());
            ResultSet rs = ps.executeQuery();
            if (rs.next())
                guest.setId(rs.getInt(1));
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return guest;
    }

    /**
     * Checks if a guest with this email already exists.
     * @return the found guest if true, else returns the original guest
     */
    public Guest checkIfGuestExists(Guest guest){
        try (Connection connection = DC.getConnection()){
            String sql = "SELECT * FROM dbo.Guests WHERE Email = (?);";
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, guest.getEmail());
            ResultSet rs = ps.executeQuery();
            if (rs.next())
                return new Guest(rs.getInt("Id"), rs.getString("FirstName"), rs.getString("LastName"), rs.getString("Email"));
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return guest;
    }

    /**
     * Retrieves a list of all guests from the database
     * @return Arraylist of all guests
     */
    public List<Guest> getGuests() {
        List<Guest> guests = new ArrayList<>();

        try (Connection connection = DC.getConnection()){
            String sql = "SELECT * FROM Guests";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String firstName = rs.getString("FirstName");
                String lastName = rs.getString("LastName");
                String email = rs.getString("Email");
                int id = rs.getInt("Id");
                Guest guest = new Guest(id, firstName, lastName, email);
                guests.add(guest);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return guests;
    }

    /**
     * Deletes the given guest from the database
     */
    public void deleteGuest(Guest guest) {
        try (Connection connection = DC.getConnection()){
            String sql = "DELETE FROM Guests WHERE Id = (?)";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, guest.getId());
            ps.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
