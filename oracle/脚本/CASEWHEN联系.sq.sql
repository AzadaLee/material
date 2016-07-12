create table z_casewhen(
       id varchar2(50) primary key,
       age number(5),
       birthday varchar2(20)
)

insert into z_casewhen(id,age,birthday) values(sys_guid(),20,'19950101');
insert into z_casewhen(id,age,birthday) values(sys_guid(),21,'19940101');
insert into z_casewhen(id,age,birthday) values(sys_guid(),22,'19930101');
insert into z_casewhen(id,age,birthday) values(sys_guid(),23,'19920101');

--方式1：case 变量 when 变量的值 then  结果

select (case t.age
             when 20 then '二十岁'
             when 21 then '二十一岁'
             when 22 then '二十二岁'
             else '二十三'
         end ) age,t.birthday
       from z_casewhen t;
       
--方式二：case  when 条件  then 结果
select (
       case
         when t.age = 20 then '二十'
         when t.age = 21 then '二十一'
         when t.age = 22 then '二十二'
         else '二十三'
       end
)from z_casewhen t;


--注意点：1、when...then 结尾不能加分号；2、不能少了end结束


drop table z_casewhen;
