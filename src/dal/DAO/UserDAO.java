package dal.DAO;

import be.Admin;
import be.EventCoordinator;
import be.User;
import dal.DBConnector;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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

    public List<User> getAdmins() {

        List<User> admins = new ArrayList<>();

        try (Connection connection = DC.getConnection()){
            String sql = "SELECT * FROM Users WHERE Usertype = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, "Admin");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String name = rs.getString("Username");
                int id = rs.getInt("Id");
                User user = new User(id, name);
                admins.add(user);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return admins;
    }

    public List<User> getCoordinators() {

        List<User> coordinators = new ArrayList<>();

        try (Connection connection = DC.getConnection()){
            String sql = "SELECT * FROM Users WHERE Usertype = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, "Coordinator");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String name = rs.getString("Username");
                int id = rs.getInt("Id");
                User user = new User(id, name);
                coordinators.add(user);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return coordinators;
    }

    public int createUser(String username, String password,String usertype) {
        try (Connection connection = DC.getConnection()){
            String sql = "INSERT INTO Users (Username, Password, Usertype) VALUES (?, ?, ?)";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            ps.setString(3, usertype);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {

               return rs.getInt("Id");
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return -1;
    }
}

