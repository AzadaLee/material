select * from t_user;
create table t_student(
  id varchar2(32) primary key,
  stu_name varchar2(60) not null,
  stu_idcode varchar2(18) unique,
  stu_class number(2),
  stu_grade number(2), 
  stu_startschool date 
)
comment on table t_student is 'ѧ���ɼ���';
comment on column t_student.id is '����';
comment on column t_student.stu_name is '����';
comment on column t_student.stu_idcode is '���֤��';
comment on column t_student.stu_class is '�꼶';
comment on column t_student.stu_grade is '�༶';
comment on column t_student.stu_startschool is '��ѧʱ��';


create table t_course(
  id varchar2(32) primary key,
  course_name varchar2(50)
);
comment on table t_course is '�γ̱�';
comment on column t_course.id is '����';
comment on column t_course.course_name is '�γ���'; 

select * from t_course;

insert into t_course(id,course_name) values(sys_guid(),'����');
insert into t_course(id,course_name) values(sys_guid(),'��ѧ');
insert into t_course(id,course_name) values(sys_guid(),'Ӣ��');
insert into t_course(id,course_name) values(sys_guid(),'����');
insert into t_course(id,course_name) values(sys_guid(),'��ѧ');
insert into t_course(id,course_name) values(sys_guid(),'����');
commit;

create table t_student_score(
  id varchar2(32) primary key,
  stu_name varchar2(60),
  stu_grade varchar2(30),
  course_name varchar2(50),
  score number(4,1),
  score_date date
)
