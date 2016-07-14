select ? || case
         when length(COLUMN_VALUE) < 12 then
          lpad(COLUMN_VALUE, 12, '0')
         else
          to_char(COLUMN_VALUE)
       end "value"
  from table(getSequences(?, ?))
  
  
select ? || case
         when length(COLUMN_VALUE) < 12 then
          lpad(COLUMN_VALUE, 12, '0')
         else
          to_char(COLUMN_VALUE)
       end "value"
  from table(getsequences('TRADE_BATCH_NUMBER_SEQ',5))
  
  
  
  
select getsequences('TRADE_BATCH_NUMBER_SEQ',5) from dual;

select column_value from table (getsequences('TRADE_BATCH_NUMBER_SEQ',5));

select * from

create type arr_test is varray(5) of varchar2(10);

create table t_test(
       id varchar2(50) primary key,
       col arr_test
)

insert into t_test(id,col) values (sys_guid(),arr_test('1','11','111','1111'));

commit;

select * from t_test;

select column_value val from table(select t.col col from t_test t where t.id='37968AFD4B5C5F68E053CA9610C04B8A' );

[{val=x}, {val=xx}, {val=xxx}, {val=xxxx}]
