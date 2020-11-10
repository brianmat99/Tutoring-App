DROP TABLE Reviews;
DROP TABLE CoursesSupported;
DROP TABLE Semester;
DROP TABLE Availability;
DROP TABLE Appointment;
DROP TABLE Student;
DROP TABLE Tutor;


/*Do not use ; in comments. It fails to distinguish the difference*/

CREATE TABLE Reviews (
    review_id INTEGER PRIMARY KEY AUTOINCREMENT, -- Maybe should be integer?
    r_tutor_id INTEGER not null,
    r_student_id INTEGER not null,
    --should reviews have a student id?
    rating decimal(3,2) not null, -- 3 digits total with 2 after decimal i.e. #.##
    comment varchar(500)
);

create table CoursesSupported (
    course_id INTEGER PRIMARY KEY AUTOINCREMENT,    -- In case we get a lot of courses
    cs_tutor_id INTEGER not null,   -- Just in case long acronym
    course_name varchar(100) not null,
    acronym varchar(10) not null     -- just in case long course name
);

create table Semester ( 
    semester_id INTEGER PRIMARY KEY AUTOINCREMENT, -- Not super sure the type of this, need to discuss with Brian
    academic_year decimal(4, 0) not null,
    sem_name varchar(2) not null,   -- Fall (F), Spring (S), Summer (SU)
    start_date date not null,   -- I think this is the correct type
    end_date date not null 
);

create table Availability (
    availability_id INTEGER PRIMARY KEY AUTOINCREMENT,
    a_semester_id INTEGER not null,
    a_tutor_id INTEGER not null,
    day_of_week varchar(2) not null,    -- Su, M, Tu, W, Th, F, Sa How do we store multiple days?
    start_time time not null,   -- Reference: https://www.sqlitetutorial.net/sqlite-date/
    end_time time not null 
);

create table Appointment ( 
    appointment_id INTEGER PRIMARY KEY AUTOINCREMENT,
    app_student_id INTEGER not null,
    app_tutor_id INTEGER not null,
    appointment_date date not null,
    -- description varchar(200),
    start_time time not null,
    end_time time not null,
    comment varchar(200)    -- Might not need to this because of description or vice versa
);
    
create table Student (
    student_id INTEGER PRIMARY KEY AUTOINCREMENT,
    name varchar(100) not null,  -- In case someone has many middle names
    email varchar(100) not null 
);

create table Tutor (
    tutor_id INTEGER PRIMARY KEY AUTOINCREMENT,
    name varchar(100) not null,
    email varchar(100) not null,
    phone varchar(20),
    description varchar(500)
);
