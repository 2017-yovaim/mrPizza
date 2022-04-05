package SpringP.repo;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.userdetails.UserDetails;

public class UserRepository
{
	private JdbcTemplate connection;
	
	public void add(UserDetails user)
	{
		connection.update("INSERT INTO Users (username, password, enabled) VALUES(?, ?, ?)", 
				user.getUsername(), user.getPassword(), user.isEnabled());
		connection.update("INSERT INTO Authorities (username, authority) VALUES(?, ?)",
				user.getUsername(), "ROLE_USER");
	}
}
