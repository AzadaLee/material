
select length('abcdefg') from dual;
--substr 注意：index是从1开始计数的并且可以为负数，如果为负数，代表从后倒数
--substr(source,index,legth)  substr('acbdefg',0,2)结果为ab：意思为从index为开始截取，截取length位    
select substr('abcdefg',-1,1)from dual;

--连接 concat(),||：注意concat只能有两个入参
select concat('hello','world') ,'你好'||'世界' from dual

--字符串搜索instr(source,'要搜索的字符[串]')：从1开始计数，不在目标中，返回0
--如果有三个参数，第三个参数为负数，代表从右边开始查找，但最终的计数还是从左边计数
--如果有四个参数，最后一个参数代表是第几次出现
select instr('abcdefgb','b',-1,2) from dual;

--字符串填充
--rpad('老字符串',追加完以后字符串的长度,'新追加的字符串')
select rpad('1234',18,'abcdefgb') from dual;--将字符串'abcdefgb'追加到字符串'1234'的右边，追加完以后从左至右截取8位，若是追加完以后不足截取长度，则将追加的字符串再次追加
--用途：将某个值追加0，使长度达到18位
select rpad('1234',15,0) from dual;


--日起函数
select to_char(sysdate,'yy-mm-dd hh24:mi') from dual;
select to_date('2016-07-11 14:56','yy-mm-dd hh24:mi')from dual;

--获取时区
select dbtimezone from dual;
--获取前一个或后一个月
select to_char(add_months(sysdate,1),'yyyy-mm-dd'),to_char(add_months(sysdate,-2),'yyyy-mm-dd') from dual;
--返回某月的最后一天
select to_char(last_day(sysdate),'yyyy-mm-dd') now,to_char(last_day(add_months(sysdate,-1)),'yyyy-mm-dd') pre from dual;

--字符串转成二进制
select asciistr('测试') from dual;

--字符串转成数字
select to_number('12345.678','999999999.99999999') from dual;
--返回登录名
select user from dual;
