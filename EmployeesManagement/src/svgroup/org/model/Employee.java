package svgroup.org.model;

import java.util.Date;

public class Employee {
	int idEmployee;
	String firstName;
	String lastName;
	Date employmentDate;
	
	Department department;
	public Employee(){
		
	}
	
	public Employee(String firstName, String lastName, Date employmentDate, Department department) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.employmentDate = employmentDate;
		this.department = department;
	}
	public int getIdEmployee() {
		return idEmployee;
	}
	public void setIdEmployee(int idEmployee) {
		this.idEmployee = idEmployee;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public Date getEmploymentDate() {
		return employmentDate;
	}
	public void setEmploymentDate(Date employmentDate) {
		this.employmentDate = employmentDate;
	}
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}
	
}
