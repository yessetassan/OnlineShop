package yesko.project.OnlineShop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import yesko.project.OnlineShop.entity.User;

import java.util.List;

@SpringBootApplication
public class OnlineShopApplication implements CommandLineRunner {

	@Autowired
	private DatabaseConnection databaseConnection;

	public static void main(String[] args) {
		SpringApplication.run(OnlineShopApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		List<User> userList = databaseConnection.executeQuery("SELECT * FROM t_user");

		// Retrieve and print connection info
		System.out.println(databaseConnection.getConnectionInfo());

		// Try getting another instance (should return the same instance)
		DatabaseConnection anotherDatabaseConnection = databaseConnection;
	}

}
