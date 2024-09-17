package Model;
// import java.util.ArrayList;
import java.util.List;
import Model.*;


public class Admin extends User {
    private DataManager dataManager;
    private List<Course> courseList;

    // Constructor
    public Admin(String email, String password, String name, DataManager dataManager) {
        super(email, password, name);
        this.dataManager = dataManager;
        this.courseList = dataManager.getCourseList();
    }

    // Method to add a new course
    public void addCourse(String title, String code, String prof, int semester, int credits, List<Course> prereq) 
    {
        for (Course c : courseList) 
        {
            if (c.getCode().equals(code)) 
            {
                System.out.println("Course with code " + code + " already exists.");
                return;
            }
        }
        Course course = new Course(title, code, prof, semester, credits, prereq);
        dataManager.addCourse(course);
        System.out.println("Course " + title + " added successfully.");
    }

    // Method to remove an existing course by code
    public void removeCourse(String code) {
        Course courseToRemove = null;
        for (Course course : courseList) {
            if (course.getCode().equals(code)) {
                courseToRemove = course;
                break;
            }
        }
        if (courseToRemove != null) {
            courseList.remove(courseToRemove);
            System.out.println("Course with code " + code + " removed successfully.");
        } else {
            System.out.println("No course found with code " + code);
        }
    }

    // Method to register a new student
    public void registerStudent(String email, String password, String name, int ID) {
        Student student = new Student(email, password, name, ID);
        dataManager.signUp(student);
        System.out.println("Student registered successfully.");
    }

    // Method to register a new professor
    public void registerProfessor(String email, String password, String name, Course course) {
        Prof professor = new Prof(email, password, name, course);
        dataManager.signUp(professor);
        System.out.println("Professor registered successfully.");
    }

    // Method to view all students
    public void viewAllStudents() {
        List<Student> students = dataManager.getAllUsers(Student.class);
        if (students.isEmpty()) {
            System.out.println("No students found.");
        } else {
            for (Student student : students) {
                System.out.println("Student Name: " + student.getName() + ", Email: " + student.getEmail() + ", Roll No: " + student.getID());
            }
        }
    }

    // Method to view all professors
    public void viewAllProfessors() {
        List<Prof> professors = dataManager.getAllUsers(Prof.class);
        if (professors.isEmpty()) {
            System.out.println("No professors found.");
        } else {
            for (Prof professor : professors) {
                System.out.println("Professor Name: " + professor.getName() + ", Email: " + professor.getEmail() + ", Assigned Course: " + professor.getCourse().getCode());
            }
        }
    }

    public void viewAllCourses() {
        List<Course> courses = dataManager.getAllCourses();
        if(courses.isEmpty()) {
            System.out.println("No courses found.");
        } else {
            for(Course course : courses) {
                System.out.println("Course Name" + course.getTitle() + ", Code: " + course.getCode() + ", Credits" + course.getCredits());
            }
        }
    }

    // Method to update user credentials (example for students and professors)
    // public void updateUserCredentials(String email, String newPassword) {
    //     if (dataManager.verifyUserCredentials(email, newPassword)) {
    //         dataManager.addUserCredentials(email, newPassword);
    //         System.out.println("User credentials updated successfully.");
    //     } else {
    //         System.out.println("Invalid email or password.");
    //     }
    // }
}