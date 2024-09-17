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
        Admin admin = new Admin("admin@university.edu", "adminpass", "Admin", dataManager);
        dataManager.signUp(admin);

        Student student1 = new Student("student1@university.edu", "password1", "Girik", 2024101);
        Student student2 = new Student("student2@university.edu", "password2", "Girik2", 2024102);
        Student student3 = new Student("student3@university.edu", "password3", "Girik3", 2024103);
        dataManager.signUp(student1);
        dataManager.signUp(student2);
        dataManager.signUp(student3);

        Course course1 = new Course("Math 101", "M101", "Aman", 1, 4, null);
        dataManager.addCourse(course1);

        Prof professor1 = new Prof("aman1@university.edu", "profpass", "Aman", course1);
        dataManager.signUp(professor1);
        dataManager.signUp(professor1);

        while (start) {
            System.out.println("===== Welcome to ERP =====");
            System.out.println("1. Login");
            System.out.println("2. Sign Up");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            // scanner.nextLine();
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
        System.out.println("Email line 63 : " + email);

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
        scanner.nextLine();
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
            // System.out.println("5. Submit a complaint");
            System.out.println("6. Logout");

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
                // case 5:
                // System.out.print("Enter complaint description: ");
                // String description = scanner.nextLine();
                // System.out.println("Enter date of complaint: ");
                // String date = scanner.nextLine();
                // // student.submitComplaint(description, date);
                // break;
                case 6:
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
            // System.out.println("5. Add an office hour");
            System.out.println("6. Logout");

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
                // case 5:
                // System.out.println("Enter day: ");
                // String day = scanner.nextLine();
                // System.out.println("Enter Start Time: ");
                // int startTime = scanner.nextInt();
                // System.out.println("Enter End Time: ");
                // int endTime = scanner.nextInt();
                // System.out.println("Enter the location: ");
                // String location = scanner.nextLine();
                // // Schedule schedule = new Schedule(day, startTime, endTime, location);
                // break;
                case 6:
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
            System.out.println("4. Register a new professor");
            System.out.println("5. View all students");
            System.out.println("6. View all professors");
            System.out.println("7. View all courses");
            
            System.out.println("8. Logout");
            
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
                    admin.addCourse(courseTitle, courseCode, professorName, semester, credits, null);
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
                    System.out.print("Enter professor Email: ");
                    String profEmail = scanner.nextLine();
                    System.out.print("Enter professor Password: ");
                    String profPassword = scanner.nextLine();
                    System.out.print("Enter professor name: ");
                    String profName = scanner.nextLine();
                    System.out.println("Enter Course Code: ");
                    courseCode = scanner.nextLine();
                    Course course = null;
                    for (Course c : dataManager.getCourseList()) {
                        if (c.getCode().equals(courseCode)) {
                            course = c;
                            break;
                        }
                    }
                    admin.registerProfessor(profEmail, profPassword, profName, course);
                    break;
                case 5:
                    admin.viewAllStudents();
                    break;
                case 6:
                    admin.viewAllProfessors();
                    break;
                case 7:
                    admin.viewAllCourses();
                    break;
                case 8:
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