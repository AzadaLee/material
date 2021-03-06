--创建存储过程（不带参数的）
create or replace procedure p_first
       is
       begin
         dbms_output.put_line('first procedure.');
       end;
       
--调用无参存储过程（在命令窗口调用）
begin
     p_first;
end;
--或者
execute p_first;

--查看存储过程的错误信息（命令窗口）
show errors procedure

--查看存储过程信息
select * from user_procedures where Object_name = 'P_FIRST';

select * from user_objects where object_name='P_FIRST';

drop procedure p_first;

