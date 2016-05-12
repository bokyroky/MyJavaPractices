package ws.app;


import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import spring.administration.ws.RegisterUserRequest;
import spring.administration.ws.RegisterUserResponse;
import spring.administration.ws.UserListRequest;


@Endpoint
public class UsersEndpoint {
	
	private static final String NAMESPACE_URI = "http://spring.administration.ws";
	

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "registerUser")
	@ResponsePayload
	public RegisterUserResponse registerUser(@RequestPayload RegisterUserRequest request) {
		JdbcCorporateEventDao jdbcDao = new JdbcCorporateEventDao();
		jdbcDao.insertUser(request.getNewUser());
		RegisterUserResponse response = new RegisterUserResponse();
		response.setRegistrationOutcome("SUCCESS!");

		return response;
	}
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "listUsers")
	@ResponsePayload
	public UserListRequest listUsers() {
		JdbcCorporateEventDao jdbcDao = new JdbcCorporateEventDao();
		UserListRequest users = new UserListRequest();
		users.getUser().addAll(jdbcDao.getAllUsers());
		return users;
	}
	
	
}
