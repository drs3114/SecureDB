# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table department (
  id                            number(19) not null,
  name                          varchar2(255),
  employer_id                   number(19),
  constraint uq_department_employer_id unique (employer_id),
  constraint pk_department primary key (id)
);
create sequence DEPARTMENT_seq;

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
  department_id                 number(19) not null,
  first_name                    varchar2(255),
  last_name                     varchar2(255),
  email                         varchar2(255),
  dob                           date,
  works_for_id                  number(19),
  constraint uq_employee_works_for_id unique (works_for_id),
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

alter table department add constraint fk_department_employer_id foreign key (employer_id) references employer (id);

alter table dependent add constraint fk_dependent_dependent_to_id foreign key (dependent_to_id) references employee (id);

alter table employee add constraint fk_employee_department_id foreign key (department_id) references department (id);
create index ix_employee_department_id on employee (department_id);

alter table employee add constraint fk_employee_works_for_id foreign key (works_for_id) references employer (id);


# --- !Downs

alter table department drop constraint if exists fk_department_employer_id;

alter table dependent drop constraint if exists fk_dependent_dependent_to_id;

alter table employee drop constraint if exists fk_employee_department_id;
drop index if exists ix_employee_department_id;

alter table employee drop constraint if exists fk_employee_works_for_id;

drop table department cascade constraints purge;
drop sequence DEPARTMENT_seq;

drop table dependent cascade constraints purge;
drop sequence DEPENDENT_seq;

drop table employee cascade constraints purge;
drop sequence EMPLOYEE_seq;

drop table employer cascade constraints purge;
drop sequence EMPLOYER_seq;

