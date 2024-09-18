package Model;

import Model.*;

// import java.util.List;
import java.util.Scanner;

public class Main {

    private static DataManager dataManager = DataManager.getInstance();
    // private static LoginManager dataManager = new LoginManager();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean start = true;

        // Seed some initial data if required (this can be modified as per need)
        Admin admin = new Admin("a", "a", "Admin", dataManager);
        dataManager.signUp(admin);

        Student student1 = new Student("s", "s", "Girik", 2024101);
        student1.setSemester(1);
        Student student2 = new Student("s1", "s1", "Girik2", 2024102);
        student2.setSemester(2);
        Student student3 = new Student("s2", "s2", "Girik3", 2024103);
        student3.setSemester(2);
        dataManager.signUp(student1);
        dataManager.signUp(student2);
        dataManager.signUp(student3);

        Course course1 = new Course("Math 101", "M101", "Aman", 1, 4, null,"4-5","RND");
        Course course2 = new Course("SNS", "ECE250", "Ram", 2, 4, null,"5-6","C01");
        Course course3 = new Course("AP", "CSE250", "Shyam", 2, 4, null,"12-2","LHC101");
        admin.addCourse("Math 101", "M101", "Aman", 1, 4, null,"4-5","RND");
        admin.addCourse("SNS", "ECE250", "Ram", 2, 4, null,"5-6","C01");
        admin.addCourse("AP", "CSE250", "Shyam", 2, 4, null,"12-2","LHC101");

        Prof professor1 = new Prof("p", "p", "Aman", course1);
        Prof professor2 = new Prof("r", "r", "Ram", course2);
        Prof professor3 = new Prof("q", "q", "Shyam", course3);
        dataManager.signUp(professor1);
        dataManager.signUp(professor2);
        dataManager.signUp(professor3);

