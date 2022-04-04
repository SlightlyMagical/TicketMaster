package dal.DAO;

import dal.DBConnector;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class GuestDAO {
    private final DBConnector DC = new DBConnector();

    public GuestDAO() throws IOException {
    }

    public void createGuest(String firstName, String lastName, String eMail) {
        try (Connection connection = DC.getConnection()){
            String sql = "INSERT INTO dbo.Guests(FirstName, LastName, Email) VALUES (?, ?, ?);";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, firstName);
            ps.setString(2, lastName);
            ps.setString(3, eMail);
            ps.executeUpdate();
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
