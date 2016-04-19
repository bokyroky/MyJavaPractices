package svgroup.org.servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svgroup.org.db.DBAccessor;
import svgroup.org.model.Employee;
import svgroup.org.servlets.helperClasses.DatabaseConnectionData;

/**
 * Servlet implementation class EmployeesPerDepartmentServlet
 */
public class EmployeesPerDepartmentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DatabaseConnectionData dbd;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmployeesPerDepartmentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		dbd = new DatabaseConnectionData(request);
		DBAccessor dba = new DBAccessor(DBAccessor.createNewDataSource(dbd.getServerName(),dbd.getUsername(),dbd.getPassword()));
		List<Employee> employees = dba.getAllEmployees_db();
		request.setAttribute("employeesPerDeprt", getEmployeesPerDepartment(employees));
		RequestDispatcher dispatcher = request.getRequestDispatcher("employeesPerDepartment.jsp");
		dispatcher.include(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	public static HashMap<String, Integer> getEmployeesPerDepartment(List<Employee> employees) {
		HashMap<String, Integer> hash = new HashMap<>();
		for (Employee employee : employees) {
			String departmantName = employee.getDepartment().getDepartmentName();
			if (hash.containsKey(departmantName)) {
				int frequency = hash.get(departmantName);
				frequency++;
				hash.replace(departmantName, frequency);
			} else {
				hash.put(departmantName, 1);
			}
		}
		return hash;
	}

}
