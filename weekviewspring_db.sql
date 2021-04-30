--drops user and db to run script multiple times.
drop database weekviewdb;
drop user weekview;

-- Creates a user and database for weekly view
create user weekview with password 'password';
create database weekviewdb with template=template0 owner =weekview;

-- connects to weekly view db.
\connect weekviewdb;

-- adds privileges to new user for tables and sequences.
alter default privileges grant all on tables to weekview;
alter default privileges grant all on sequences to weekview;


-- creates users table
create table wv_users(
user_id integer primary key not null,
first_name varchar(20) not null,
last_name varchar(20) not null,
email varchar(30) not null,
password text not null
);

-- creates calendars table
create table wv_calendars(
calendar_id integer primary key not null,
user_id integer not null,
cal_title varchar(20) not null,
description varchar(50) not null,
wake_time float not null,
sleep_time float not null
);

-- foreign key creation for calendar_id
alter table wv_calendars add constraint cat_users_fk
foreign key (user_id) references wv_users(user_id);

-- creates tasks table
create table wv_tasks(
task_id integer primary key not null,
calendar_id integer not null,
task_title varchar(30) not null,
description varchar(30) not null,
week_day integer not null,
wake_time float not null,
sleep_time float not null,
text_color text,
background_color text
);

-- foreign key creation for tasks table
alter table wv_tasks add constraint tasks_cat_fk
foreign key (calendar_id) references wv_calendars(calendar_id);


-- Sequences

create sequence wv_users_seq increment 1 start 1;
create sequence wv_calendars_seq increment 1 start 1;
create sequence wv_tasks_seq increment 1 start 1;