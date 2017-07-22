---------------------------------------------
-- Export file for user SCOTT              --
-- Created by Think on 2017/7/22, 16:16:19 --
---------------------------------------------


create table SYS_LOGIN_LOG
(
  id            NUMBER not null,
  userid        VARCHAR2(100),
  logintime     DATE,
  loginip       VARCHAR2(50),
  ipinfocountry VARCHAR2(50),
  ipinforegion  VARCHAR2(30),
  ipinfocity    VARCHAR2(30),
  ipinfoisp     VARCHAR2(20),
  logintype     NUMBER,
  logindesc     VARCHAR2(50)
)
;
create index IDX_LOGIN_TIMESYS_LOGIN_LOG on SYS_LOGIN_LOG (LOGINTIME);
create index IDX_SYS_LOGIN_LOG_IBFK_1SYS_LO on SYS_LOGIN_LOG (USERID);
alter table SYS_LOGIN_LOG
  add constraint PRIMARYSYS_LOGIN_LOG1 primary key (ID);


create table SYS_PERMISSION
(
  id         NUMBER not null,
  pid        NUMBER,
  name       VARCHAR2(50),
  url        VARCHAR2(255),
  sort       NUMBER,
  ismenu     NUMBER(1),
  isenable   NUMBER(1),
  icon       VARCHAR2(100),
  permission VARCHAR2(200)
)
;
create index IDX_P_ISENABLESYS_PERMISSION on SYS_PERMISSION (ISENABLE);
alter table SYS_PERMISSION
  add constraint PRIMARYSYS_PERMISSION1 primary key (ID);


create table SYS_RE_ROLE_PERMISSION
(
  id           NUMBER not null,
  roleid       NUMBER,
  permissionid NUMBER
)
;
create index IDX_FK_SYS_RE_R_REFERENCE_SYS_ on SYS_RE_ROLE_PERMISSION (PERMISSIONID);
alter table SYS_RE_ROLE_PERMISSION
  add constraint PRIMARYSYS_RE_ROLE_PERMISSION1 primary key (ID);


create table SYS_RE_USER_ROLE
(
  id     NUMBER not null,
  userid NUMBER,
  roleid NUMBER
)
;
create index IDX_FK_SYS_RE_U_REFERENCE_SYS_ on SYS_RE_USER_ROLE (USERID);
alter table SYS_RE_USER_ROLE
  add constraint PRIMARYSYS_RE_USER_ROLE1 primary key (ID);


create table SYS_ROLE
(
  id          NUMBER not null,
  name        VARCHAR2(50),
  code        VARCHAR2(50),
  sort        NUMBER,
  description VARCHAR2(200),
  isenable    NUMBER(1)
)
;
create index IDX_R_ISENABLESYS_ROLE on SYS_ROLE (ISENABLE);
alter table SYS_ROLE
  add constraint PRIMARYSYS_ROLE1 primary key (ID);


create table SYS_SETTINGS
(
  k VARCHAR2(50) not null,
  v NVARCHAR2(2000)
)
;
alter table SYS_SETTINGS
  add constraint PRIMARYSYS_SETTINGS1 primary key (K);


create table SYS_USER
(
  id            NUMBER not null,
  account       VARCHAR2(50),
  password      VARCHAR2(100),
  lastloginip   VARCHAR2(20),
  lastlogintime DATE,
  logincount    NUMBER,
  createtime    DATE,
  isenable      NUMBER(1)
)
;
create index IDX_U_ACCOUNTSYS_USER on SYS_USER (ACCOUNT);
create index IDX_U_ISENABLESYS_USER on SYS_USER (ISENABLE);
alter table SYS_USER
  add constraint PRIMARYSYS_USER1 primary key (ID);


create sequence SYS_LOGIN_LOG_SEQ
minvalue 1
maxvalue 9999999999999999999999999999
start with 1050
increment by 1
cache 10;


create sequence SYS_PERMISSION_SEQ
minvalue 1
maxvalue 9999999999999999999999999999
start with 1070
increment by 1
cache 10;


create sequence SYS_RE_ROLE_PERMISSION_SEQ
minvalue 1
maxvalue 9999999999999999999999999999
start with 1010
increment by 1
cache 10;


create sequence SYS_RE_USER_ROLE_SEQ
minvalue 1
maxvalue 9999999999999999999999999999
start with 1010
increment by 1
cache 10;


create sequence SYS_ROLE_SEQ
minvalue 1
maxvalue 9999999999999999999999999999
start with 1010
increment by 1
cache 10;


create sequence SYS_SETTINGS_SEQ
minvalue 1
maxvalue 9999999999999999999999999999
start with 1000
increment by 1
cache 10;

create sequence SYS_USER_SEQ
minvalue 1
maxvalue 9999999999999999999999999999
start with 1010
increment by 1
cache 10;
