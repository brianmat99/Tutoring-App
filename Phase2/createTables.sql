drop table Reviews;
drop table CoursesSupported;
drop table Semester;
drop table Availability;
drop table Appointment;
drop table Student;
drop table Student_Appointments;
drop table Tutor_CoursesSupported;

create table Reviews (
    review_id decimal(4, 0) not null, -- Maybe should be integer?
    rating decimal(3, 2) not null,   -- 3 digits total with 2 after decimal i.e. #.##
    comment varchar(20)
);

create table CoursesSupported (
    course_id decimal(4, 0) not null,    -- In case we get a lot of courses
    acronym varchar(10) not null,   -- Just in case long acronym
    course_name varchar(30) not null    -- just in case long course name
);

create table Semester ( 
    semester_id decimal(4, 0) not null, -- Not super sure the type of this, need to discuss with Brian
    academic_year decimal(4, 0) not null,
    sem_name varchar(2) not null,   -- Fall (F), Spring (S), Summer (SU)
    start_date date not null,   -- I think this is the correct type
    end_date date not null 
);

create table Availability (
    availability_id decimal(4, 0) not null,
    day_of_week varchar(2) not null,    -- Su, M, Tu, W, Th, F, Sa How do we store multiple days?
    start_time text not null,   -- Reference: https://www.sqlitetutorial.net/sqlite-date/
    end_time text not null,
    semester_id decimal(4, 0) not null 
);

create table Appointment ( 
    appointment_id decimal(4, 0) not null, 
    datee date not null,
    description varchar(200),
    start_time text not null,
    end_time text not null,
    comment varchar(200)    -- Might not need to this because of description or vice versa
);
    
create table Student (
    student_id decimal(4, 0) not null,
    name varchar(100) not null,  -- In case someone has many middle names
    email varchar(100) not null 
);

-- Many to many tables
create table Student_Appointments {
    student_id decimal(4, 0) not null,
    appointment_id decimal(4, 0) not null
);

create table Tutor_CoursesSupported {
    course_id decimal(4, 0) not null,
    --- TODO: Add attributes to Tutor table!
);
