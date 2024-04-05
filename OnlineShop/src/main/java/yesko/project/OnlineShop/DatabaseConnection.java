package yesko.project.OnlineShop;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import yesko.project.OnlineShop.entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
@PropertySource("classpath:application.yml")
@Scope("singleton")
public class DatabaseConnection {
    @Value("${database.hostname}")
    private String hostname;

    @Value("${database.username}")
    private String username;

    @Value("${database.password}")
    private String password;

    @Value("${database.databaseName}")
    private String databaseName;

    public List<User> executeQuery(String sql) {
        List<User> userList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            // Establish database connection
            connection = DriverManager.getConnection(
                    "jdbc:postgresql://" + hostname + "/" + databaseName,
                    username,
                    password);

            // Create prepared statement
            preparedStatement = connection.prepareStatement(sql);

            // Execute query
            resultSet = preparedStatement.executeQuery();

            // Process results
            while (resultSet.next()) {
                // Assuming User entity has appropriate constructor and setters
                User user = new User(
                        resultSet.getInt("id")
                );
                userList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception appropriately
        } finally {
            // Close resources
            try {
                if (resultSet != null) resultSet.close();
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace(); // Handle the exception appropriately
            }
        }

        return userList;
    }

    public String getConnectionInfo() {
        return "Hostname: " + hostname + ", Username: " + username + ", Database: " + databaseName;
    }
}
