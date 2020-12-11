import java.sql.*;

public class Back {
    private Connection c = null;
    private String dbName;
    private boolean isConnected = false;

    public void openConnection(String dbName) {
        this.dbName = dbName;

        if(!isConnected) {
            try {
                String connStr = new String("jdbc:sqlite:");
                connStr = connStr + dbName;

                Class.forName("org.sqlite.JDBC");
                c = DriverManager.getConnection(connStr);

                c.setAutoCommit(false);

                isConnected = true;

            } catch(Exception e) {
                System.err.println(e.getClass().getName() + ": " + e.getMessage());
                System.exit(0);
            }
        }
    }

    public void closeConnection() {
        if(isConnected) {
            try {
                c.close();

                isConnected = false;
                dbName = "";
            } catch(Exception e) {
                System.err.println(e.getClass().getName() + ": " + e.getMessage());
            }
        }
    }

    // TODO: Create functions for each use case. Will probably be using PreparedStatements a lot
    
    // returns num of tutors who support input course and days
    public int getNumOfTutorsByCourse(String co, String[] d) {
        try {
            if(d.length == 1) {
                String sql = "select distinct count(tutor_id) " +
                    "from Tutor, CoursesSupported, Availability " +
                    "where tutor_id = cs_tutor_id " +
                        "and tutor_id = a_tutor_id " +
                        "and course_name = ? " +
                        "and day_of_week = ? ";
                PreparedStatement stmt = c.prepareStatement(sql);
                stmt.setString(1, co);
                stmt.setString(2, d[0]);

                ResultSet rs = stmt.executeQuery();
                int num = rs.getInt(1);
                
                return num;
            }
            if(d.length == 2) {
                String sql = "select distinct count(tutor_id) " +
                    "from Tutor, CoursesSupported, Availability " +
                    "where tutor_id = cs_tutor_id " +
                        "and tutor_id = a_tutor_id " +
                        "and course_name = ? " +
                        "and (day_of_week = ? or day_of_week = ?) ";
                PreparedStatement stmt = c.prepareStatement(sql);
                stmt.setString(1, co);
                stmt.setString(2, d[0]);
                stmt.setString(3, d[1]);

                ResultSet rs = stmt.executeQuery();
                int num = rs.getInt(1);
                
                return num;
            }
            if(d.length == 3) {
                String sql = "select distinct count(tutor_id) " +
                    "from Tutor, CoursesSupported, Availability " +
                    "where tutor_id = cs_tutor_id " +
                        "and tutor_id = a_tutor_id " +
                        "and course_name = ? " +
                        "and (day_of_week = ? or day_of_week = ? or day_of_week = ?) ";
                PreparedStatement stmt = c.prepareStatement(sql);
                stmt.setString(1, co);
                stmt.setString(2, d[0]);
                stmt.setString(3, d[1]);
                stmt.setString(4, d[2]);

                ResultSet rs = stmt.executeQuery();
                int num = rs.getInt(1);
                
                return num;
            }
            if(d.length == 4) {
                String sql = "select distinct count(tutor_id) " +
                    "from Tutor, CoursesSupported, Availability " +
                    "where tutor_id = cs_tutor_id " +
                        "and tutor_id = a_tutor_id " +
                        "and course_name = ? " +
                        "and (day_of_week = ? or day_of_week = ? or day_of_week = ? or day_of_week = ?) ";
                PreparedStatement stmt = c.prepareStatement(sql);
                stmt.setString(1, co);
                stmt.setString(2, d[0]);
                stmt.setString(3, d[1]);
                stmt.setString(4, d[2]);
                stmt.setString(5, d[3]);

                ResultSet rs = stmt.executeQuery();
                int num = rs.getInt(1);
                
                return num;
            }
            if(d.length == 5) {
                String sql = "select distinct count(tutor_id) " +
                    "from Tutor, CoursesSupported, Availability " +
                    "where tutor_id = cs_tutor_id " +
                        "and tutor_id = a_tutor_id " +
                        "and course_name = ? " +
                        "and (day_of_week = ? or day_of_week = ? or day_of_week = ? or day_of_week = ? or day_of_week = ?) ";
                PreparedStatement stmt = c.prepareStatement(sql);
                stmt.setString(1, co);
                stmt.setString(2, d[0]);
                stmt.setString(3, d[1]);
                stmt.setString(4, d[2]);
                stmt.setString(5, d[3]);
                stmt.setString(6, d[4]);

                ResultSet rs = stmt.executeQuery();
                int num = rs.getInt(1);
                
                return num;
            }
            if(d.length == 6) {
                String sql = "select distinct count(tutor_id) " +
                    "from Tutor, CoursesSupported, Availability " +
                    "where tutor_id = cs_tutor_id " +
                        "and tutor_id = a_tutor_id " +
                        "and course_name = ? " +
                        "and (day_of_week = ? or day_of_week = ? or day_of_week = ? or day_of_week = ? or day_of_week = ? or day_of_week = ?) ";
                PreparedStatement stmt = c.prepareStatement(sql);
                stmt.setString(1, co);
                stmt.setString(2, d[0]);
                stmt.setString(3, d[1]);
                stmt.setString(4, d[2]);
                stmt.setString(5, d[3]);
                stmt.setString(6, d[4]);
                stmt.setString(7, d[5]);

                ResultSet rs = stmt.executeQuery();
                int num = rs.getInt(1);
                
                return num;
            }
            if(d.length == 7) {
                String sql = "select distinct count(tutor_id) " +
                    "from Tutor, CoursesSupported, Availability " +
                    "where tutor_id = cs_tutor_id " +
                        "and tutor_id = a_tutor_id " +
                        "and course_name = ? " +
                        "and (day_of_week = ? or day_of_week = ? or day_of_week = ? or day_of_week = ? or day_of_week = ? or day_of_week = ? or day_of_week = ?) ";
                PreparedStatement stmt = c.prepareStatement(sql);
                stmt.setString(1, co);
                stmt.setString(2, d[0]);
                stmt.setString(3, d[1]);
                stmt.setString(4, d[2]);
                stmt.setString(5, d[3]);
                stmt.setString(6, d[4]);
                stmt.setString(7, d[5]);
                stmt.setString(8, d[6]);

                ResultSet rs = stmt.executeQuery();
                int num = rs.getInt(1);

                stmt.close();
                
                return num;
            }
            
        } catch(Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }

        return -1;  // Error
    }
    
