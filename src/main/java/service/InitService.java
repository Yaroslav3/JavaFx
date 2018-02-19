package service;

import com.mysql.fabric.jdbc.FabricMySQLDriver;
import model.User;

import java.sql.*;

public class InitService {
    private static String URL = "jdbc:sqlite:miracle.sqlite";

    public static void createTables() {
        try (Connection connection = DriverManager.getConnection(URL);
             Statement statement = connection.createStatement()) {
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS \"users\" (\"id\" INTEGER PRIMARY KEY  NOT NULL ,\"name\" TEXT DEFAULT (null) ,\"about\" TEXT DEFAULT (null))");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addUser(User user) throws SQLException {
        String ADD_USER = "INSERT INTO users(name, about) VALUES (?,?);";
        Driver driver = new FabricMySQLDriver();
        DriverManager.registerDriver(driver);
        Connection connection = DriverManager.getConnection(URL);
        PreparedStatement preparedStatement = connection.prepareStatement(ADD_USER);
        preparedStatement.setString(1, user.getName());
        preparedStatement.setString(2, user.getAbout());
        preparedStatement.execute();
        preparedStatement.close();
    }
}
