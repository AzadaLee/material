
create table z_class(
       c_id varchar2(50) primary key,
       c_name varchar2(200),
       c_grade number(4)
)

alter table z_class add constraint u_grade unique(c_grade);

alter table z_class disable constraint u_grade;

alter table z_class modify c_name not null;

create table z_student (
       s_id varchar2(50) primary key,
       s_cid varchar2(50),
       s_name varchar2(200),
       s_age number(3)
)

alter table z_student add constraint fk_cid foreign key (s_cid) references z_class (c_id);

alter table z_student disable constraint fk_cid;

alter table z_student enable constraint fk_cid;


insert into z_class(c_id,c_name,c_grade) values('1234','工商管理',2010);


insert into z_student(s_id,s_cid,s_name,s_age) values(sys_guid(),'1234','zhangp',25);

delete from z_student;

insert into z_student(s_id,s_cid,s_name,s_age)
select sys_guid(),t.c_id,'xx',22 from z_class t

select * from z_student;
