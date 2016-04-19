package svgroup.org.servlets;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import svgroup.org.db.DBAccessor;
import svgroup.org.model.Employee;
import svgroup.org.servlets.helperClasses.DatabaseConnectionData;

/**
 * Servlet implementation class EmployeesServlet
 */
public class EmployeesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DatabaseConnectionData dbd;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EmployeesServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		if (!isConnectionSessionDataSet(request)) {
			setConnectionSessionAttributes(request, response);
		}
		dbd = new DatabaseConnectionData(request);

		DBAccessor dba = new DBAccessor(
				DBAccessor.createNewDataSource(dbd.getServerName(), dbd.getUsername(), dbd.getPassword()));
		String idEmployee = request.getParameter("idEmployee");
		if (idEmployee != null) {
			// Delete employee
			deleteEmployee(dba, idEmployee);
		} else {
			// Show employees
			try {
				showEmployeesList(dba, request, response);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	private void setConnectionSessionAttributes(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		String serverName = request.getParameter("serverName");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		session.setAttribute("serverName", serverName);
		session.setAttribute("username", username);
		session.setAttribute("password", password);

	}

	private boolean isConnectionSessionDataSet(HttpServletRequest request) {
		HttpSession session = request.getSession();
		if (session.getAttribute("password") == null)
			return false;
		else
			return true;
	}

	private void deleteEmployee(DBAccessor dba, String idEmployee) {
		dba.deleteEmployee(Integer.valueOf(idEmployee));

	}

	private void showEmployeesList(DBAccessor dba, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Employee> employees = dba.getAllEmployees_db();
		request.setAttribute("employees", employees);
		request.setAttribute("employeesPerDeprt", EmployeesPerDepartmentServlet.getEmployeesPerDepartment(employees));
		request.setCharacterEncoding("UTF-8");
		RequestDispatcher dispatcher = request.getRequestDispatcher("employees.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String idEmployee = request.getParameter("idEmployee");
		String fName = request.getParameter("firstName");
		String lName = request.getParameter("lastName");
		String dateString = request.getParameter("emplDate");
		Date date = convertStringToDate(dateString);
		String departmentId = request.getParameter("departmentID");
		DBAccessor dba = new DBAccessor(
				DBAccessor.createNewDataSource(dbd.getServerName(), dbd.getUsername(), dbd.getPassword()));
		if (idEmployee != null) {
			// Update employee
			dba.updateEmployee(Integer.valueOf(idEmployee), fName, lName, date, Integer.valueOf(departmentId));

		} else {
			// Insert new employee
			dba.insertNewEmployee_db(fName, lName, date, Integer.valueOf(departmentId));
		}

	}

	private Date convertStringToDate(String dateString) {
		DateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
		try {
			Date date = formatter.parse(dateString);
			return date;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

	}

}
