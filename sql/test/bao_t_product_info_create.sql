-- Create table
create table BAO_T_PRODUCT_INFO
(
  id                     VARCHAR2(50) not null,
  product_type           VARCHAR2(50) not null,
  product_name           VARCHAR2(150),
  product_status         VARCHAR2(50),
  income_type            VARCHAR2(100),
  income_handle_method   VARCHAR2(50),
  type_term              INTEGER,
  seat_term              INTEGER,
  invest_min_amount      NUMBER(22,8),
  invest_max_amount      NUMBER(22,8),
  plan_total_amount      NUMBER(22,8),
  product_end_time       DATE,
  ensure_method          VARCHAR2(50),
  lucre_into_method      VARCHAR2(50),
  invest_bearinte_method VARCHAR2(50),
  grant_rule             VARCHAR2(500),
  product_desc           VARCHAR2(2000),
  service_charge_rate    NUMBER(22,18),
  recharge_rate          NUMBER(22,18),
  quit_rate              NUMBER(22,18),
  enable_status          VARCHAR2(50),
  record_status          VARCHAR2(50),
  create_user            VARCHAR2(50),
  create_date            DATE not null,
  last_update_user       VARCHAR2(50),
  last_update_date       DATE,
  version                INTEGER,
  memo                   VARCHAR2(300),
  increase_amount        NUMBER(22,8),
  product_code           VARCHAR2(50),
  favorite_sort          VARCHAR2(50)
)
tablespace BAO
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
