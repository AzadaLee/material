--insert 
--�﷨1:insert into table_name(column1,column2,......) values(value1,value2,....)
insert into z_student(s_id,s_cid,s_name,s_age) values(sys_guid(),'1234','zhangp',25);
--�﷨2��ע��û�йؼ���values. insert into table_name(column1,column2,......) select column1,column2.......
insert into z_student(s_id,s_cid,s_name,s_age) select sys_guid(),t.c_id,'xx',22 from z_class t

--update 
--�﷨��update table_name set column1_name = 'new value',column2_name='new value' where condition
update z_class t set t.c_name = '����Ӣ��',t.c_grade=2014 where t.c_id = '1234'; 

--truncate
--�﷨��turncate table table_name
truncate table z_class;

delete from z_class;


select * from z_class for update;

--distinct����ʹ�ö��н���ȥ�ص�ʱ��ֻ������е�ֵһ��ʱ�Ż�ﵽȥ�ص�Ч��������distinct�з����е���ǰ�棬����ᱨ��

select distinct t.c_name,t.c_grade from z_class t; 

select t.s_id , distinct(t.s_cid) from z_student t;

--order by 

select * from z_class t order by t.c_grade desc; 
--group by ע�⣺order by ���У�Ӧ�ó�����group by �Ӿ��С�����һ������Ȼ�ĵ��� 
select t.c_name from z_class t  group by t.c_name order by t.c_name

--between and����ѯ��Χ����Ϊ˫��
select * from z_class t where t.c_grade between 2011 and 2014;
