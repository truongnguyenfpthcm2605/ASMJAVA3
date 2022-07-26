/*
Ngay tao 16/7/2022
Kết nối với Asmjav3
*/

CREATE DATABASE STUDENTMANAGER
ON
(
	NAME = STUDENTMANAGER_dat,
	FILENAME = 'D:\JAVA3\STUDENTMANAGER.dat',
	SIZE = 10,
	MAXSIZE = 50,
	FILEGROWTH = 5
)
LOG ON
(
	NAME = STUDENTMANAGER_ldf,
	FILENAME = 'D:\JAVA3\STUDENTMANAGER.ldf',
	SIZE = 10MB,
	MAXSIZE = 50MB,
	FILEGROWTH = 5MB

)
GO
USE STUDENTMANAGER
GO

CREATE TABLE USERS
(
	USERNAME NVARCHAR(10) NOT NULL PRIMARY KEY,
	PASSWORD NVARCHAR(10) ,
	ROLE NVARCHAR(5)
)
GO
CREATE TABLE STUDENTS
(
	MASV NVARCHAR(7) NOT NULL PRIMARY KEY,
	HOTEN NVARCHAR(50),
	EMAIL NVARCHAR(50),
	SODT NVARCHAR(12),
	GIOITINH BIT,
	DIACHI NVARCHAR(100),
	HINH NVARCHAR(50)
)
GO
CREATE TABLE GRADE 
(
	ID INT NOT NULL,
	MASV NVARCHAR(7) REFERENCES STUDENTS(MASV),
	TOAN FLOAT,
	NGUVAN FLOAT,
	TIENGANH FLOAT

)
GO
-- rang buoc duy nhat 1 ma sinh vien có 1 khoa ngoai
ALTER TABLE students ADD CONSTRAINT onlyOne UNIQUE(Masv)
alter table users add unique(masv)
alter table grade add unique(masv)
go

-- KHI THEM 1 SV MOI THI SE THEM 1 GRADE CUA SINH VIEN VA 1 ACCOUNT CUA SV VAO USER
drop trigger if exists ADD_ALLACOUNT
GO
CREATE  TRIGGER ADD_ALLACOUNT ON STUDENTS INSTEAD OF INSERT
 AS
 BEGIN
	INSERT INTO STUDENTS SELECT * FROM inserted
	-- GIANG VIEN TU UPDATE DIEM THEO QUA TRINH HOC
	INSERT INTO GRADE select  MASV ,NULL,NULL,NULL from inserted
	-- SINH VIEN CO THE DOI PASS DO TRUONG CAP ACCOUNT
	INSERT INTO USERS select masv,N'123','sv', MASV FROM inserted
 END
 GO
 -- KHI XOA 1 STUDENT SE XOA ALL THONG TIN LIEN QUAN CUA STUDENT DO
 drop trigger if exists DELETE_ALLACCOUNT
 go
CREATE TRIGGER DELETE_ALLACCOUNT ON STUDENTS INSTEAD OF DELETE
 AS
  BEGIN
	delete from USERS where MASV in (select MASV from deleted)
	delete from grade where MASV in (select MASV from deleted)
	delete from STUDENTS where MASV in (select MASV from deleted)
 END
 GO

 INSERT INTO bangao VALUES (N'PS37775',N'Huỳnh Thị Mỹ Ngọc',N'Ngoc@fpt.edu.vn',N'0377379289',0,N'Quận 1 ,TP HCM',null)

 SELECT * FROM GRADE
 SELECT * FROM STUDENTS
 select * from users
 delete from bangao
 select  * from bangao

 ALTER TABLE users
DROP CONSTRAINT UQ__USERS__60228A29642495A0 ;


 insert into users values(N'admin',N'12345',N'ad',null)

 --update USERS set PASSWORD = ? where USERNAME = ?

GO
CREATE TABLE bangao
(
	MASV NVARCHAR(7) NOT NULL PRIMARY KEY,
	HOTEN NVARCHAR(50),
	EMAIL NVARCHAR(50),
	SODT NVARCHAR(12),
	GIOITINH BIT,
	DIACHI NVARCHAR(100),
	HINH NVARCHAR(50)
)

drop trigger if exists insert_bangao
go
create proc  insert_bangao
as
begin

	insert into STUDENTS select * from bangao where MASV not in (select MASV from STUDENTS)
	declare @masv table (masv varchar(9)) 
	insert into @masv select masv from students where masv not in ( select masv from bangao)


	delete from STUDENTS where MASV in (select MASV from @masv)

	delete from bangao
end
insert into bangao (MASV,HOTEN,EMAIL,SODT,GIOITINH,DIACHI,HINH) values (?,?,?,?,?,?,?);

execute  insert_bangao


