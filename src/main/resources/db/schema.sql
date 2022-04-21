drop table IF EXISTS subject_learning;
drop table IF EXISTS student;
drop table IF EXISTS department;

create table student (
                                    id bigint NOT NULL auto_increment,
                                    first_name varchar(25),
                                    last_name varchar(25),
                                    email varchar(25),
                                    dept_id bigint,
                                    primary key (id)
);
create table department (
                                       id bigint NOT NULL auto_increment,
                                       dept_name varchar(25),
                                       primary key (id)
);

create table subject_learning (
                                             id bigint NOT NULL auto_increment,
                                             sub_name varchar(25),
                                             marks_obtained int,
                                             student_id bigint,
                                             primary key (id),
                                             FOREIGN KEY (student_id) REFERENCES student(id)
);
alter table subject_learning add FOREIGN KEY (student_id) REFERENCES student(id);
alter table student add FOREIGN KEY (dept_id) REFERENCES department(id);

