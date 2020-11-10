/*
This file should contain a copy of all the scripts in this folder 
for easy submission
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