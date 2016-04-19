package svgroup.org.db;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.sql.DataSource;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;

import svgroup.org.model.Department;
import svgroup.org.model.Employee;

public class DBAccessor {
	final DataSource dataSource;

	public DBAccessor(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public List<Employee> getAllEmployees_db() {
		List<Employee> employees = new LinkedList<>();

		final String callQuery = "{CALL GetAllEmployees}";
		try (Connection conn = dataSource.getConnection(); CallableStatement cs = conn.prepareCall(callQuery)) {
			ResultSet rs = cs.executeQuery();
			while (rs.next()) {
				Employee e = new Employee();
				e.setIdEmployee(rs.getInt("IDEmployee"));
				e.setFirstName(rs.getString("FirstName"));
				e.setLastName(rs.getString("LastName"));
				java.util.Date d = rs.getTimestamp("EmploymentDate");
				e.setEmploymentDate(d);
				e.setDepartment(getDepartment(rs.getInt("DepartmentID")));
				employees.add(e);
			}
		} catch (SQLException ex) {
			Logger.getLogger(DBAccessor.class.getName()).log(Level.SEVERE, null, ex);
		}
		return employees;

	}

	public List<Department> getAllDepartments_db() {
		List<Department> departments = new LinkedList<>();

		final String callQuery = "{CALL GetAllDepartments}";
		try (Connection conn = dataSource.getConnection(); CallableStatement cs = conn.prepareCall(callQuery)) {
			ResultSet rs = cs.executeQuery();
			while (rs.next()) {
				Department d = new Department();
				d.setIdDepartmant(rs.getInt("IDDepartment"));
				d.setDepartmentName(rs.getString("Name"));
				departments.add(d);
			}
		} catch (SQLException ex) {
			Logger.getLogger(DBAccessor.class.getName()).log(Level.SEVERE, null, ex);
		}
		return departments;

	}

	public Employee getEmployee(int idEmployee) {
		Employee e = new Employee();
		String callQuery = "{CALL GetEmployee(?)}";
		try (Connection conn = dataSource.getConnection(); CallableStatement cs = conn.prepareCall(callQuery)) {
			cs.setInt(1, idEmployee);
			ResultSet rs = cs.executeQuery();
			while (rs.next()) {
				e.setIdEmployee(rs.getInt("IDEmployee"));
				e.setFirstName(rs.getString("FirstName"));
				e.setLastName(rs.getString("LastName"));
				e.setEmploymentDate(rs.getDate("EmploymentDate"));
				e.setDepartment(getDepartment(rs.getInt("DepartmentID")));
			}
		} catch (SQLException ex) {
			Logger.getLogger(DBAccessor.class.getName()).log(Level.SEVERE, null, ex);
		}
		return e;
	}

	private Department getDepartment(int idDepartment) {
		Department d = new Department();
		String callQuery = "{CALL GetDepartment(?)}";
		try (Connection conn = dataSource.getConnection(); CallableStatement cs = conn.prepareCall(callQuery)) {
			cs.setInt(1, idDepartment);
			ResultSet rs = cs.executeQuery();
			while (rs.next()) {
				d.setIdDepartmant(rs.getInt("IDDepartment"));
				d.setDepartmentName(rs.getString("Name"));
			}
		} catch (SQLException ex) {
			Logger.getLogger(DBAccessor.class.getName()).log(Level.SEVERE, null, ex);
		}
		return d;
	}

	public int insertNewEmployee_db(String firstName, String lastName, Date emplDate, int departmentId) {
		int rowsInserted = 0;
		String callQuery = "{CALL InsertNewEmployee(?,?,?,?)}";
		try (Connection conn = dataSource.getConnection(); CallableStatement cs = conn.prepareCall(callQuery)) {
			cs.setString(1, firstName);
			cs.setString(2, lastName);
			cs.setDate(3, new java.sql.Date(emplDate.getTime()));
			cs.setInt(4, departmentId);
			rowsInserted = cs.executeUpdate();
		} catch (SQLException ex) {
			Logger.getLogger(DBAccessor.class.getName()).log(Level.SEVERE, null, ex);
		}

		return rowsInserted;
	}

	public static DataSource createNewDataSource(String serverName, String user, String pass) {
		SQLServerDataSource dataSource = new SQLServerDataSource();
		dataSource.setServerName(serverName);
		dataSource.setDatabaseName("Employees");
		dataSource.setUser(user);
		dataSource.setPassword(pass);
		return dataSource;
	}


	public void updateEmployee(Integer idEmployee, String firstName, String lastName, Date emplDate, int departmentId) {
		String callQuery = "{CALL UpdateEmployee(?,?,?,?,?)}";
		try (Connection conn = dataSource.getConnection(); CallableStatement cs = conn.prepareCall(callQuery)) {
			cs.setInt(1, idEmployee);
			cs.setString(2, firstName);
			cs.setString(3, lastName);
			cs.setDate(4, new java.sql.Date(emplDate.getTime()));
			cs.setInt(5, departmentId);
			cs.executeUpdate();
		} catch (SQLException ex) {
			Logger.getLogger(DBAccessor.class.getName()).log(Level.SEVERE, null, ex);
		}

	}

	public void deleteEmployee(Integer idEmployee) {
		String callQuery = "{CALL DeleteEmployee(?)}";
		try (Connection conn = dataSource.getConnection(); CallableStatement cs = conn.prepareCall(callQuery)) {
			cs.setInt(1, idEmployee);			
			cs.executeUpdate();
		} catch (SQLException ex) {
			Logger.getLogger(DBAccessor.class.getName()).log(Level.SEVERE, null, ex);
		}
		
	}
}
