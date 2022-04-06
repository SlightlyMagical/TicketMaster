package dal.DAO;

import be.Admin;
import be.EventCoordinator;
import be.User;
import dal.DBConnector;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

public class UserDAO {
    private final DBConnector DC = new DBConnector();

    public UserDAO() throws IOException {
    }

    public User handleLogin(String username, String password) {

            User user = null;
            try (Connection connection = DC.getConnection()){
                String sql = "SELECT * FROM Users WHERE Username = ? AND Password = ? ";
                PreparedStatement ps = connection.prepareStatement(sql);
                ps.setString(1, username);
                ps.setString(2, password);
                ResultSet rs = ps.executeQuery();
                while (rs.next()){
                    String type = rs.getString("Usertype");
                   if (type.equals("Coordinator")) {
                       user = new EventCoordinator(rs.getString("Username"), rs.getInt("Id"), new ArrayList<>(), new ArrayList<>());
                       //The Arrays list should not be empty - put in data for both coordinator and admin

                   } else if (type.equals("Admin")) {
                       user = new Admin(rs.getString("Username"), rs.getInt("Id"), new ArrayList<>(), new ArrayList<>());
                   }
                }
            }catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            return user;
        }
    }

