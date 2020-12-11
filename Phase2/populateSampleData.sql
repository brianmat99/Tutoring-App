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
    ('Steve Rodgers', 'steveshield@hotmail.com', '4879512364', 'Happy to help in any way I can.'),
    ('Thor Odinson', 'thor@godmail.com', '1231231234', 'Want to hear about how I killed Thanos?'),
    ('Natasha Romanoff', 'nat@hotmail.com', '4515786512', 'I know a lot about history.'),
    ('Florin Rusu', 'frusu@ucmerced.edu', '1235638625', 'Computer science professor at UC Merced.');

INSERT INTO CoursesSupported (cs_tutor_id, course_name, acronym)
VALUES 
    (1, 'Computer Architecture', 'CSE 140'),
    (1, 'Operating System', 'CSE 150'),
    (1, 'Vector Calculus', 'MATH 023'),
    (1, 'Database Management', 'CSE 111'),
    (1, 'Software Engineering', 'CSE 120'),
    (2, 'Vector Calculus', 'MATH 023'),
    (2, 'Linear Algebra', 'MATH 024'),
    (2, 'Operating Systems', 'CSE 150'),
    (2, 'Intro to Physics', 'PHYS 008'),
    (3, 'Advanced Programming', 'CSE 024'),
    (3, 'Drawing I', 'GASP 010'),
    (3, 'Molecular Electronic Structure', 'CHEM 225'),
    (3, 'Cognitive Science of Emotions', 'COGS 278'),
    (3, 'Intro to Computer Vision', 'CSE 185'),
    (3, 'Software Engineering', 'CSE 120'),
    (3, 'Advanced Operating Systems', 'EECS 251'),
    (4, 'Advanced Shakespeare', 'ENG 151'),
    (4, 'Design I', 'GASP 013A'),
    (4, 'Drawing I', 'GASP 010'),
    (4, 'Drawing II', 'GASP 110'),
    (5, 'Human Origins', 'ANTH 160'),
    (5, 'Religion and Healing', 'ANTH 122'),
    (5, 'Nutrition', 'BIO 060'),
    (6, 'History of Things', 'HIST 051'),
    (6, 'Britain, 1500-1750', 'HIST 179'),
    (6, 'Intro to Physics', 'PHYS 008'),
    (6, 'Database Management', 'CSE 111'),
    (6, 'Operating Systems', 'CSE 150'),
    (6, 'Advanced Programming', 'CSE 024')
    (7, 'Intro Computer Applications', 'CSE 005'),
    (7, 'Intro Computer Applications', 'CSE 005');
    


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
    (1, 1, 'Sun', '17:00:00', '20:30:00'),
    (2, 2, 'Fri', '12:30:00', '18:30:00'),
    (1, 3, 'Mon', '08:00:00', '10:00:00'),
    (1, 3, 'Fri', '08:00:00', '10:00:00'),
    (2, 4, 'Tue', '10:00:00', '12:30:00'),
    (2, 4, 'Sat', '06:00:00', '12:00:00'),
    (1, 5, 'Mon', '13:00:00', '15:00:00'),
    (1, 5, 'Fri', '13:00:00', '15:00:00'),
    (2, 6, 'Wed', '06:00:00', '09:30:00'),
    (2, 6, 'Sat', '06:00:00', '10:00:00'),
    (2, 6, 'Mon', '06:00:00', '09:30:00'),


--Insert availability and use delete if any tutor wants to get rid of any

INSERT INTO Appointment (app_student_id, app_tutor_id, appointment_date, start_time, end_time, description, accepted)
VALUES 
    (2, 2, '2020-09-23', '12:30:00', '13:30:00', 'Need Help with comp sci.', 0),
    (1, 2, '2021-02-15', '18:30:00', '19:30:00', 'Need help for final.', 1),
    (4, 3, '2020-10-24', '12:00:00', '13:00:00', 'Really need some help making my mechatronic', 0),
    (3, 3, '2020-09-12', '14:00:00', '15:00:00', 'Please help me build my AI', 0),
    (3, 3, '2020-09-14', '14:00:00', '15:00:00', 'I am almost done with my AI. Just need a little more help', 1),
    (1, 4, '2021-03-14', '09:00:00', '10:00:00', 'Can you help me write my essay?', 0),
    (2, 4, '2021-02-09', '12:00:00', '01:00:00', 'Need some help with WW1 essay', 1);