--insert 
--语法1:insert into table_name(column1,column2,......) values(value1,value2,....)
insert into z_student(s_id,s_cid,s_name,s_age) values(sys_guid(),'1234','zhangp',25);
--语法2，注意没有关键字values. insert into table_name(column1,column2,......) select column1,column2.......
insert into z_student(s_id,s_cid,s_name,s_age) select sys_guid(),t.c_id,'xx',22 from z_class t

--update 
--语法：update table_name set column1_name = 'new value',column2_name='new value' where condition
update z_class t set t.c_name = '商务英语',t.c_grade=2014 where t.c_id = '1234'; 

--truncate
--语法：turncate table table_name
truncate table z_class;

delete from z_class;


select * from z_class for update;

--distinct：当使用多列进行去重的时候，只有这多列的值一样时才会达到去重的效果；并且distinct中放在列的最前面，否则会报错

select distinct t.c_name,t.c_grade from z_class t; 

select t.s_id , distinct(t.s_cid) from z_student t;

--order by 

select * from z_class t order by t.c_grade desc; 
--group by 注意：order by 中列，应该出现在group by 子句中。这是一个很显然的道理。 
select t.c_name from z_class t  group by t.c_name order by t.c_name

--between and：查询范围控制为双闭
select * from z_class t where t.c_grade between 2011 and 2014;
