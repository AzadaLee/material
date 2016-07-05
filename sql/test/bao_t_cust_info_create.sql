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
  is 'BAO�ͻ���Ϣ��';
-- Add comments to the columns 
comment on column BAO_T_CUST_INFO.id
  is '���� ID  UUID';
comment on column BAO_T_CUST_INFO.login_name
  is '��½���� �û���/EMIAL/�ֻ�';
comment on column BAO_T_CUST_INFO.login_password
  is '��¼����, ����';
comment on column BAO_T_CUST_INFO.trade_password
  is '��������';
comment on column BAO_T_CUST_INFO.credentials_type
  is '֤������';
comment on column BAO_T_CUST_INFO.credentials_code
  is '֤������';
comment on column BAO_T_CUST_INFO.cust_name
  is '�ͻ�����';
comment on column BAO_T_CUST_INFO.cust_code
  is '12λ������λǰ����㣬��ʼֵ��100000000000';
comment on column BAO_T_CUST_INFO.birthday
  is '�������� yyyyMMdd';
comment on column BAO_T_CUST_INFO.cust_level
  is '�ͻ�����';
comment on column BAO_T_CUST_INFO.safe_level
  is '��ȫ����';
comment on column BAO_T_CUST_INFO.cust_gender
  is '�ͻ��Ա�';
comment on column BAO_T_CUST_INFO.cust_source
  is '�ֻ��ˡ�PC�ˡ��ⲿ�ͻ�';
comment on column BAO_T_CUST_INFO.cust_type
  is '��ƿͻ�\�Ŵ��ͻ�';
comment on column BAO_T_CUST_INFO.natvice_place_province
  is '����ʡ��';
comment on column BAO_T_CUST_INFO.natvice_place_city
  is '�������';
comment on column BAO_T_CUST_INFO.natvice_place_county
  is '�����س�';
comment on column BAO_T_CUST_INFO.natvice_place_area
  is '��������';
comment on column BAO_T_CUST_INFO.commun_address
  is 'ͨ�ŵ�ַ';
comment on column BAO_T_CUST_INFO.mobile
  is '�ֻ� ,����������¼';
comment on column BAO_T_CUST_INFO.email
  is '����';
comment on column BAO_T_CUST_INFO.portrait_path
  is 'ͷ��·��';
comment on column BAO_T_CUST_INFO.real_name_auth_count
  is 'ʵ����֤���� ,�������';
comment on column BAO_T_CUST_INFO.is_lumper
  is '��/��';
comment on column BAO_T_CUST_INFO.msg_on_off
  is '��/��';
comment on column BAO_T_CUST_INFO.enable_status
  is '�Ƿ�����';
comment on column BAO_T_CUST_INFO.record_status
  is '��¼״̬';
comment on column BAO_T_CUST_INFO.create_user
  is '������Ա';
comment on column BAO_T_CUST_INFO.create_date
  is '����ʱ��';
comment on column BAO_T_CUST_INFO.last_update_user
  is '��������Ա';
comment on column BAO_T_CUST_INFO.last_update_date
  is '������ʱ��';
comment on column BAO_T_CUST_INFO.version
  is '�汾��';
comment on column BAO_T_CUST_INFO.memo
  is '��ע';
comment on column BAO_T_CUST_INFO.invite_code
  is '������';
comment on column BAO_T_CUST_INFO.integral
  is '����';
comment on column BAO_T_CUST_INFO.qr_code_path
  is '��ά��·��';
comment on column BAO_T_CUST_INFO.invite_origin_id
  is '������ԴID';
comment on column BAO_T_CUST_INFO.query_permission
  is '�鿴Ȩ��';
comment on column BAO_T_CUST_INFO.spread_level
  is '�ȼ�';
comment on column BAO_T_CUST_INFO.login_pwd_level
  is '��½���뼶�� -���е�';
comment on column BAO_T_CUST_INFO.trade_pwd_level
  is '�������뼶�� -���е�';
comment on column BAO_T_CUST_INFO.channel_source
  is '������Դ';
comment on column BAO_T_CUST_INFO.zip_code
  is '�ʱ�';
comment on column BAO_T_CUST_INFO.qq_code
  is 'QQ���';
comment on column BAO_T_CUST_INFO.is_recommend
  is '�Ƿ�����Ƽ���';
comment on column BAO_T_CUST_INFO.is_employee
  is '�Ƿ��ڲ�Ա��';
comment on column BAO_T_CUST_INFO.memo2
  is '��ע2';
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