    // returns tutors who support input course and days
    public String[][] getTutorsByCourse(String co, String[] d) {
        String[][] failure = new String[123][2];
        try {
            int num = getNumOfTutorsByCourse(co, d);
            String[][] tutors = new String[num][2];

            if(d.length == 1) {
                String sql = "select distinct Tutor.name, day_of_week " +
                    "from Tutor, CoursesSupported, Availability " +
                    "where tutor_id = cs_tutor_id " +
                        "and tutor_id = a_tutor_id " +
                        "and course_name = ? " +
                        "and day_of_week = ? ";
                PreparedStatement stmt = c.prepareStatement(sql);
                stmt.setString(1, co);
                stmt.setString(2, d[0]);

                ResultSet rs = stmt.executeQuery();
                int i = 0;
                while(rs.next()) {
                    tutors[i][0] = rs.getString(1);
                    tutors[i][1] = rs.getString(2);
                    i++;
                }
                
                return tutors;
            }
            if(d.length == 2) {
                String sql = "select distinct Tutor.name, day_of_week " +
                    "from Tutor, CoursesSupported, Availability " +
                    "where tutor_id = cs_tutor_id " +
                        "and tutor_id = a_tutor_id " +
                        "and course_name = ? " +
                        "and (day_of_week = ? or day_of_week = ?) ";
                PreparedStatement stmt = c.prepareStatement(sql);
                stmt.setString(1, co);
                stmt.setString(2, d[0]);
                stmt.setString(3, d[1]);

                ResultSet rs = stmt.executeQuery();
                int i = 0;
                while(rs.next()) {
                    tutors[i][0] = rs.getString(1);
                    tutors[i][1] = rs.getString(2);
                    i++;
                }
                
                return tutors;
            }
            if(d.length == 3) {
                String sql = "select distinct Tutor.name, day_of_week " +
                    "from Tutor, CoursesSupported, Availability " +
                    "where tutor_id = cs_tutor_id " +
                        "and tutor_id = a_tutor_id " +
                        "and course_name = ? " +
                        "and (day_of_week = ? or day_of_week = ? or day_of_week = ?) ";
                PreparedStatement stmt = c.prepareStatement(sql);
                stmt.setString(1, co);
                stmt.setString(2, d[0]);
                stmt.setString(3, d[1]);
                stmt.setString(4, d[2]);

                ResultSet rs = stmt.executeQuery();
                int i = 0;
                while(rs.next()) {
                    tutors[i][0] = rs.getString(1);
                    tutors[i][1] = rs.getString(2);
                    i++;
                }
                
                return tutors;
            }
            if(d.length == 4) {
                String sql = "select distinct Tutor.name, day_of_week " +
                    "from Tutor, CoursesSupported, Availability " +
                    "where tutor_id = cs_tutor_id " +
                        "and tutor_id = a_tutor_id " +
                        "and course_name = ? " +
                        "and (day_of_week = ? or day_of_week = ? or day_of_week = ? or day_of_week = ?) ";
                PreparedStatement stmt = c.prepareStatement(sql);
                stmt.setString(1, co);
                stmt.setString(2, d[0]);
                stmt.setString(3, d[1]);
                stmt.setString(4, d[2]);
                stmt.setString(5, d[3]);

                ResultSet rs = stmt.executeQuery();
                int i = 0;
                while(rs.next()) {
                    tutors[i][0] = rs.getString(1);
                    tutors[i][1] = rs.getString(2);
                    i++;
                }
                
                return tutors;
            }
            if(d.length == 5) {
                String sql = "select distinct Tutor.name, day_of_week " +
                    "from Tutor, CoursesSupported, Availability " +
                    "where tutor_id = cs_tutor_id " +
                        "and tutor_id = a_tutor_id " +
                        "and course_name = ? " +
                        "and (day_of_week = ? or day_of_week = ? or day_of_week = ? or day_of_week = ? or day_of_week = ?) ";
                PreparedStatement stmt = c.prepareStatement(sql);
                stmt.setString(1, co);
                stmt.setString(2, d[0]);
                stmt.setString(3, d[1]);
                stmt.setString(4, d[2]);
                stmt.setString(5, d[3]);
                stmt.setString(6, d[4]);

                ResultSet rs = stmt.executeQuery();
                int i = 0;
                while(rs.next()) {
                    tutors[i][0] = rs.getString(1);
                    tutors[i][1] = rs.getString(2);
                    i++;
                }
                
                return tutors;
            }
            if(d.length == 6) {
                String sql = "select distinct Tutor.name, day_of_week " +
                    "from Tutor, CoursesSupported, Availability " +
                    "where tutor_id = cs_tutor_id " +
                        "and tutor_id = a_tutor_id " +
                        "and course_name = ? " +
                        "and (day_of_week = ? or day_of_week = ? or day_of_week = ? or day_of_week = ? or day_of_week = ? or day_of_week = ?) ";
                PreparedStatement stmt = c.prepareStatement(sql);
                stmt.setString(1, co);
                stmt.setString(2, d[0]);
                stmt.setString(3, d[1]);
                stmt.setString(4, d[2]);
                stmt.setString(5, d[3]);
                stmt.setString(6, d[4]);
                stmt.setString(7, d[5]);

                ResultSet rs = stmt.executeQuery();
                int i = 0;
                while(rs.next()) {
                    tutors[i][0] = rs.getString(1);
                    tutors[i][1] = rs.getString(2);
                    i++;
                }
                
                return tutors;
            }
            if(d.length == 7) {
                String sql = "select distinct Tutor.name, day_of_week " +
                    "from Tutor, CoursesSupported, Availability " +
                    "where tutor_id = cs_tutor_id " +
                        "and tutor_id = a_tutor_id " +
                        "and course_name = ? " +
                        "and (day_of_week = ? or day_of_week = ? or day_of_week = ? or day_of_week = ? or day_of_week = ? or day_of_week = ? or day_of_week = ?) ";
                PreparedStatement stmt = c.prepareStatement(sql);
                stmt.setString(1, co);
                stmt.setString(2, d[0]);
                stmt.setString(3, d[1]);
                stmt.setString(4, d[2]);
                stmt.setString(5, d[3]);
                stmt.setString(6, d[4]);
                stmt.setString(7, d[5]);
                stmt.setString(8, d[6]);

                ResultSet rs = stmt.executeQuery();
                int i = 0;
                while(rs.next()) {
                    tutors[i][0] = rs.getString(1);
                    tutors[i][1] = rs.getString(2);
                    i++;
                }
                
                stmt.close();
                return tutors;
            }
            
        } catch(Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }

        return failure;  // Error
    }

