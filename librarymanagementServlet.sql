create database libraryManagement_db;

use libraryManagement_db;

select * from book_management;

select * from member_management;


select * from issue_management;

drop database librarymanagement_db;

USE libraryManagement_db;

UPDATE issue_management
SET due_date = DATE_ADD(issue_date, INTERVAL 7 DAY) WHERE issue_id > 0;





show tables;

delete from issue_management where issue_id = 7;