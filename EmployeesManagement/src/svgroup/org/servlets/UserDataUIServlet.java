package svgroup.org.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import svgroup.org.db.DBAccessor;
import svgroup.org.model.Department;
import svgroup.org.model.Employee;
import svgroup.org.servlets.helperClasses.DatabaseConnectionData;

/**
 * Servlet implementation class UserDataUIServlet
 */
public class UserDataUIServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DatabaseConnectionData dbd;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserDataUIServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		dbd = new DatabaseConnectionData(request);
		DBAccessor dba = new DBAccessor(DBAccessor.createNewDataSource(dbd.getServerName(),dbd.getUsername(),dbd.getPassword()));
		List<Department> departments = dba.getAllDepartments_db();
		request.setAttribute("departments", departments);
		int idEmployee = Integer.valueOf(request.getParameter("idEmployee"));
		if(idEmployee!=-1){
			Employee employee = dba.getEmployee(idEmployee);
			request.setAttribute("employee", employee);
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher("userDataUI.jsp");
		dispatcher.include(request, response);
	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
