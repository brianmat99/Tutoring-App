import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Scanner;

import javax.lang.model.util.ElementScanner6;

public class Front {
    public static String studentEmail;
    public static String tutorEmail;
    public static void main(String[] args) {
        Back b = new Back();
        b.openConnection("Phase2/newdb.sqlite");

        int funcSelected;
        int res;

        System.out.println("Welcome to PeerTutors 2.0!");
        res = login(b);
        // Scanner input = new Scanner(System.in);
        // student = input.nextLine();


        // need to look up name by email to display welcome message
        // System.out.println("Welcome " + tutorEmail + "...💩");
        
        // if student
        if (res == 0){
            System.out.println("\n          Student Menu");
            do {
                funcSelected = showOptionsStudent();
    
                if (funcSelected < 1 || funcSelected > 4) {
                    System.out.println("Error: function " + funcSelected + " does not exists.");
                }
    
                if (funcSelected == 1) {
                    createAppointment(b);
                }
    
                if (funcSelected == 2) {
                    viewAppointments(b);
                }
    
                if (funcSelected == 3) {
                    manageStudentAppointment(b);
                }
    
                if (funcSelected == 4) {
                    logout(b);
                }
    
            } while (true);
        }
        
        
        // if tutor
        if (res == 1){
            System.out.println("\n          Tutor Menu");
            do {
                funcSelected = showOptionsTutor();
                if (funcSelected < 1 || funcSelected > 4) {
                    System.out.println("Error: function " + funcSelected + " does not exists.");
                }
    
                if (funcSelected == 1) {    // Manage Appointment
                    manageTutorAppointment(b);
                }
    
                if (funcSelected == 2) {    // Edit Profile
                    Scanner input = new Scanner(System.in);
                    Scanner sInput = new Scanner(System.in);
                    int profileFunc;
                    while(true) {
                        String[] tutorInfo = new String[5];
                        tutorInfo = b.getTutorProfileByEmail(tutorEmail);

                        System.out.println("\n          Your profile");
                        System.out.println("\nName: " + tutorInfo[1]);
                        System.out.println("Email: " + tutorInfo[2]);
                        System.out.println("Phone number: " + tutorInfo[3]);
                        System.out.println("Description: " + tutorInfo[4]);

                        System.out.println("\nPlease select a function: ");
                        System.out.println("1. Update name ");
                        System.out.println("2. Update email ");
                        System.out.println("3. Update phone number ");
                        System.out.println("4. Update description ");
                        System.out.println("5. Exit profile");
                        profileFunc = input.nextInt();

                        if(profileFunc == 1) {
                            System.out.println("Enter updated name: ");
                            String updatedName = sInput.nextLine();
                            b.updateTutorName(updatedName, tutorEmail);
                            System.out.println("Name updated!");
                        }
                        if(profileFunc == 2) {
                            System.out.println("Enter updated email: ");
                            String updatedEmail = sInput.nextLine();
                            b.updateTutorEmail(updatedEmail, tutorEmail);
                            tutorEmail = updatedEmail;
                            System.out.println("Email updated!");
                        }
                        if(profileFunc == 3) {
                            System.out.println("Enter updated phone number: ");
                            String updatedPhone = sInput.nextLine();
                            b.updateTutorPhone(updatedPhone, tutorEmail);
                            System.out.println("Phone number updated!");
                        }
                        if(profileFunc == 4) {
                            System.out.println("Enter updated description: ");
                            String updatedPhone = sInput.nextLine();
                            b.updateTutorDesc(updatedPhone, tutorEmail);
                            System.out.println("Description updated!");
                        }
                        if(profileFunc == 5) {
                            break;
                        }
                    }

                }
    
                if (funcSelected == 3) {    // Accept/Decline
                    while(true) {
                        Scanner input = new Scanner(System.in);
                        int apptAccept = -1;

                        String[] tutorInfo = new String[5];
                        tutorInfo = b.getTutorProfileByEmail(tutorEmail);

                        int profileTutorID = Integer.parseInt(tutorInfo[0]);
                        int numOfPendingAppts = b.numOfPendingApptByTutorID(profileTutorID);
                        String[][] pendingAppts = new String[numOfPendingAppts][7];
                        pendingAppts = b.getPendingApptByTutorID(profileTutorID);

                        System.out.println("\n          Pending Appointments\n");

                        if(numOfPendingAppts == 0) {
                            System.out.println("No pending appointments\n");
                            break;
                        }

                        for(int i = 0; i < numOfPendingAppts; i++) {
                            String sName = b.getStudentNameFromStudentID(Integer.parseInt(pendingAppts[i][1]));
                            System.out.println((i + 1) + ". with " + sName);
                            System.out.println(pendingAppts[i][3] + " from " + pendingAppts[i][4] + "-" + pendingAppts[i][5]);
                            System.out.println("Description: " + pendingAppts[i][6]);
                        }

                        System.out.println("Which appointment to accept: (0 to exit) ");
                        apptAccept = input.nextInt();

                        if(apptAccept == 0) {
                            break;
                        }
                        else {
                            b.acceptAppointmentByID(Integer.parseInt(pendingAppts[apptAccept - 1][0]));
                            System.out.println("Appointment accepted!");
                        }
                    }

                }

                if (funcSelected == 4) {
                    logout(b);
                }
            } while (true);
        }

        System.out.println("invalid login... goodbye!");
        b.closeConnection();
    }

