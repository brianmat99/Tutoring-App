import java.util.Scanner;

public class Front {
    public static int student;
    public static int tutorID;
    public static void main(String[] args) {
        int res = login();
        int funcSelected;

        // if student
        if (res == 0){
            do {
                funcSelected = showOptionsStudent();
    
                if (funcSelected < 1 || funcSelected > 4) {
                    System.out.println("Error: function " + funcSelected + " does not exists.");
                }
    
                if (funcSelected == 1) {
                    findTutor();
                }
    
                if (funcSelected == 2) {
                    viewAppointments();
                }
    
                if (funcSelected == 3) {
                    manageAppointment();
                }
    
                if (funcSelected == 4) {
                    logout();
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
        System.out.println("Please select a function:");
        System.out.println("1. Request Appointment");
        System.out.println("2. View Appointments");
        System.out.println("3. Manage Appointment");
        System.out.println("4. Logout");

        int func = input.nextInt();
        input.close();
        return func;
    }

    public static int showOptionsTutor() {
        Scanner input = new Scanner(System.in);
        System.out.println("PLease select a function:");
        System.out.println("1. Manage Appointment");
        System.out.println("2. Edit Profile");
        System.out.println("3. Accept/Decline");

        int func = input.nextInt();
        input.close();
        return func;
    }

    public static void findTutor() {
        //Connect to database and find all tutors
        //Store all tutors into String array
        String [] tutorNames = new String[100]; //set to 100 max tutors but can be adjusted with select count * from tutors
        System.out.println("Please select a tutor:");
        for (int i = 1; i <= tutorNames.length; i++){
            System.out.println(i + tutorNames[i-1]);
        }

        Scanner input = new Scanner(System.in);
        int tutorChosen = input.nextInt() - 1;
        //tutorNames[tutorChosen] is the name of the tutor; we can use this to select review / availability
        //change tutorID to tutor chosen

        System.out.println("Tutor: " + tutorNames[tutorChosen]);
        System.out.println("Reviews: ...");
        System.out.println("Availability: ...\n\n");

        System.out.println("Please enter the date requested for this session: (e.x: 01/27/2020)");
        String date = input.nextLine();
        System.out.println("Please enter the time: (e.x: 4:30pm)");
        String time = input.nextLine();

        //Insert into appointments 

        
    }

    private static void viewAppointments() {
        //select all appointments belonging to current student
    }

    private static void manageAppointment() {
        //edit appointment

        //post review

        //cancel appointment
    }

    private static void logout() {
        //application shuts down
    }
}
