import java.util.Scanner;

public class Front {
    public static String student;
    public static void main(String[] args) {
        Back b = new Back();
        b.openConnection("Phase2/newdb.sqlite");

        int res = login();
        int funcSelected;

        System.out.println("Welcome to PeerTutors 2.0!");
        System.out.print("Please enter your name: ");
        Scanner input = new Scanner(System.in);
        student = input.nextLine();

        //TODO: verify if the name exists in the database

        System.out.println("Welcome " + student + "...💩");
        
        // if student
        if (res == 0){
            do {
                funcSelected = showOptionsStudent();
    
                if (funcSelected < 1 || funcSelected > 4) {
                    System.out.println("Error: function " + funcSelected + " does not exists.");
                }
    
                if (funcSelected == 1) {
                    createAppointment(b);
                }
    
                if (funcSelected == 2) {
                    viewAppointments();
                }
    
                if (funcSelected == 3) {
                    manageAppointment(b);
                }
    
                if (funcSelected == 4) {
                    logout();
                    break;
                }
    
            } while (true);
        }
        
        
        // if tutor
        if (res == 1){
            do {
                showOptionsTutor();
            } while (true);
        }
        b.closeConnection();
    }

    public static int login() {
        int response = 0; // 0 for student, 1 for tutor?

        return response;
    }

    public static int showOptionsStudent() {
        Scanner input = new Scanner(System.in);
        int func;
        System.out.println("\nThis program supports 4 functions:");
        System.out.println("1. Request Appointment");
        System.out.println("2. View Appointments");
        System.out.println("3. Manage Appointment");
        System.out.println("4. Logout");
        System.out.println("Please choose the function you want: ");

        func = input.nextInt();
        return func;
    }

    public static int showOptionsTutor() {
        Scanner input = new Scanner(System.in);
        System.out.println("\nPlease select a function:");
        System.out.println("1. Manage Appointment");
        System.out.println("2. Edit Profile");
        System.out.println("3. Accept/Decline");

        int func = input.nextInt();
        return func;
    }

    public static void createAppointment(Back b) {
        Scanner input = new Scanner(System.in);
        int tutorChosen;
        int courseChosen;
        String daysAvailable;

        int numOfCourses = b.getCoursesCount();
        String[] courseNames = new String[numOfCourses];
        courseNames = b.getCourseNames();

        System.out.println("\nWhat course do you need help with? ");
        for(int i = 0; i < numOfCourses; i++) {
            System.out.println((i + 1) + ". " + courseNames[i]);
        }
        courseChosen = input.nextInt() - 1;

        System.out.println("\nWhat days are you available: Su, M, Tu, W, Th, F, Sa (in comma separated list i.e. M,Tu,F,Sa)");
        daysAvailable = input.next();

        String[] daysAvailArr = daysAvailable.split(",");

        int numOfTutors = b.getNumOfTutorsByCourse(courseNames[courseChosen], daysAvailArr);
        String[] tutorNames = new String[numOfTutors];
        tutorNames = b.getTutorsByCourse(courseNames[courseChosen], daysAvailArr);
        
        while(true) {   // To allow user to look through all tutor reviews, availability, etc 
            int confirm = 2;
            System.out.println("\nPlease select a tutor: ");
            for (int i = 0; i < numOfTutors; i++) {
                System.out.println((i + 1) + ". " + tutorNames[i]);
            }
        
            tutorChosen = input.nextInt() - 1;

            int numOfReviews = b.getNumOfTutorReviews(tutorNames[tutorChosen]);
            String[] reviews = new String[numOfReviews];
            reviews = b.getTutorReviews(tutorNames[tutorChosen]);
            System.out.println("NUM: " + numOfReviews + ", " + tutorChosen);

            System.out.println("\nTutor: " + tutorNames[tutorChosen]);
            System.out.println("Reviews: ");
            for(int i = 0; i < numOfReviews; i++) {
                System.out.println((i + 1) + ". " + reviews[i]);
            }
            System.out.println("Availability: ...");
            
            System.out.println("\nConfirm choice (1/0): ");
            confirm = input.nextInt();

            if(confirm == 1) {
                break;
            }
            else {
                // continue
            }
        }
        
        System.out.println("Please enter the date requested for this session: (e.x: 01/27/2020)");
        String date = input.next();
        System.out.println("Please enter the time: (e.x: 4:30pm)");
        String time = input.next();

        //Insert into appointments 
        
    }

    private static void viewAppointments() {
        //select all appointments belonging to current student
    }

