package dal.DAO;

import be.Guest;
import dal.DBConnector;

import java.io.IOException;
import java.sql.*;

public class GuestDAO {
    private final DBConnector DC = new DBConnector();

    public GuestDAO() throws IOException {
    }

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
}
