/*
This file should contain a copy of all the scripts in this folder 
for easy submission
*/

/*============================

        createTables.sql

==============================
*/

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

/*============================

populateSampleData.sql

==============================
*/

INSERT INTO Student (name, email)
VALUES 
    ('Neymar Jr', 'neymar2santos@gmail.com'),
    ('Kylian Mbappe', 'kmbappe@gmail.com'),
    ('Keanu Reeves', 'kreeves@movie.com'),
    ('Ronald McDonald', 'mascot@happymeal.com');

INSERT INTO tutor (name, email, phone, description)
VALUES 
    ('Lionel Messi', 'lmessi10@gmail.com', '9258532934', 'Computer Science tutor with 5 years of experience.'),
    ('Cristiano Ronaldo','cr7@gmail.com','4156782345','I tutor in mathematics and physics. Always eager to help!'),
    ('Tony Stark', 'tony@starkindustries.com', '5102452452', 'You know who I am.'),
    ('Steve Rodgers', 'steveshield@hotmail.com', '4879512364', 'Happy to help in any way I can.');

INSERT INTO CoursesSupported (cs_tutor_id, course_name, acronym)
VALUES 
    (1, 'Computer Architecture', 'CSE 140'),
    (1, 'Intro to Programming', 'CSE 150'),
    (1, 'Vector Calculus', 'MATH 023'),
    (1, 'Database Management', 'CSE 111'),
    (1, 'Software Engineering', 'CSE 120'),
    (2, 'Vector Calculus', 'MATH 023'),
    (2, 'Linear Algebra', 'MATH 024'),
    (2, 'Intro to Programming', 'CSE 150'),
    (2, 'Intro to Physics', 'PHYS 008'),
    (3, 'Molecular Electronic Structure', 'CHEM 225'),
    (3, 'Cognitive Science of Emotions', 'COGS 278'),
    (3, 'Intro to Computer Vision', 'CSE 185'),
    (3, 'Software Engineering', 'CSE 120'),
    (3, 'Advanced Operating Systems', 'EECS 251'),
    (4, 'Advanced Shakespeare', 'ENG 151'),
    (4, 'Design I', 'GASP 013A'),
    (4, 'Drawing I', 'GASP 010'),
    (4, 'Drawing II', 'GASP 110');

INSERT INTO Reviews (r_tutor_id, r_student_id, rating, comment)
VALUES 
    (1, 2, 9.8, 'Great tutor! Definitely recommend!'),
    (1, 1, 3.2, 'Feel more confused now than when I walked in.'),
    (2, 1, 8.4, 'Helped me visualize the concept I was having trouble grasping.'),
    (2, 3, 9.0, 'Very good tutor. Very breathtaking!'),
    (3, 4, 9.9, 'He really is the smartest guy you will ever meet'),
    (3, 1, 9.0, 'Really helped me out with my homework!'),
    (3, 2, 8.0, 'Great tutor! Loved the technology he brought to help me understand.');

INSERT INTO Semester (academic_year, sem_name, start_date, end_date)
VALUES 
    (2020, 'F', '2020-08-31', '2020-12-15'),
    (2020, 'S', '2021-01-01', '2020-05-20'),
    (2021, 'SU', '2021-06-12', '2020-08-20');
INSERT INTO Availability (a_semester_id, a_tutor_id, day_of_week, start_time, end_time)
VALUES 
    (1, 1, 'S', '17:00:00', '20:30:00'),
    (2, 2, 'F', '12:30:00', '18:30:00'),
    (1, 3, 'M', '08:00:00', '10:00:00'),
    (1, 3, 'F', '08:00:00', '10:00:00'),
    (2, 4, 'Tu', '10:00:00', '12:30:00');

--Insert availability and use delete if any tutor wants to get rid of any

INSERT INTO Appointment (app_student_id, app_tutor_id, appointment_date, start_time, end_time, comment)
VALUES 
    (2, 2, '2020-09-23', '12:30:00', '13:30:00', 'Need Help with comp sci.'),
    (1, 2, '2021-02-15', '18:30:00', '19:30:00', null),
    (4, 3, '2020-10-24', '12:00:00', '13:00:00', 'Really need some help making my mechatronic'),
    (3, 3, '2020-09-12', '14:00:00', '15:00:00', 'Please help me build my AI'),
    (3, 3, '2020-09-14', '14:00:00', '15:00:00', 'I am almost done with my AI. Just need a little more help'),
    (1, 4, '2021-03-14', '09:00:00', '10:00:00', 'Can you help me write my essay?'),
    (2, 4, '2021-02-09', '12:00:00', '01:00:00', 'Need some help with WW1 essay');

/*============================

useCaseQueries.sql

==============================
*/

--#1: Get tutor ID for search purposes
SELECT tutor_id
FROM Tutor
WHERE email LIKE ?;

--#2: Get Student ID for search purposes
SELECT student_id
FROM Student
WHERE email LIKE ?

--#3: Get availability for a tutor
SELECT day_of_week, start_time, end_time
FROM Availability
WHERE tutor_id 
    IN (SELECT tutor_id
        FROM Tutor
        WHERE email LIKE ?);

