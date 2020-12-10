import java.util.Scanner;

public class Front {
    public static int student;
    public static int tutorID;
    public static void main(String[] args) {
        Back b = new Back();
        b.openConnection("Phase2/database.sqlite");

        int res = login();
        int funcSelected;

        System.out.println("Welcome to PeerTutors 2.0!");
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
                    manageAppointment();
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
        
    }

    public static int login() {
        int response = 0; // 0 for student, 1 for tutor?

        return response;
    }

    public static int showOptionsStudent() {
        Scanner input = new Scanner(System.in);
        int func;
        System.out.println("This program supports 4 functions:");
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
        System.out.println("PLease select a function:");
        System.out.println("1. Manage Appointment");
        System.out.println("2. Edit Profile");
        System.out.println("3. Accept/Decline");

        int func = input.nextInt();
        return func;
    }

    public static void createAppointment(Back b) {
        int numOfTutors = b.getTutorsCount();
        System.out.println("Num of tutors: " + numOfTutors);
        
        //Store all tutors into String array
        String [] tutorNames = new String[100]; //set to 100 max tutors but can be adjusted with select count * from tutors
        System.out.println("Please select a tutor:");
        for (int i = 1; i <= tutorNames.length; i++){
            System.out.println(i + ". " + tutorNames[i-1]);
        }

        Scanner input = new Scanner(System.in);
        int tutorChosen = input.nextInt() - 1;
        //tutorNames[tutorChosen] is the name of the tutor; we can use this to select review / availability
        //change tutorID to tutor chosen

        System.out.println("Tutor: " + tutorNames[tutorChosen]);
        System.out.println("Reviews: ...");
        System.out.println("Availability: ...\n\n");

        System.out.println("Please enter the date requested for this session: (e.x: 01/27/2020)");
        String date = input.next();
        System.out.println("Please enter the time: (e.x: 4:30pm)");
        String time = input.next();

        //Insert into appointments 
        
    }

    private static void viewAppointments() {
        //select all appointments belonging to current student
    }

    private static void manageAppointment() {

        int appointmentID = selectAppointment();
        

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
                    System.out.println("Select edit option:");
                    System.out.println("1. Edit Appointment Date");
                    System.out.println("2. Edit Start Time");
                    System.out.println("3. Edit Description");
                    System.out.println("4. Go back");
                    int editChoice = input.nextInt();
                    if (editChoice == 1) {
                        System.out.print("Enter new appointment date: ");
                        String newDate = stringInput.next();
                        // update appointment_date in appointment
                        System.out.println("Appointment date updated!\n");
                        
                    }
                    if (editChoice == 2) {
                        System.out.print("Enter new start time: ");
                        String newTime = stringInput.next();
                        // update start_time in appointment
                        System.out.println("Start time updated!\n");
                    }
                    if (editChoice == 3) {
                        System.out.print("Enter new description: ");
                        String description = stringInput.next();
                        // update comment in appointment
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

    public static int selectAppointment(){
        //store all appointments into 2d array
        String[][] appointments = new String[100][6]; //100 rows, 5 columns (appoitnmentID, tutorID, appointment_date, start_time, end_time, comment)
        System.out.println("Select the appointment to manage: ");
        //search all appointments for student
        for (int i = 1; i <= 100; i++) {
            for (int j = 1; j < 6; j++) {
                System.out.println(i + ". " + appointments[i][j]);
            }
        }

        Scanner input = new Scanner(System.in);
        int appointmentChosen = input.nextInt() - 1;

        // appointmentChosen is the appointment selected and points to the row. We can use the row to fetch column values
        String appointment_id = appointments[appointmentChosen][0];
        int appointmentID = Integer.parseInt(appointment_id);

        return appointmentID;
    }
}
