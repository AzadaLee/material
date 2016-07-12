create table z_casewhen(
       id varchar2(50) primary key,
       age number(5),
       birthday varchar2(20)
)

insert into z_casewhen(id,age,birthday) values(sys_guid(),20,'19950101');
insert into z_casewhen(id,age,birthday) values(sys_guid(),21,'19940101');
insert into z_casewhen(id,age,birthday) values(sys_guid(),22,'19930101');
insert into z_casewhen(id,age,birthday) values(sys_guid(),23,'19920101');

--��ʽ1��case ���� when ������ֵ then  ���

select (case t.age
             when 20 then '��ʮ��'
             when 21 then '��ʮһ��'
             when 22 then '��ʮ����'
             else '��ʮ��'
         end ) age,t.birthday
       from z_casewhen t;
       
--��ʽ����case  when ����  then ���
select (
       case
         when t.age = 20 then '��ʮ'
         when t.age = 21 then '��ʮһ'
         when t.age = 22 then '��ʮ��'
         else '��ʮ��'
       end
)from z_casewhen t;


--ע��㣺1��when...then ��β���ܼӷֺţ�2����������end����


drop table z_casewhen;