--#4: Search for all available tutors
SELECT name, description
FROM Tutor

--#5: View all reviews on a tutor (this example is for tutor "Lionel Messi")
SELECT S.name,T.name,rating, comment
FROM Reviews, Student S, Tutor T
WHERE tutor_id = r_tutor_id AND r_student_id = student_id
    AND T.name LIKE 'Lionel Messi';

--#6: Student can view the availability of selected tutor
-- This example is for tutor "Cristiano Ronaldo"
SELECT T.name, day_of_week, start_time, end_time
FROM Availability, Tutor T
WHERE a_tutor_id = tutor_id
    AND T.name LIKE 'Cristiano Ronaldo';

--#7: Find all appointments schedule for student "Neymar Jr"
SELECT S.name, T.name, appointment_date, start_time, end_time, comment
FROM Appointment, Student S, Tutor T
WHERE app_student_id = student_id AND app_tutor_id = tutor_id
    AND S.name LIKE 'Neymar Jr';

--#8: Tutor can also see their own appointments 
SELECT T.name, S.name, appointment_date, start_time, end_time, comment
FROM Appointment, Student S, Tutor T
WHERE app_student_id = student_id AND app_tutor_id = tutor_id
    AND T.name LIKE 'Cristiano Ronaldo';

--#9: Update tutor profile (email, phone, description) for Lionel Messi
UPDATE Tutor
SET
    email = '10messi@gmail.com',
    phone = '9251234567',
    description = 'I teach Computer Science!'
WHERE name LIKE 'Lionel Messi';

--#10:??
SELECT *
FROM
    (SELECT student_id
FROM Student
WHERE email LIKE 'neymar2santos@gmail.com') A,
    (SELECT a_tutor_id, day_of_week, start_time, end_time
FROM Availability
WHERE a_tutor_id 
    IN (SELECT tutor_id
        FROM Tutor
        WHERE email LIKE 'cr7@gmail.com')) B;

--#11: Request Appointment
INSERT INTO Appointment (app_student_id, app_tutor_id, appointment_date, start_time, end_time, comment)
VALUES (2, 3, '2020-09-01' , '12:00:00', '12:30:00', 'Mr. Stark, I really some help with my databases homework!');

--#12: Tony quits his job
delete from Tutor 
where tutor_id = 
    (select tutor_id 
    from Tutor 
    where name = 'Tony Stark');
delete from Reviews 
where r_tutor_id = 
    (select tutor_id 
    from Tutor 
    where name = 'Tony Stark');
delete from CoursesSupported 
where cs_tutor_id =
    (select tutor_id 
    from Tutor 
    where name = 'Tony Stark');
delete from Availability 
where a_tutor_id = 
    (select tutor_id 
    from Tutor 
    where name = 'Tony Stark');

--#13: Make account
insert into Tutor (name, email, phone, description)
values ('Thor', 'godofthunder@godmail.com', null, 'I can help with many things');
insert into CoursesSupported (cs_tutor_id, course_name, acronym)
values 
(5, 'Human Origins', 'ANTH 160'),
(5, 'Bioarchaeology', 'ANTH 179'),
(5, 'Meaning in Music', 'GASP 075A'),
(5, 'History Capstone', 'HIST 191');
insert into Availability (a_semester_id, a_tutor_id, day_of_week, start_time, end_time)
values (2, 5, 'W', '15:30:00', '17:00:00');

--#14: Cancel appointment, cancel all of Tony Stark's appointment
delete from Appointment 
where app_tutor_id = 
    (select tutor_id 
    from Tutor
    where name = 'Tony Stark');

--#15: Reschedule appointment, move Tony's appointment to next day
update Appointment 
set 
    appointment_date = '2020-09-13'
where appointment_date = '2020-09-12'
    and app_tutor_id = '3';

--#16: Edit review
update Reviews 
set 
    rating = 8.5,
    comment = 'I take that back! He is actually a really good tutor. I just did not study'
where r_student_id = 1 
    and r_tutor_id = 1
    and rating = 3.2;

--#17: Tutor can view own reviews
select Student.name, rating, Reviews.comment
from Reviews, Tutor, Student 
where r_tutor_id = tutor_id 
    and r_student_id = student_id
    and Tutor.name = 'Tony Stark';

--#18: Student can view reviews that they wrote
select Tutor.name, rating, Reviews.comment
from Reviews, Tutor, Student 
where r_tutor_id = tutor_id 
    and r_student_id = student_id 
    and Student.name = 'Neymar Jr';

--#19: Delete reviews, all of a student's reviews
delete from Reviews 
where r_student_id =
    (select student_id 
    from student
    where name = 'Kylian Mbappe');

--#20: Delete student account 
delete from Appointment 
where app_student_id = 
    (select student_id 
    from student
    where name = 'Kylian Mbappe');
delete from Reviews 
where r_student_id = 
    (select student_id 
    from student
    where name = 'Kylian Mbappe');
delete from Student 
where student_id = 
    (select student_id 
    from student
    where name = 'Kylian Mbappe');
--TODO: Write some more complex queries like group by, union, etc