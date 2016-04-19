USE master
GO
CREATE DATABASE Employees
GO
USE Employees
GO

CREATE TABLE Department
(
	IDDepartment int primary key identity,
	Name nvarchar(50) not null
)

CREATE TABLE Employee
(
	IDEmployee int primary key identity,
	FirstName nvarchar(30) not null,
	LastName nvarchar(30) not null,
	EmploymentDate date not null,
	DepartmentID int foreign key references Department(IDDepartment)
)

GO

CREATE PROC GetAllEmployees
AS
BEGIN
	SELECT * FROM Employee
END

GO

CREATE PROC GetEmployee
@idEmployee int
AS
BEGIN
	SELECT * FROM Employee e
	WHERE @idEmployee=e.IDEmployee
END

GO

CREATE PROC GetAllDepartments
AS
BEGIN
	SELECT *  FROM Department
END

GO

CREATE PROC GetDepartment
@idDepartment int
AS
BEGIN
	SELECT * FROM Department
	WHERE IDDepartment=@idDepartment
END

GO

CREATE PROC InsertNewEmployee
@firstName nvarchar(30),
@lastName nvarchar(30),
@employmentDate date,
@departmentID int
AS
BEGIN
	INSERT INTO Employee VALUES(@firstName,@lastName,@employmentDate,@departmentID)
END

GO

CREATE PROC UpdateEmployee
@idEmployee int,
@firstName nvarchar(30),
@lastName nvarchar(30),
@employmentDate date,
@departmentID int
AS
BEGIN
	Update Employee 
	SET FirstName=@firstName, 
		LastName=@lastName,
		EmploymentDate=@employmentDate,
		DepartmentID=@departmentID
	WHERE IDEmployee=@idEmployee
END

GO

CREATE PROC DeleteEmployee
@idEmployee int
AS
BEGIN
	DELETE FROM Employee
	WHERE IDEmployee=@idEmployee
END

GO
--* Inserting departments
INSERT INTO Department VALUES('Board')
INSERT INTO Department VALUES('Office of the Board')
INSERT INTO Department VALUES('Sales')
INSERT INTO Department VALUES('System soluton and services')
INSERT INTO Department VALUES('Application solution and services')

--* Inserting employees
INSERT INTO Employee VALUES('Shay','Given','20080307','1')
INSERT INTO Employee VALUES('Stephen','Ward','20080307','2')
INSERT INTO Employee VALUES('Richard','Dunne','20090307','2')
INSERT INTO Employee VALUES('Glenn','Whelan','20090307','3')
INSERT INTO Employee VALUES('Aiden','McGeady','20090307','3')
INSERT INTO Employee VALUES('Keith','Andrews','20090307','4')
INSERT INTO Employee VALUES('Paul','Walters','20090307','4')
INSERT INTO Employee VALUES('Stephen','Hunt','20090307','5')
INSERT INTO Employee VALUES('Paul','Green','20100307','5')



