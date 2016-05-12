use master
go
create database UserAdministration
go
use UserAdministration
go
create table UserCredentials
(
	IdUser int primary key identity,
	FirstName nvarchar(100) not null,
	LastName nvarchar(100) not null,
	UserLogin nvarchar(20) not null unique
)



