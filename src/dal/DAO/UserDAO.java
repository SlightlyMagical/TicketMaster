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

    /**
     * Checks if the combination of username and password exists in the database.
     * @return a user of the type indicated in the database, if a match is found
     */
    public User handleLogin(String username, String password) {
            User user = null;
            try (Connection connection = DC.getConnection()){
                String sql = "SELECT * FROM Users WHERE Username = ? AND Password = ? COLLATE SQL_Latin1_General_CP1_CS_AS";
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

    /**
     * Retrieves a list of all admins in the database
     * @return an arraylist of admins
     */
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

    /**
     * Retrieves a list of all coordinators in the database
     * @return an arraylist of coordinators
     */
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

    /**
     * Inserts a new user with the provided information into the database
     * @return the ID assigned to the user by the database if successful, or -1 if failed
     */
    public int createUser(String username, String password,String usertype) {
        try (Connection connection = DC.getConnection()){
            String sql = "INSERT INTO Users (Username, Password, Usertype) VALUES (?, ?, ?)";
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, username);
            ps.setString(2, password);
            ps.setString(3, usertype);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
               return rs.getInt(1);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return -1;
    }

    /**
     * Checks if a user with the given username already exists in the database
     */
    public boolean checkIfUsernameTaken(String username){
        try (Connection connection = DC.getConnection()){
            String sql = "SELECT * FROM Users WHERE Username = (?)";
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    /**
     * Deletes the given user from the database
     */
    public void deleteUser(User user) {
        try (Connection connection = DC.getConnection()){
            String sql = "DELETE FROM Users WHERE Username = (?)";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, user.getName());
            ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}

