--建表语句
create table t_procuctinfo(
       c_pid varchar2(50) primary key,
       c_pname varchar2(300),
       c_pprice number(20,8),
       c_pquantity number(10),
       c_pcategory varchar2(100),
       c_pdesperation varchar2(300),
       c_porigin varchar2(300)
)

--插入数据
insert into t_procuctinfo(c_pid,c_pname,c_pprice,c_pquantity,c_pcategory,c_pdesperation,c_porigin) 
            values(sys_guid(),'哇哈哈AD钙奶',12.58,6,'饮料','适合10岁以下孩子','杭州');
            
insert into t_procuctinfo(c_pid,c_pname,c_pprice,c_pquantity,c_pcategory,c_pdesperation,c_porigin,memo) 
            values(sys_guid(),'景田百岁山',4.58,6,'饮料','我们是大自然的搬运工','南京','n');

select * from t_procuctinfo for update;


--修改表名
alter table t_product rename to t_procuctinfo;

comment on table t_procuctinfo is '产品信息表';
comment on column t_procuctinfo.c_pid is '主键id';
comment on column t_procuctinfo.c_pname is '产品名称';
comment on column t_procuctinfo.c_pprice is '产品价格';
comment on column t_procuctinfo.c_pquantity is '产品数量';
comment on column t_procuctinfo.c_pcategory is '产品类型';
comment on column t_procuctinfo.c_pdesperation is '产品描述';
comment on column t_procuctinfo.c_porigin is '产品产地';

--添加列(不需要加column进行修饰)
alter table t_procuctinfo add memo varchar2(200);

--修改列明
alter table t_procuctinfo rename column memo to rename_memo;

--修改列属性（不需要加column进行修饰）
alter table t_procuctinfo modify rename_memo number(5);

--删除列（需要加column进行修饰，否则会报“缺少关键字异常”）
alter table t_procuctinfo drop column rename_memo;


--约束：primary key,foreign key,check,unique添加方法如下
--添加约束
alter table t_procuctinfo add constraints pmemo_check check(memo in('f','m') );

--删除约束
alter table t_procuctinfo drop constraints pmemo_check;

--约束not null添加方法如下，删除时还是使用modify进行修改
alter table t_procuctinfo modify c_pname not null;

--禁用约束
alter table t_procuctinfo disable constraint pmemo_check;
--启用约束（会检查在禁用约束期间插入的数据是否合法，如果不合法就会报错）
alter table t_procuctinfo enable constraint pmemo_check;

--删除表
drop table t_procuctinfo;
