create table USER_DETAILS(
user_id NUMBER(5) primary key,
username varchar2(10) not null,
firstname varchar2(25) not null,
lastname varchar2(25) not null,
password varchar2(10) not null,
emailId varchar2(50) not null,
birthdate date not null,
gender char(1) not null,
role varchar2(20) not null,
profile varchar2(50) not null,
status varchar2(25) default 'PENDING' not null,
is_online NUMBER(1) default '0' not null,
enabled NUMBER(1) default '1' not null
);

create sequence USER_SEQ
START WITH 1
INCREMENT BY 1
NOCACHE
NOCYCLE;


create table blog(
blog_id NUMBER(5) primary key,
blog_name varchar2(50) not null,
status varchar2(25) default 'PENDING' not null,
description CLOB not null,
post_date Date default sysDate,
no_of_likes NUMBER(5),
no_of_comments NUMBER(5),
no_of_views NUMBER(5),
user_id NUMBER(5) not null,
user_name varchar2(10) not null
);

create sequence BLOG_SEQ
START WITH 1
INCREMENT BY 1
NOCACHE
NOCYCLE;

create table BLOG_COMMENT (
BLOG_COMMENT_ID NUMBER(5) primary key,
BLOG_ID NUMBER(5) NOT NULL,
COMMENT_DATE Date default sysDate,
user_id NUMBER(5) not null,
user_name varchar2(10) not null,
User_ProfileId varchar2(50) not null,
title varchar2(50) not null,
BLOG_COMMENT CLOB NOT NULL
)

CREATE sequence BLOG_COMMENT_SEQ
START WITH 1
INCREMENT BY 1
NOCACHE
NOCYCLE;

CREATE TABLE FORUM (
FORUM_ID NUMBER(5) PRIMARY KEY,
FORUM_NAME VARCHAR2(50) NOT NULL,
DESCRIPTION CLOB NOT NULL,
status varchar2(25) default 'PENDING' not null,
NUMBER_OF_POSTS NUMBER(5),
post_date Date default sysDate,
user_id NUMBER(5) not null,
user_name varchar2(10) not null
);

CREATE SEQUENCE FORUM_SEQ
START WITH 1
INCREMENT BY 1
NOCACHE
NOCYCLE

CREATE TABLE FORUM_POSTS(
POST_ID NUMBER(5) PRIMARY KEY,
FORUM_ID NUMBER(5) NOT NULL,
user_id NUMBER(5) not null,
user_name varchar2(10) not null,
User_ProfileId varchar2(50) not null,
title  varchar2(50) not null,
POST_CONTAINT CLOB NOT NULL,
POST_DATE DATE DEFAULT SYSDATE
);

CREATE SEQUENCE FORUM_POST_SEQ
START WITH 1
INCREMENT BY 1
NOCACHE
NOCYCLE

create table forum_request (
request_id NUMBER(5) PRIMARY KEY,
user_id NUMBER(5) not null,
user_name varchar2(10) not null,
FORUM_ID NUMBER(5),
STATUS VARCHAR2(25) default 'PENDING' not null
);

CREATE SEQUENCE FORUM_REQ_SEQ 
START WITH 1
INCREMENT BY 1
NOCACHE
NOCYCLE

CREATE TABLE JOB_DETAIL (
JOB_ID NUMBER(5) PRIMARY KEY,
COMPANY_NAME VARCHAR2(50) NOT NULL,
SUB_TITLE VARCHAR2(100) NOT NULL,
ABOUT VARCHAR2(800) NOT NULL,
JOB_PROFILE VARCHAR2(500) NOT NULL,
QUALIFICATION VARCHAR2(500) NOT NULL,
CONTACT_INFO VARCHAR2(1000) NOT NULL,
STATUS VARCHAR2(25) default 'PENDING' not null,
POST_DATE DATE DEFAULT SYSDATE,
user_id NUMBER(5) not null,
user_name varchar2(10) not null
)

CREATE SEQUENCE JOB_SEQ
START WITH 1
INCREMENT BY 1
NOCACHE
NOCYCLE

CREATE TABLE JOB_APPLIED (
APPLIED_ID NUMBER(5) PRIMARY KEY,
JOB_ID NUMBER(5) NOT NULL,
user_id NUMBER(5) not null,
user_name varchar2(10) not null,
APPLIED_DATE DATE DEFAULT SYSDATE,
STATUS VARCHAR2(25) default 'PENDING' not null
);

CREATE SEQUENCE JOB_APP_SEQ
START WITH 1
INCREMENT BY 1
NOCACHE
NOCYCLE

CREATE TABLE EVENTS (
EVENT_ID NUMBER(5) PRIMARY KEY,
user_id NUMBER(5) not null,
user_name varchar2(10) not null,
NAME VARCHAR2(25) not null,
VENUE VARCHAR2(100) NOT NULL,
DESCRIPTION CLOB NOT NULL,
STATUS VARCHAR2(25) default 'PENDING' not null,
START_DATE DATE NOT NULL,
END_DATE DATE NOT NULL,
POST_DATE DATE DEFAULT SYSDATE NOT NULL
)

CREATE SEQUENCE EVENTS_SEQ 
START WITH 1
INCREMENT BY 1
NOCACHE
NOCYCLE

CREATE TABLE EVENT_JOINED (
JOINED_ID NUMBER(5) PRIMARY KEY,
EVENT_ID NUMBER(5) NOT NULL,
user_id NUMBER(5) not null,
user_name varchar2(10) not null,
JOINED_DATE DATE DEFAULT SYSDATE,
STATUS VARCHAR2(25) default 'PENDING' not null
);

create sequence EVENT_JOINED_SEQ 
START WITH 1
INCREMENT BY 1
NOCACHE
NOCYCLE

CREATE TABLE FRIENDS (
ID NUMBER(5) PRIMARY KEY,
INITIATOR_ID NUMBER(5) NOT NULL,
FRIEND_ID NUMBER(5) NOT NULL,
STATUS VARCHAR2(25) default 'PENDING' not null
)

create sequence FRIENDS_SEQ
START WITH 1
INCREMENT BY 1
NOCACHE
NOCYCLE