use master
go
create database Articles
go
use Articles
go

create table Article

(
	IDArticle int primary key identity,
	Title nvarchar(50),
	Content nvarchar(max)

)
select * from Article