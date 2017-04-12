# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table department (
  id                            number(19) not null,
  name                          varchar2(255),
  constraint pk_department primary key (id)
);
create sequence DEPARTMENT_seq;

create table department_employee (
  department_id                 number(19) not null,
  employee_id                   number(19) not null,
  constraint pk_department_employee primary key (department_id,employee_id)
);

create table dependent (
  id                            number(19) not null,
  name                          varchar2(255),
  dependent_to_id               number(19),
  relationship                  varchar2(255),
  constraint uq_dependent_dependent_to_id unique (dependent_to_id),
  constraint pk_dependent primary key (id)
);
create sequence DEPENDENT_seq;

create table employee (
  id                            number(19) not null,
  first_name                    varchar2(255),
  last_name                     varchar2(255),
  username                      varchar2(255),
  email                         varchar2(255),
  dob                           date,
  constraint uq_employee_email unique (email),
  constraint pk_employee primary key (id)
);
create sequence EMPLOYEE_seq;

create table employer (
  id                            number(19) not null,
  name                          varchar2(255),
  address                       varchar2(255),
  no_of_employees               number(10),
  constraint pk_employer primary key (id)
);
create sequence EMPLOYER_seq;

alter table department_employee add constraint fk_dprtmnt_mply_dprtmnt foreign key (department_id) references department (id);
create index ix_dprtmnt_mply_dprtmnt on department_employee (department_id);

alter table department_employee add constraint fk_dprtmnt_mply_mply foreign key (employee_id) references employee (id);
create index ix_dprtmnt_mply_mply on department_employee (employee_id);

alter table dependent add constraint fk_dependent_dependent_to_id foreign key (dependent_to_id) references employee (id);


# --- !Downs

alter table department_employee drop constraint if exists fk_dprtmnt_mply_dprtmnt;
drop index if exists ix_dprtmnt_mply_dprtmnt;

alter table department_employee drop constraint if exists fk_dprtmnt_mply_mply;
drop index if exists ix_dprtmnt_mply_mply;

alter table dependent drop constraint if exists fk_dependent_dependent_to_id;

drop table department cascade constraints purge;
drop sequence DEPARTMENT_seq;

drop table department_employee cascade constraints purge;

drop table dependent cascade constraints purge;
drop sequence DEPENDENT_seq;

drop table employee cascade constraints purge;
drop sequence EMPLOYEE_seq;

drop table employer cascade constraints purge;
drop sequence EMPLOYER_seq;

