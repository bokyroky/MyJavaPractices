package ws.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import io.spring.ws.administration.RegisterUserRequest;
import io.spring.ws.administration.RegisterUserResponse;
import io.spring.ws.administration.UserListRequest;
import io.spring.ws.administration.UserListResponse;




@Endpoint
public class UsersEndpoint {

	private static final String NAMESPACE_URI = "http://spring.io/ws/administration";
	@Autowired
	protected JdbcCorporateEventDao jdbcDao;
    
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "registerUserRequest")
	@ResponsePayload
	public RegisterUserResponse registerUser(@RequestPayload RegisterUserRequest request) {
		
		jdbcDao.insertUser(request.getNewUser());
		RegisterUserResponse response = new RegisterUserResponse();
		response.setRegistrationOutcome("SUCCESS!");

		return response;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "userListRequest")
	@ResponsePayload
	public UserListResponse listUsers(@RequestPayload UserListRequest request) {
		UserListResponse users = new UserListResponse();
		users.getUser().addAll(jdbcDao.getAllUsers());
		return users;
	}

}
