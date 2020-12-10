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
                String sql = "select count(distinct tutor_id) " +
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
                String sql = "select count(distinct tutor_id) " +
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
                String sql = "select count(distinct tutor_id) " +
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
                String sql = "select count(distinct tutor_id) " +
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
                String sql = "select count(distinct tutor_id) " +
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
                String sql = "select count(distinct tutor_id) " +
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
                String sql = "select count(distinct tutor_id) " +
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
                
                return num;
            }
            
        } catch(Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }

        return -1;  // Error
    }
    
    // returns tutors who support input course and days
    public String[] getTutorsByCourse(String co, String[] d) {
        String[] failure = new String[123];
        try {
            int num = getNumOfTutorsByCourse(co, d);
            String[] tutors = new String[num];

            if(d.length == 1) {
                String sql = "select distinct Tutor.name " +
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
                    tutors[i] = rs.getString(1);
                    i++;
                }
                
                return tutors;
            }
            if(d.length == 2) {
                String sql = "select distinct Tutor.name " +
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
                    tutors[i] = rs.getString(1);
                    i++;
                }
                
                return tutors;
            }
            if(d.length == 3) {
                String sql = "select distinct Tutor.name " +
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
                    tutors[i] = rs.getString(1);
                    i++;
                }
                
                return tutors;
            }
            if(d.length == 4) {
                String sql = "select distinct Tutor.name " +
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
                    tutors[i] = rs.getString(1);
                    i++;
                }
                
                return tutors;
            }
            if(d.length == 5) {
                String sql = "select distinct Tutor.name " +
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
                    tutors[i] = rs.getString(1);
                    i++;
                }
                
                return tutors;
            }
            if(d.length == 6) {
                String sql = "select distinct Tutor.name " +
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
                    tutors[i] = rs.getString(1);
                    i++;
                }
                
                return tutors;
            }
            if(d.length == 7) {
                String sql = "select distinct Tutor.name " +
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
                    tutors[i] = rs.getString(1);
                    i++;
                }
                
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

            return courses;

        } catch(Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }

        return failure; // If return this then function failed
    }

    /*
    public int getNumOfTutorReviews(int t) {
        try {
            String sql = "select count(*) " +
                "from Reviews " +
                "where r_tutor_id = ? ";
            PreparedStatement stmt = c.prepareStatement(sql);
            stmt.setInt(1, t);

            ResultSet rs = stmt.executeQuery();

            int num = rs.getInt(1);

            return num;

        } catch(Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }

        return -1;  // Error
    }

    public String[] getTutorReviews(int t) {
        String[] failure = new String[1234];
        try {
            int numOfReviews = getNumOfTutorReviews(t);
            String[] reviews = new String[numOfReviews];
            String sql = "select comment " +
                "from Reviews " +
                "where r_tutor_id = ? ";
            PreparedStatement stmt = c.prepareStatement(sql);
            stmt.setInt(1, t);

            ResultSet rs = stmt.executeQuery();

            int i = 0;
            while(rs.next()) {
                
                i++;
            }

            return ;

        } catch(Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }

        return failure;  // Error
    }
    */
}