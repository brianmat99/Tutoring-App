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

    public int getNumOfTutorsByCourse(String co) {
        try {
            String sql = "select count(*) " +
                "from Tutor, CoursesSupported " +
                "where tutor_id = cs_tutor_id " +
                    "and course_name = ? ";
            PreparedStatement stmt = c.prepareStatement(sql);
            stmt.setString(1, co);

            ResultSet rs = stmt.executeQuery();

            int num = rs.getInt(1);
            
            return num;

        } catch(Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }

        return -1;  // Error
    }

    public String[] getTutorsByCourse(String co) {
        String[] failure = new String[1234];
        try {
            int numOfTutors = getNumOfTutorsByCourse(co);
            String[] tutors = new String[numOfTutors];

            String sql = "select Tutor.name " +
                "from Tutor, CoursesSupported " +
                "where tutor_id = cs_tutor_id " +
                    "and course_name = ? ";
            PreparedStatement stmt = c.prepareStatement(sql);
            stmt.setString(1, co);

            ResultSet rs = stmt.executeQuery();

            int i = 0;
            while(rs.next()) {
                tutors[i] = rs.getString(1);
                i++;
            }

            return tutors;

        } catch(Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }

        return failure;  // Error
    }

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
    
}