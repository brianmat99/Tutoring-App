INSERT INTO Student (name, email)
VALUES 
    ('Neymar Jr', 'neymar2santos@gmail.com'),
    ('Kylian Mbappe', 'kmbappe@gmail.com');

INSERT INTO tutor (name, email, phone, description)
VALUES 
    ('Lionel Messi', 'lmessi10@gmail.com', '9258532934', 'Computer Science tutor with 5 years of experience.'),
    ('Cristiano Ronaldo','cr7@gmail.com','4156782345','I tutor in mathematics and physics. Always eager to help!');

INSERT INTO CoursesSupported (cs_tutor_id, course_name, acronym)
VALUES 
    (1, 'Computer Architecture', 'CSE 140'),
    (1, 'Intro to Programming', 'CSE 150'),
    (2, 'Intro to Programming', 'CSE 150'),
    (1, 'Database Management', 'CSE 111'),
    (1, 'Software Engineering', 'CSE 120'),
    (2, 'Vector Calculus', 'MATH 023'),
    (2, 'Linear Algebra', 'MATH 024'),
    (1, 'Vector Calculus', 'MATH 023'),
    (2, 'Intro to Physics', 'PHYS 008');

INSERT INTO Reviews (r_tutor_id, r_student_id, rating, comment)
VALUES 
    (1, 2, 9.8, 'Great tutor! Definitely recommend!'),
    (1, 1, 3.2, 'Feel more confused now than when I walked in.'),
    (2, 1, 8.4, 'Helped me visualize the concept I was having trouble grasping.');

INSERT INTO Semester (academic_year, sem_name, start_date, end_date)
VALUES 
    (2020, 'F', '2020-08-31', '2020-12-15'),
    (2020, 'S', '2021-01-01', '2020-05-20'),
    (2021, 'SU', '2021-06-12', '2020-08-20');
INSERT INTO Availability (a_semester_id, a_tutor_id, day_of_week, start_time, end_time)
VALUES 
    (1, 1, 'S', '17:00:00', '20:30:00'),
    (2, 2, 'F', '12:30:00', '18:30:00');

--Insert availability and use delete if any tutor wants to get rid of any

INSERT INTO Appointment (app_student_id, app_tutor_id, appointment_date, start_time, end_time, comment)
VALUES 
    (2,2,'2020-09-23','12:30:00','13:30:00','Need Help with comp sci.'),
    (1,2,'2021-02-15','18:30:00','19:30:00',null); 