    public static int login(Back b) {
        int response = 1000; // 0 for student, 1 for tutor?

        Scanner input = new Scanner(System.in);
        System.out.println("Do you have an account?\n1. Yes\n2. No");
        int account = input.nextInt();

        // Has an account
        if (account == 1){
            System.out.println("Sign in process...");
            System.out.println("Please enter your email: ");
            String email = input.next();

            studentEmail = b.getStudentByEmail(email);
            tutorEmail = b.getTutorByEmail(email);
            
            if (studentEmail == null) {
                response = 1;
            }
            if (tutorEmail == null){
                response = 0;
            }

        }

        // Does NOT have an account
        else if (account == 2){
            // Sign up
            System.out.println("\n\nSign up process...");
            System.out.println("Are you a student or a tutor?\n1. Student\n2. Tutor");
            int accountType = input.nextInt();
            // Student account
            if (accountType == 1) {
                Scanner input2 = new Scanner(System.in);
                System.out.println("Please enter your name: (i.e: Bob Smith)");
                String name = input2.nextLine();

                System.out.println("Please enter your email: (i.e: bobsmith123@gmail.com)");
                String email = input.next();
                // Insert into Student
                b.insertNewStudent(name, email);
                System.out.println("\nThank you for signing up!\nTransferring you to main menu...");
                studentEmail = email;
                response = 0;
            }
            // Tutor account
            if (accountType == 2) {
                Scanner input2 = new Scanner(System.in);
                // Insert into Tutor
                System.out.println("Please enter your name: (i.e: Bob Smith)");
                String name = input2.nextLine();

                System.out.println("Please enter your email: (i.e: bobsmith123@gmail.com)");
                String email = input.next();

                System.out.println("Please enter your phone number: (i.e: 4153397200)");
                String phone = input.next();
                
                System.out.println("Please enter a profile description: (i.e: I'm excited to help!)");
                String description = input2.nextLine();

                b.insertNewTutor(name, email, phone, description);
                System.out.println("\nThank you for signing up!\nTransferring you to main menu...");
                tutorEmail = email;
                response = 1;
            }
        }
        else {
            System.out.println("Invalid input.");
        }

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
        System.out.println("\nThis program supports 4 functions: ");
        System.out.println("1. Manage Appointment");
        System.out.println("2. Edit Profile");
        System.out.println("3. Accept/Decline");
        System.out.println("4. Logout");
        System.out.println("Please choose the function you want: ");

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

        System.out.println("\nWhat days are you available: Sun, Mon, Tue, Wed, Thu, Fri, Sat (in comma separated list i.e. Mon,Tue,Fri,Sat)");
        daysAvailable = input.next();

        String[] daysAvailArr = daysAvailable.split(",");

        int numOfTutors = b.getNumOfTutorsByCourse(courseNames[courseChosen], daysAvailArr);
        String[][] tutorNames = new String[numOfTutors][2];
        tutorNames = b.getTutorsByCourse(courseNames[courseChosen], daysAvailArr);
        // Variable declarations from while loop
        int numOfReviews;
        String[][] reviews;
        double avgRating;
        int numOfAvail;
        String[][] tutorAvail;
        
        while(true) {   // To allow user to look through all tutor reviews, availability, etc 
            int confirm = 2;
            System.out.println("\nPlease select a tutor: ");
            for (int i = 0; i < numOfTutors; i++) {
                System.out.println((i + 1) + ". " + tutorNames[i][0] + " on " + tutorNames[i][1]);
            }
        
            tutorChosen = input.nextInt() - 1;

            numOfReviews = b.getNumOfTutorReviews(tutorNames[tutorChosen][0]);
            reviews = new String[numOfReviews][2];
            reviews = b.getTutorReviews(tutorNames[tutorChosen][0]);
            avgRating = 0;
            for(int i = 0; i < numOfReviews; i++) {
                avgRating += Double.parseDouble(reviews[i][1]);
            }
            avgRating /= numOfReviews;

            System.out.println("\nTutor: " + tutorNames[tutorChosen][0]);
            System.out.println("Reviews: ");
            for(int i = 0; i < numOfReviews; i++) {
                System.out.println((i + 1) + ". Rating: " + reviews[i][1] + " - " + reviews[i][0]);
            }
            System.out.println("Average rating: " + String.format("%.2f", avgRating));

            numOfAvail = b.getTutorNumAvail(tutorNames[tutorChosen][0]);
            tutorAvail = new String[numOfAvail][3];
            tutorAvail = b.getTutorAvail(tutorNames[tutorChosen][0]);
            System.out.println("Availability: ");
            for(int i = 0; i < numOfAvail; i++) {
                System.out.println(tutorNames[tutorChosen][0] + " " + tutorAvail[i][0] + " " +tutorAvail[i][1] + " to " + tutorAvail[i][2]);
            }
            
            System.out.println("\nConfirm choice (1/0): ");
            confirm = input.nextInt();

            if(confirm == 1) {
                break;
            }
            else {
                // continue
            }
        }
        // Variable declarations from while loop below 
        String date;
        String[] dateParsed;
        int month;
        int day;
        int year;
        Calendar myCalendar;
        Date myDate;
        SimpleDateFormat simp;
        String dayUserEntered;
        
        while(true) {
            int validDateEntered = 0;   // Starts false

            System.out.println("Please enter the date requested for this session: (e.x: 1/2/2020)(M/D/Y)");
            date = input.next();
            dateParsed = date.split("/");
            month = Integer.parseInt(dateParsed[0]);
            day = Integer.parseInt(dateParsed[1]);
            year = Integer.parseInt(dateParsed[2]);
            System.out.println("(m/d/y): (" + month + "/" + day + "/" + year + ")");
            myCalendar = new GregorianCalendar(year, (month - 1), day);
            myDate = myCalendar.getTime();
            simp = new SimpleDateFormat("E");
            dayUserEntered = simp.format(myDate);
            System.out.println(dayUserEntered);

            for(int i = 0; i < numOfAvail; i++) {
                if(tutorAvail[i][0].equals(dayUserEntered)) {
                    validDateEntered = 1;
                }
            }

            if(validDateEntered == 1) {
                break;
            }
            System.out.println("Day of date entered does not match tutor's availability. Please enter a new date...");;
        }
        
        // Variable declarations from while loop below 
        String userTime;
        String[] userTimeParsed;
        int userTimeSHour;
        int userTimeSMin;
        String[] tutorAvailSParsed;
        String[] tutorAvailFParsed;
        int tutorAvailSHour;
        int tutorAvailFHour;
        int tutorAvailSMin;
        int tutorAvailFMin;

        while(true) {
            int validTime= 0;   // Starts false
            System.out.println("Please enter the time: (e.x: 04:30, 24h format)");
            userTime = input.next();

            userTimeParsed = userTime.split(":");
            userTimeSHour = Integer.parseInt(userTimeParsed[0]);
            userTimeSMin = Integer.parseInt(userTimeParsed[1]);
            
            for(int i = 0 ; i < numOfAvail; i++) {
                tutorAvailSParsed = tutorAvail[i][1].split(":");
                tutorAvailFParsed = tutorAvail[i][2].split(":");
                tutorAvailSHour = Integer.parseInt(tutorAvailSParsed[0]);
                tutorAvailFHour = Integer.parseInt(tutorAvailFParsed[0]);
                tutorAvailSMin = Integer.parseInt(tutorAvailSParsed[1]);
                tutorAvailFMin = Integer.parseInt(tutorAvailFParsed[1]);

                if(tutorAvail[i][0].equals(dayUserEntered)) {
                    if((userTimeSHour < tutorAvailFHour) && (userTimeSHour >= tutorAvailSHour)) {
                        validTime = 1;
                    }
                }
            }

            if(validTime == 1) {
                break;
            }
            else {
                System.out.println("Time selected is not within tutor's availability. Please try again... ");
            }
        }

        System.out.println("Describe help required: ");
        input.nextLine();
        String userComment = input.nextLine();
        System.out.println(userComment);

        int sID = b.getStudentIDFromEmail(studentEmail);
        int tID = b.getTutorIDFromName(tutorNames[tutorChosen][0]);
        String appt = year + "-" + month + "-" + day;
        String sTime = String.valueOf(userTimeSHour) + ":" + String.valueOf(userTimeSMin) + ":00";
        String eTime = String.valueOf(userTimeSHour + 1) + ":" + String.valueOf(userTimeSMin) + ":00";

        b.insertAppointment(sID, tID, appt, sTime, eTime, userComment);
        System.out.println("Appointment created! ");
        System.out.println(dayUserEntered + " " + appt + " " + sTime + "-" + eTime + "\n" + "with " + tutorNames[tutorChosen][0]);


        //Insert into appointments 
        
    }

