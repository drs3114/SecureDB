# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table employee (
  id                            number(19) not null,
  name                          varchar2(255),
  constraint pk_employee primary key (id)
);
create sequence EMPLOYEE_seq;

create table employer (
  id                            number(19) not null,
  name                          varchar2(255),
  constraint pk_employer primary key (id)
);
create sequence EMPLOYER_seq;


# --- !Downs

drop table employee cascade constraints purge;
drop sequence EMPLOYEE_seq;

drop table employer cascade constraints purge;
drop sequence EMPLOYER_seq;