        while (start) {
            System.out.println("===== Welcome to ERP =====");
            System.out.println("1. Login");
            System.out.println("2. Sign Up");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    loginUser();
                    break;
                case 2:
                    signUpUser();
                    break;
                case 3:
                    System.out.println("Exiting the system. Goodbye!");
                    start = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void loginUser() {

        Scanner scanner1 = new Scanner(System.in);
        System.out.print("Enter email: ");
        String email = scanner1.nextLine();

        System.out.print("Enter password: ");
        String password = scanner1.nextLine();

        User user = dataManager.login(email, password);
        // scanner1.close();
        if (user != null) {
            System.out.println("Login successful!");

            // Determine user type and show respective menu
            if (user instanceof Student) {
                studentMenu((Student) user);
            } else if (user instanceof Prof) {
                professorMenu((Prof) user);
            } else if (user instanceof Admin) {
                adminMenu((Admin) user);
            } else {
                System.out.println("Unrecognized role.");
            }
        } else {
            System.out.println("Login failed. Please check your credentials.");
        }
    }

    private static void signUpUser() {
        System.out.println("\n===== Sign Up =====");
        System.out.println("1. Sign up as Student");
        System.out.println("2. Sign up as Professor");
        System.out.println("3. Sign up as Admin");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter your email: ");
        String email = scanner.nextLine();
        System.out.print("Enter your password: ");
        String password = scanner.nextLine();
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();

        switch (choice) {
            case 1:
                Student student = new Student(email, password, name, 0);
                dataManager.signUp(student);
                // dataManager.signUp(student);
                System.out.println("Student sign up successful!");
                break;

            case 2:
                Prof professor = new Prof(email, password, name, null);
                dataManager.signUp(professor);
                // dataManager.signUp(professor);
                System.out.println("Professor sign up successful!");
                break;

            case 3:
                Admin admin = new Admin(email, password, name, dataManager);
                dataManager.signUp(admin);
                // dataManager.signUp(admin);
                System.out.println("Admin sign up successful!");
                break;

            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }

    // Student Menu
    private static void studentMenu(Student student) {
        boolean exit = false;

        while (!exit) {
            System.out.println("\n===== Student Menu =====");
            System.out.println("1. View available courses");
            System.out.println("2. Register for a course");
            System.out.println("3. Drop a course");
            System.out.println("4. View grades");
            System.out.println("5. View Schedule");
            System.out.println("6. View Completed Courses");
            System.out.println("7. View CGPA and SGPA");
            System.out.println("8. Register a Complaint");
            System.out.println("9. View all Complaints");
            System.out.println("10. Logout");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    student.viewAvailableCourses();
                    break;
                case 2:
                    System.out.print("Enter course code to register: ");
                    String courseCode = scanner.nextLine();
                    student.registerCourse(courseCode);
                    break;
                case 3:
                    System.out.print("Enter course code to drop: ");
                    String dropCode = scanner.nextLine();
                    student.dropCourse(dropCode);
                    break;
                case 4:
                    student.viewGrades();
                    break;
                case 5:
                    student.viewSchedule();
                    break;
                case 6:
                    student.viewcompleted();
                    break;
                case 7:
                    student.trackProgress(student.getSemester());
                    student.trackProgress();
                    break;
                case 8:
                    System.out.println("Enter the description for complaint: ");
                    String desc = scanner.nextLine();
                    student.addComplaint(desc);
                    break;
                case 9:
                    System.out.println("All complaints: ");
                    student.viewComplaints();
                    break;
                case 10:
                    System.out.println("Logging out...");
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    // Prof Menu
    private static void professorMenu(Prof professor) {
        boolean exit = false;

        while (!exit) {
            System.out.println("\n===== Professor Menu =====");
            System.out.println("1. View assigned course");
            System.out.println("2. Update course details");
            System.out.println("3. View enrolled students");
            System.out.println("4. Set grades for students");
            System.out.println("5. Logout");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    professor.viewCourseDetails();
                    break;
                case 2:
                    updateCourseDetails(professor);
                    break;
                case 3:
                    professor.viewEnrolledStudents();
                    break;
                case 4:
                    assignGrade(professor);
                    break;

                case 5:
                    System.out.println("Logging out...");
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    // Admin Menu
    private static void adminMenu(Admin admin) {
        boolean exit = false;

        while (!exit) {// add prereqs to a course
            System.out.println("\n===== Admin Menu =====");
            System.out.println("1. Add a new course");
            System.out.println("2. Remove a course");
            System.out.println("3. Register a new student");
            System.out.println("4. View all students");
            System.out.println("5. View all professors");
            System.out.println("6. View all courses");
            System.out.println("7. Update Student Details");
            System.out.println("8. Delete Student Details");
            System.out.println("9. View Complaints");
            System.out.println("10. Update Complaints");
            System.out.println("11. Logout");
            
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    System.out.println("To add a course,");
                    System.out.println("Enter course title: ");
                    String courseTitle = scanner.nextLine();
                    System.out.println("Enter course code: ");
                    String courseCode = scanner.nextLine();
                    System.out.println("Enter Professor Name: ");
                    String professorName = scanner.nextLine();
                    System.out.println("Enter Semester: ");
                    int semester = scanner.nextInt();
                    System.out.println("Enter Credits: ");
                    int credits = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Enter timings: ");
                    String timings = scanner.nextLine();
                    System.out.println("Enter location : ");
                    String  location = scanner.nextLine();
                    admin.addCourse(courseTitle, courseCode, professorName, semester, credits, null,timings,location);
                    break;
                case 2:
                    System.out.print("Enter course code to remove: ");
                    courseCode = scanner.nextLine();
                    admin.removeCourse(courseCode);
                    break;
                case 3:
                    System.out.print("Enter student Email: ");
                    String studentEmail = scanner.nextLine();
                    System.out.print("Enter student Password: ");
                    String studentPassword = scanner.nextLine();
                    System.out.print("Enter student name: ");
                    String studentName = scanner.nextLine();
                    System.out.println("Enter Semester: ");
                    semester = scanner.nextInt();
                    System.out.println("Enter student Roll Number: ");
                    int studentRollNo = scanner.nextInt();
                    admin.registerStudent(studentEmail, studentPassword, studentName, studentRollNo);
                    break;
                case 4:
                    admin.viewAllStudents();
                    break;
                case 5:
                    admin.viewAllProfessors();
                    break;
                case 6:
                    admin.viewAllCourses();
                    break;
                    case 7:
                        admin.updateStudentDetails();
                        break;
                case 8:
                    admin.deleteStudentDetails();
                    break;
                case 9:
                    admin.viewAllComplaints();
                    break;
                case 10:
                    admin.updateComplaintStatus();
                    break;
                case 11:
                    System.out.println("Logging out...");
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    // Methods to handle specific tasks
    private static void updateCourseDetails(Prof professor) {
        System.out.print("Enter new course credits: ");
        int credits = scanner.nextInt();
        professor.updateCredits(credits);
        System.out.println("Enter the course limits: ");
        int limit  = scanner.nextInt();
        professor.updateLimit(limit);
        System.out.print("Enter new course Timings: ");
        Scanner scanner1 = new Scanner(System.in);
         String timings = scanner1.nextLine();
         professor.updateTimings(timings);

    }

    private static void assignGrade(Prof professor) {
        System.out.print("Enter student roll number: ");
        int ID = scanner.nextInt();
        Student student = null;
        for (Student s : dataManager.getAllUsers(Student.class)) {
            if (s.getID() == ID) {
                student = s;
                break;
            }
        }
        System.out.print("Enter grade: ");
        int grade = scanner.nextInt();
        professor.assignGrade(student, grade);
    }
}