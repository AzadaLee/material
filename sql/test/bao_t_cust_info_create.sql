-- Create table
create table BAO_T_CUST_INFO
(
  id                     VARCHAR2(50) not null,
  login_name             VARCHAR2(150),
  login_password         VARCHAR2(50),
  trade_password         VARCHAR2(50),
  credentials_type       VARCHAR2(50),
  credentials_code       VARCHAR2(50),
  cust_name              VARCHAR2(150),
  cust_code              VARCHAR2(50),
  birthday               VARCHAR2(8),
  cust_level             VARCHAR2(50),
  safe_level             VARCHAR2(50),
  cust_gender            VARCHAR2(8),
  cust_source            VARCHAR2(150),
  cust_type              VARCHAR2(150),
  natvice_place_province VARCHAR2(150),
  natvice_place_city     VARCHAR2(150),
  natvice_place_county   VARCHAR2(150),
  natvice_place_area     VARCHAR2(150),
  commun_address         VARCHAR2(2000),
  mobile                 VARCHAR2(50),
  email                  VARCHAR2(50),
  portrait_path          VARCHAR2(255),
  real_name_auth_count   INTEGER,
  is_lumper              VARCHAR2(50),
  msg_on_off             VARCHAR2(50),
  enable_status          VARCHAR2(50),
  record_status          VARCHAR2(50),
  create_user            VARCHAR2(50),
  create_date            DATE not null,
  last_update_user       VARCHAR2(50),
  last_update_date       DATE,
  version                INTEGER,
  memo                   VARCHAR2(300),
  invite_code            VARCHAR2(50),
  integral               INTEGER,
  qr_code_path           VARCHAR2(1000),
  invite_origin_id       VARCHAR2(50),
  query_permission       VARCHAR2(3000),
  spread_level           VARCHAR2(300),
  login_pwd_level        VARCHAR2(50),
  trade_pwd_level        VARCHAR2(50),
  channel_source         VARCHAR2(300),
  zip_code               VARCHAR2(50),
  qq_code                VARCHAR2(50),
  is_recommend           VARCHAR2(50),
  is_employee            VARCHAR2(50),
  cust_kind              VARCHAR2(150),
  tel                    VARCHAR2(150),
  memo2                  VARCHAR2(300)
)
tablespace BAO
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 8K
    minextents 1
    maxextents unlimited
  );
-- Add comments to the table 
comment on table BAO_T_CUST_INFO
  is 'BAO客户信息表';
-- Add comments to the columns 
comment on column BAO_T_CUST_INFO.id
  is '主键 ID  UUID';
comment on column BAO_T_CUST_INFO.login_name
  is '登陆名称 用户名/EMIAL/手机';
comment on column BAO_T_CUST_INFO.login_password
  is '登录密码, 加密';
comment on column BAO_T_CUST_INFO.trade_password
  is '交易密码';
comment on column BAO_T_CUST_INFO.credentials_type
  is '证件类型';
comment on column BAO_T_CUST_INFO.credentials_code
  is '证件号码';
comment on column BAO_T_CUST_INFO.cust_name
  is '客户姓名';
comment on column BAO_T_CUST_INFO.cust_code
  is '12位，不够位前面加零，起始值：100000000000';
comment on column BAO_T_CUST_INFO.birthday
  is '出生日期 yyyyMMdd';
comment on column BAO_T_CUST_INFO.cust_level
  is '客户级别';
comment on column BAO_T_CUST_INFO.safe_level
  is '安全级别';
comment on column BAO_T_CUST_INFO.cust_gender
  is '客户性别';
comment on column BAO_T_CUST_INFO.cust_source
  is '手机端、PC端、外部客户';
comment on column BAO_T_CUST_INFO.cust_type
  is '理财客户\信贷客户';
comment on column BAO_T_CUST_INFO.natvice_place_province
  is '籍贯省份';
comment on column BAO_T_CUST_INFO.natvice_place_city
  is '籍贯城市';
comment on column BAO_T_CUST_INFO.natvice_place_county
  is '籍贯县城';
comment on column BAO_T_CUST_INFO.natvice_place_area
  is '籍贯区域';
comment on column BAO_T_CUST_INFO.commun_address
  is '通信地址';
comment on column BAO_T_CUST_INFO.mobile
  is '手机 ,可以用来登录';
comment on column BAO_T_CUST_INFO.email
  is '邮箱';
comment on column BAO_T_CUST_INFO.portrait_path
  is '头像路径';
comment on column BAO_T_CUST_INFO.real_name_auth_count
  is '实名认证次数 ,最多三次';
comment on column BAO_T_CUST_INFO.is_lumper
  is '是/否';
comment on column BAO_T_CUST_INFO.msg_on_off
  is '开/关';
comment on column BAO_T_CUST_INFO.enable_status
  is '是否启用';
comment on column BAO_T_CUST_INFO.record_status
  is '记录状态';
comment on column BAO_T_CUST_INFO.create_user
  is '创建人员';
comment on column BAO_T_CUST_INFO.create_date
  is '创建时间';
comment on column BAO_T_CUST_INFO.last_update_user
  is '最后更新人员';
comment on column BAO_T_CUST_INFO.last_update_date
  is '最后更新时间';
comment on column BAO_T_CUST_INFO.version
  is '版本号';
comment on column BAO_T_CUST_INFO.memo
  is '备注';
comment on column BAO_T_CUST_INFO.invite_code
  is '邀请码';
comment on column BAO_T_CUST_INFO.integral
  is '积分';
comment on column BAO_T_CUST_INFO.qr_code_path
  is '二维码路径';
comment on column BAO_T_CUST_INFO.invite_origin_id
  is '邀请来源ID';
comment on column BAO_T_CUST_INFO.query_permission
  is '查看权限';
comment on column BAO_T_CUST_INFO.spread_level
  is '等级';
comment on column BAO_T_CUST_INFO.login_pwd_level
  is '登陆密码级别 -高中低';
comment on column BAO_T_CUST_INFO.trade_pwd_level
  is '交易密码级别 -高中低';
comment on column BAO_T_CUST_INFO.channel_source
  is '渠道来源';
comment on column BAO_T_CUST_INFO.zip_code
  is '邮编';
comment on column BAO_T_CUST_INFO.qq_code
  is 'QQ编号';
comment on column BAO_T_CUST_INFO.is_recommend
  is '是否金牌推荐人';
comment on column BAO_T_CUST_INFO.is_employee
  is '是否内部员工';
comment on column BAO_T_CUST_INFO.memo2
  is '备注2';
-- Create/Recreate primary, unique and foreign key constraints 
alter table BAO_T_CUST_INFO
  add constraint PK_BAO_T_CUST_INFO primary key (ID)
  using index 
  tablespace BAO
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