    // returns num of all tutors
    public int getTutorsCount() {
        try {
            Statement stmt = c.createStatement();
            String sql = "select count(tutor_id) as c " +
                "from Tutor ";

            ResultSet rs = stmt.executeQuery(sql);

            int count = rs.getInt("c");

            stmt.close();
            return count;

        } catch(Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return -1;  // Return -1 for error, should not get here...
    }

    // returns all tutor names 
    public String[] getTutorNames() {
        String[] failure = new String[1234];
        try {
            int numOfTutors = getTutorsCount();
            String[] tutors = new String[numOfTutors];

            Statement stmt = c.createStatement();
            String sql = "select name as n " +
                "from Tutor ";

            ResultSet rs = stmt.executeQuery(sql);

            int i = 0;
            while(rs.next()) {
                String name = rs.getString(1);
                tutors[i] = name;
                i++;
            }

            stmt.close();
            return tutors;

        } catch(Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }

        return failure; // Should not reach here...
    }

    // returns num of all courses 
    public int getCoursesCount() {
        try {
            
            Statement stmt = c.createStatement();
            String sql = "select count(course_name) as c " +
                "from CoursesSupported ";

            ResultSet rs = stmt.executeQuery(sql);

            int count = rs.getInt("c");

            stmt.close();
            return count;

        } catch(Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }

        return -1;  // Error if we get here
    }

    // returns all course names 
    public String[] getCourseNames() {
        String[] failure = new String[1234];
        try {
            int courseCount = getCoursesCount();
            String[] courses = new String[courseCount];

            Statement stmt = c.createStatement();
            String sql = "select course_name " + 
                "from CoursesSupported ";

            ResultSet rs = stmt.executeQuery(sql);

            int i = 0;
            while(rs.next()) {
                courses[i] = rs.getString(1);
                i++;
            }
            stmt.close();
            return courses;

        } catch(Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }

        return failure; // If return this then function failed
    }

    public int getTutorNumAvail(String t) {
        try {
            String sql = "select count(*) as c " +
                "from Availability, Tutor " +
                "where a_tutor_id = tutor_id " +
                    "and name = ? ";
            PreparedStatement stmt = c.prepareStatement(sql);
            stmt.setString(1, t);

            ResultSet rs = stmt.executeQuery();

            int num = rs.getInt(1);

            return num;

        } catch(Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }

        return -1;  // Failure
    }
    
    public String[][] getTutorAvail(String t) {
        String[][] failure = new String[1234][1];
        try {
            int num = getTutorNumAvail(t);
            String[][] tutorAvail = new String[num][3];
            String sql = "select day_of_week, start_time, end_time " +
                "from Availability, Tutor " +
                "where a_tutor_id = tutor_id " +
                    "and name = ? ";
            PreparedStatement stmt = c.prepareStatement(sql);
            stmt.setString(1, t);

            ResultSet rs = stmt.executeQuery();

            int i = 0;
            while(rs.next()) {
                tutorAvail[i][0] = rs.getString(1);
                tutorAvail[i][1] = rs.getString(2);
                tutorAvail[i][2] = rs.getString(3);
                i++;
            }

            return tutorAvail;

        } catch(Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }

        return failure; // Failure
    }

    public int getNumOfTutorReviews(String t) {
        try {
            String sql = "select count(*) " +
                "from Reviews, Tutor " +
                "where r_tutor_id = tutor_id " +
                    "and name = ? ";
            PreparedStatement stmt = c.prepareStatement(sql);
            stmt.setString(1, t);

            ResultSet rs = stmt.executeQuery();

            int num = rs.getInt(1);

            stmt.close();
            return num;

        } catch(Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }

        return -1;  // Error
    }

    public String[][] getTutorReviews(String t) {
        String[][] failure = new String[1234][1];
        try {
            int numOfReviews = getNumOfTutorReviews(t);
            String[][] reviews = new String[numOfReviews][2];
            String sql = "select comment, rating " +
                "from Reviews, Tutor " +
                "where r_tutor_id = tutor_id " +
                    "and name = ? ";
            PreparedStatement stmt = c.prepareStatement(sql);
            stmt.setString(1, t);

            ResultSet rs = stmt.executeQuery();

            int i = 0;
            while(rs.next()) {
                reviews[i][0] = rs.getString(1);
                reviews[i][1] = rs.getString(2);
                i++;
            }

            stmt.close();
            return reviews;

        } catch(Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }

        return failure;  // Error
    }
    
    public String[][] getAppointments(String student){
        String[][] failure = new String[1][1];
        try {
            int numAppointments = getNumOfAppointments(student);
            String[][] appointments = new String[numAppointments][6];
            

            
            String sql = "SELECT A.appointment_id, A.appointment_date, T.name as tutor_name, A.start_time, A.end_time, A.description " +
                        "FROM Appointment A, Student S, Tutor T " +
                        "WHERE app_student_id = student_id AND app_tutor_id = tutor_id " +
                            "AND S.email LIKE ? ";
            PreparedStatement stmt = c.prepareStatement(sql);

            stmt.setString(1, student);
            ResultSet rs = stmt.executeQuery();

            int row = 0;
            while(rs.next()){
                for (int i = 0; i < 6; i++) {
                    appointments[row][i] = rs.getString(i+1);
                }
                row++;
            }
            stmt.close();
            return appointments;

        } catch(Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }

        return failure; // If return this then function failed
    }

    public int getNumOfAppointments(String student){
        try {
            String sql = "select count(*) " +
                    "FROM Student, Appointment " +
                    "WHERE student_id = app_student_id " +
                        "and Student.email LIKE ? ";
            PreparedStatement stmt = c.prepareStatement(sql);
            stmt.setString(1, student);

            ResultSet rs = stmt.executeQuery();

            int num = rs.getInt(1);
            stmt.close();
            rs.close();
            return num;

        } catch(Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }

        return -1;  // Error
    }

    public String[] getAppointmentInfo(int appointmentID){
        String[] failure = new String[10];
        try {
            
            String[] appointmentInfo = new String[3];
            String sql = "SELECT appointment_date, start_time, description " +
                    "FROM Appointment " +
                    "WHERE appointment_id = ? ";

            PreparedStatement stmt = c.prepareStatement(sql);
            stmt.setInt(1, appointmentID);

            ResultSet rs = stmt.executeQuery();

            
            while(rs.next()){
                appointmentInfo[0] = rs.getString(1);
                appointmentInfo[1] = rs.getString(2);
                appointmentInfo[2] = rs.getString(3);
            }

            stmt.close();
            rs.close();
            return appointmentInfo;

        } catch(Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return failure;
    }

    public void updateAppointmentDate(String newDate, int appointment_id){
        try {
            String sql = "UPDATE Appointment  " + 
                        " SET appointment_date = ? " +
                        " WHERE appointment_id = ? ";
            PreparedStatement stmt = c.prepareStatement(sql);
            stmt.setString(1, newDate);
            stmt.setInt(2, appointment_id);

            stmt.executeUpdate();
            c.commit();

            stmt.close();

        } catch(Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }

    public void updateAppointmentStartTime(String newTime, int appointment_id){
        try {
            String sql = "UPDATE Appointment  " + 
                        " SET start_time = ? " +
                        " WHERE appointment_id = ? ";
            PreparedStatement stmt = c.prepareStatement(sql);
            stmt.setString(1, newTime);
            stmt.setInt(2, appointment_id);

            stmt.executeUpdate();
            c.commit();

            stmt.close();

        } catch(Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }

    public void updateAppointmentDescription(String newDescription, int appointment_id) {
        try {
            String sql = "UPDATE Appointment  " + 
                        " SET description = ? " +
                        " WHERE appointment_id = ? ";
            PreparedStatement stmt = c.prepareStatement(sql);
            stmt.setString(1, newDescription);
            stmt.setInt(2, appointment_id);

            stmt.executeUpdate();
            c.commit();

            stmt.close();

        } catch(Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }

    // assume that the user always enters an existing user
    public String getStudentByEmail(String email){
        String failure = null;
        try {
            String fetchedEmail = null;
            String sql = "SELECT email " + 
                        " FROM Student " +
                        " WHERE email = ? ";
            PreparedStatement stmt = c.prepareStatement(sql);
            stmt.setString(1, email);

            ResultSet rs = stmt.executeQuery();
            c.commit();

            if (rs.next()){
                fetchedEmail = rs.getString(1); 
            }

            stmt.close();

            return fetchedEmail;

        } catch(Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return failure;
    }

    public String getTutorByEmail(String email){
        String failure = null;
        try {
            String fetchedEmail = null;
            String sql = "SELECT email " + 
                        " FROM Tutor " +
                        " WHERE email = ? ";
            PreparedStatement stmt = c.prepareStatement(sql);
            stmt.setString(1, email);

            ResultSet rs = stmt.executeQuery();
            c.commit();

            if (rs.next()){
                fetchedEmail = rs.getString(1); 
            }

            stmt.close();

            return fetchedEmail;

        } catch(Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return failure;
    }

    public void insertNewStudent(String name, String email) {
        try {
            String sql = "INSERT INTO Student (name, email) " + 
                        " VALUES (?,?)";

            PreparedStatement stmt = c.prepareStatement(sql);
            stmt.setString(1, name);
            stmt.setString(2, email);

            stmt.executeUpdate();
            c.commit();

            stmt.close();

        } catch(Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }

    public void insertNewTutor (String name, String email, String phone, String description){
        try {
            String sql = "INSERT INTO Tutor (name, email, phone, description) " + 
                        " VALUES (?,?,?,?)";

            PreparedStatement stmt = c.prepareStatement(sql);
            stmt.setString(1, name);
            stmt.setString(2, email);
            stmt.setString(3, phone);
            stmt.setString(4, description);

            stmt.executeUpdate();
            c.commit();

            stmt.close();

        } catch(Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }

    public int getStudentIDFromEmail(String se) {
        try {
            String sql = "select student_id " +
                "from Student " +
                "where email = ? ";
            PreparedStatement stmt = c.prepareStatement(sql);
            stmt.setString(1, se);

            ResultSet rs = stmt.executeQuery();

            int studentID = rs.getInt(1);

            return studentID;

        } catch(Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }

        return -1;  // Error
    }

    public int getTutorIDFromName(String tn) {
        try {
            String sql = "select tutor_id " +
                "from Tutor " +
                "where name = ? ";
            PreparedStatement stmt = c.prepareStatement(sql);
            stmt.setString(1, tn);

            ResultSet rs = stmt.executeQuery();

            int tutorID = rs.getInt(1);

            return tutorID;

        } catch(Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }

        return -1;  // Error
    }

    public void insertAppointment(int sID, int tID, String appt, String sTime, String eTime, String comment) {
        try {
            String sql = "insert into Appointment (app_student_id, app_tutor_id, appointment_date, start_time, end_time, description, accepted) " +
                "values( ?, ?, ?, ?, ?, ?, 0)";
            PreparedStatement stmt = c.prepareStatement(sql);
            stmt.setInt(1, sID);
            stmt.setInt(2, tID);
            stmt.setString(3, appt);
            stmt.setString(4, sTime);
            stmt.setString(5, eTime);
            stmt.setString(6, comment);

            stmt.executeUpdate();
            c.commit();

        } catch(Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }
}