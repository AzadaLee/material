select * from t_user;
create table t_student(
  id varchar2(32) primary key,
  stu_name varchar2(60) not null,
  stu_idcode varchar2(18) unique,
  stu_class number(2),
  stu_grade number(2), 
  stu_startschool date 
)
comment on table t_student is '学生成绩表';
comment on column t_student.id is '主键';
comment on column t_student.stu_name is '姓名';
comment on column t_student.stu_idcode is '身份证号';
comment on column t_student.stu_class is '年级';
comment on column t_student.stu_grade is '班级';
comment on column t_student.stu_startschool is '入学时间';


create table t_course(
  id varchar2(32) primary key,
  course_name varchar2(50)
);
comment on table t_course is '课程表';
comment on column t_course.id is '主键';
comment on column t_course.course_name is '课程名'; 

select * from t_course;

insert into t_course(id,course_name) values(sys_guid(),'语文');
insert into t_course(id,course_name) values(sys_guid(),'数学');
insert into t_course(id,course_name) values(sys_guid(),'英语');
insert into t_course(id,course_name) values(sys_guid(),'物理');
insert into t_course(id,course_name) values(sys_guid(),'化学');
insert into t_course(id,course_name) values(sys_guid(),'生物');
commit;

create table t_student_score(
  id varchar2(32) primary key,
  stu_name varchar2(60),
  stu_grade varchar2(30),
  course_name varchar2(50),
  score number(4,1),
  score_date date
)
