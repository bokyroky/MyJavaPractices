package ws.app;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import spring.administration.ws.User;



@Repository
public class JdbcCorporateEventDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
    
    public void insertUser(User user){
    	this.jdbcTemplate.update(
    	        "insert into UserCredentials (FirstName, LastName, UserLogin) values (?, ?, ?)",
    	        user.getFirstName(), user.getLastName(),user.getLogin());
    }
    
    public List<User> getAllUsers(){
    	return this.jdbcTemplate.query( "select FirstName, LastName, UserLogin from UserCredentials", new UserMapper());
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