    private static void manageAppointment(Back b) {

        int appointmentID = selectAppointment(b);
    

        do {
            //edit appointment (update in appointment)
            System.out.println("Select manage option:");
            System.out.println("1. Edit Appointment");
            System.out.println("2. Post Review");
            System.out.println("3. Cancel Appointment");
            System.out.println("4. Go back\n");
            Scanner input = new Scanner(System.in);
            Scanner stringInput = new Scanner(System.in);
            int func = input.nextInt();

            if (func == 1) {
                do {
                    // 1.appointment_date ; 2.start_time ; 3.description   <- values of appointment selected
                    String[] appointmentInfo = b.getAppointmentInfo(appointmentID);
                    System.out.println("Select edit option:");
                    System.out.println("1. Edit Appointment Date");
                    System.out.println("2. Edit Appointment Time");
                    System.out.println("3. Edit Description");
                    System.out.println("4. Go back");
                    int editChoice = input.nextInt();
                    if (editChoice == 1) {
                        System.out.println("Old appointment date: " + appointmentInfo[0]);
                        System.out.print("Enter new appointment date: (i.e: 2020-01-23)\n");
                        String newDate = stringInput.next();
                        // update appointment_date in appointment
                        // TODO: check if new date entered is withing the semester selected
                        b.updateAppointmentDate(newDate, appointmentID);
                        System.out.println("Appointment date updated!\n");
                        
                    }
                    if (editChoice == 2) {
                        String[] fullTime = new String[2];
                        String[] hour = new String[2];
                        String completeTime;
                        int startTime;
                        System.out.println("Old appointment time: " + appointmentInfo[1]);
                        System.out.print("Enter new appointment time: (i.e: 7:30 pm)\n");
                        String newTime = stringInput.nextLine();

                        
                        //TODO: if time, add session duration option
                        System.out.println("Enter the duration of the session in minutes: \n");



                        fullTime = newTime.split(":");
                        hour = fullTime[1].split(" ");

                        startTime = Integer.parseInt(fullTime[0]);

                        if (Integer.parseInt(fullTime[0]) == 12){

                            if (hour[1].charAt(0) == 'p') {
                                startTime += 0;
                                completeTime = startTime + ":" + hour[0] + ":00";
                                b.updateAppointmentStartTime(completeTime, appointmentID);
                                System.out.println("Apppointment time updated!\n");
                            }

                            if (hour[1].charAt(0) == 'a') {
                                startTime -= 12;
                                completeTime = "0"+ startTime + ":" + hour[0] + ":00";
                                b.updateAppointmentStartTime(completeTime, appointmentID);
                                System.out.println("Appointment time updated!\n");    
                            }
                        }

                        else if (fullTime[1].charAt(3) == 'p'){
                            startTime += 12;
                            completeTime = startTime + ":" + hour[0] + ":00";
                            b.updateAppointmentStartTime(completeTime, appointmentID);
                            System.out.println("Appointment time updated!\n");
                        }

                        else if (fullTime[1].charAt(3) == 'a'){
                            startTime += 0;
                            completeTime = "0"+ startTime + ":" + hour[0] + ":00";
                            b.updateAppointmentStartTime(completeTime, appointmentID);
                            System.out.println("Appointment time updated!\n");
                        }

                        else {
                            System.out.println("Incorrect value");
                        }
                    }
                    if (editChoice == 3) {
                        System.out.println("Old description: " + appointmentInfo[2]);
                        System.out.print("Enter new description: ");
                        String description = stringInput.nextLine();

                        b.updateAppointmentDescription(description, appointmentID);
                        // update description in appointment
                        System.out.println("Description updated!\n");
                    }
                    if (editChoice == 4) {
                        break;
                    }
                } while (true);
            }

            //post review (insert into reviews)
            if (func == 2) {

            }

            //cancel appointment (delete from appointments)
            if (func == 3) {
                
            }
            
            //go back
            if (func == 4) {
                break;
            }
            
        } while (true);
    }

    private static void logout() {
        //application shuts down
    }

    public static int selectAppointment(Back b){
        //store all appointments into 2d array
        int numOfAppointments = b.getNumOfAppointments(student);
        String[][] appointments = new String[numOfAppointments][6]; //100 rows, 5 columns (appoitnmentID, tutorID, appointment_date, start_time, end_time, comment)
        appointments = b.getAppointments(student);
        System.out.println("\nAll available appointments: ");
        //search all appointments for student
        for (int i = 0; i < numOfAppointments; i++) {
                System.out.println(i+1 + ". " + "Appointment date: " + appointments[i][1] + "\n " +
                                "  Tutor name: " + appointments[i][2] + "\n " +
                                "  Start time: " + appointments[i][3] + "\n " +
                                "  End time: " + appointments[i][4] + "\n " +
                                "  Comment: " + appointments[i][5] + "\n"               
                ); 
        }
        System.out.print("Select the appointment to manage: ");

        Scanner input = new Scanner(System.in);
        int appointmentChosen = input.nextInt() - 1;

        System.out.println("\nYou selected appointment #" + (appointmentChosen+1) + ":");
        System.out.println(     "   Appointment date: " + appointments[appointmentChosen][1] + "\n " +
                                "  Tutor name: " + appointments[appointmentChosen][2] + "\n " +
                                "  Start time: " + appointments[appointmentChosen][3] + "\n " +
                                "  End time: " + appointments[appointmentChosen][4] + "\n " +
                                "  Comment: " + appointments[appointmentChosen][5] + "\n"               
                ); 

        // appointmentChosen is the appointment selected and points to the row. We can use the row to fetch column values
        String appointment_id = appointments[appointmentChosen][0];
        int appointmentID = Integer.parseInt(appointment_id);

        return appointmentID;
    }
}