    private static void viewAppointments(Back b) {
        //select all appointments belonging to current student
        int sID = b.getStudentIDFromEmail(studentEmail);
        int numOfAppts = b.getNumOfStudentAppts(sID);
        String[][] apptInfo = new String[numOfAppts][7];
        apptInfo = b.getStudentAppts(sID);

        if(numOfAppts == 0) {
            System.out.println("You have no active appointments.");
        }

        System.out.println();

        for(int i = 0; i < numOfAppts; i++) {
            String tName = b.getTutorNameFromID(Integer.parseInt(apptInfo[i][1]));
            String apptDate = apptInfo[i][2];
            String sTime = apptInfo[i][3];
            String eTime = apptInfo[i][4];
            String apptDesc = apptInfo[i][5];
            
            if(Integer.parseInt(apptInfo[i][6]) == 1) {
                System.out.println((i + 1) + ". ACCEPTED");
            }
            else {
                System.out.println((i + 1) + ". APPOINTMENT PENDING");
            }
            System.out.println("Tutor: " + tName);
            System.out.println(apptDate + " " + sTime + "-" + eTime);
            System.out.println("Description: " + apptDesc);
        }

    }

    private static void manageStudentAppointment(Back b) {

        int appointmentID = selectAppointment(b);
        // Variables declared here to be used in entire function
        String[] appointmentInfo = b.getAppointmentInfo(appointmentID);
        
        if (appointmentID != 0){
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
                    appointmentInfo = b.getAppointmentInfo(appointmentID);
                    System.out.println("\nSelect edit option:");
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
                        //System.out.println("Enter the duration of the session in minutes: \n");



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
                System.out.println("Type review on one line: ");
                String review = stringInput.nextLine();
                System.out.println("Enter rating: ");
                double userRating = input.nextDouble();

                int studentID = b.getSIDFromApptID(appointmentID);
                int tutorID = b.getTIDFromApptID(appointmentID);

                b.insertReview(studentID, tutorID, userRating, review);
                System.out.println("Review published!\n");
            }

            //cancel appointment (delete from appointments)
            if (func == 3) {
                b.deleteApptFromID(appointmentID);
                System.out.println("Appointment deleted!\n");
            }
            
            //go back
            if (func == 4) {
                break;
            }
            
        } while (true);
    }
    }

    private static void manageTutorAppointment(Back b) {
        int appointmentID = selectAppointmentTutor(b);
        // Variables declared here to be used in entire function
        String[] appointmentInfo = b.getAppointmentInfo(appointmentID);

        if (appointmentID != 0){
            do {
                //edit appointment (update in appointment)
                System.out.println("Select manage option:");
                System.out.println("1. Edit Appointment");
                System.out.println("2. Cancel Appointment");
                System.out.println("3. Go back\n");
                Scanner input = new Scanner(System.in);
                Scanner stringInput = new Scanner(System.in);
                int func = input.nextInt();
    
                if (func == 1) {
                    do {
                        // 1.appointment_date ; 2.start_time  <- values of appointment selected
                        appointmentInfo = b.getAppointmentInfo(appointmentID);
                        System.out.println("\nSelect edit option:");
                        System.out.println("1. Edit Appointment Date");
                        System.out.println("2. Edit Appointment Time");
                        System.out.println("3. Go back");
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
                            //System.out.println("Enter the duration of the session in minutes: \n");
    
    
    
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
                            break;
                        }
                    } while (true);
                }
    
                // cancel appointment (delete from appointments)
                if (func == 2) {
                    b.deleteApptFromID(appointmentID);
                    System.out.println("Appointment deleted!\n");
                }
                
                // go back
                if (func == 3) {
                    break;
                }
                
            } while (true);
        }
        
    }

    private static void logout(Back b) {
        //application shuts down
        b.closeConnection();
        System.out.println("\nThanks for using PeerTutors 2.0! Have a great day! ");
        System.exit(0);
    }

    public static int selectAppointment(Back b){
        //store all appointments into 2d array
        int numOfAppointments = b.getNumOfAppointmentsStudent(studentEmail);
        if (numOfAppointments == 0){
            System.out.println("\nYou have no appointments scheduled.\n");
            return 0;
        }
        String[][] appointments = new String[numOfAppointments][6]; //100 rows, 5 columns (appoitnmentID, tutorID, appointment_date, start_time, end_time, comment)
        appointments = b.getStudentAppointments(studentEmail);
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

    public static int selectAppointmentTutor(Back b){
        //store all appointments into 2d array
        int numOfAppointments = b.getNumOfAppointmentsTutor(tutorEmail);
        if (numOfAppointments == 0){
            System.out.println("\nYou have no appointments scheduled.\n");
            return 0;
        }
        String[][] appointments = new String[numOfAppointments][6]; //100 rows, 5 columns (appoitnmentID, tutorID, appointment_date, start_time, end_time, comment)
        appointments = b.getTutorAppointments(tutorEmail);
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
