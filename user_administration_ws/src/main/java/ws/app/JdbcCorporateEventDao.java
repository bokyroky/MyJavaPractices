package ws.app;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import io.spring.ws.administration.User;

@Repository
public class JdbcCorporateEventDao {

	@Autowired
	protected  JdbcTemplate jdbcTemplate;
	

	public void insertUser(User user) {
		
		this.jdbcTemplate.update("insert into UserCredentials (FirstName, LastName, UserLogin) values (?, ?, ?)",
				new Object[] { user.getFirstName(), user.getLastName(), user.getLogin() });
	}

	public List<User> getAllUsers() {
		return this.jdbcTemplate.query("select FirstName, LastName, UserLogin from UserCredentials", new UserMapper());
	}

	private static final class UserMapper implements RowMapper<User> {

		public User mapRow(ResultSet rs, int rowNum) throws SQLException {
			User user = new User();
			user.setFirstName(rs.getString("FirstName"));
			user.setLastName(rs.getString("LastName"));
			user.setLogin(rs.getString("UserLogin"));
			return user;
		}
	}

}
