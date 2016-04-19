package svgroup.org.model;

public class Department {
	
int idDepartmant;
String id;

public String getId() {
	return String.valueOf(this.idDepartmant);
}

String departmentName;

public int getIdDepartmant() {
	return idDepartmant;
}
public void setIdDepartmant(int idDepartmant) {
	this.idDepartmant = idDepartmant;
}
public String getDepartmentName() {
	return departmentName;
}
public void setDepartmentName(String departmentName) {
	this.departmentName = departmentName;
}

}
