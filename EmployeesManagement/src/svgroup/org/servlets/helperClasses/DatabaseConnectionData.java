package svgroup.org.servlets.helperClasses;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class DatabaseConnectionData {
	String serverName, username,password;
	
	

	public DatabaseConnectionData(HttpServletRequest request) {
		super();
		HttpSession session = request.getSession();
		this.serverName= session.getAttribute("serverName").toString();
		this.username= session.getAttribute("username").toString();
		this.password= session.getAttribute("password").toString();
	}

	public String getServerName() {
		return serverName;
	}

	

	public String getUsername() {
		return username;
	}

	

	public String getPassword() {
		return password;
	}

	

}
