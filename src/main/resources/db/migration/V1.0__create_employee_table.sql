drop table if exists company;
drop table if exists employee;
create table company
(
    id            int not null auto_increment primary key,
    employeesNums int,
    companyName   char
);

create table employee
(
    id         int not null auto_increment primary key,
    name       char,
    age        int,
    gender     char,
    salary     double,
    companyId int,
    foreign key (companyId) references company (id)
);
