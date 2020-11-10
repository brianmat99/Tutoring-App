/*
Should be at least 20 SQL statements (queries and data modification statements
(INSERT, UPDATE, DELETE)
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
INSERT INTO Appointment (app_student_id,app_tutor_id,appointment_date,start_time,end_time,comment)


