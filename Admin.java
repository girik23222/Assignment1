package Model;
// import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import Model.*;


public class Admin extends User {
    private DataManager dataManager;
    private List<Course> courseList;
    public Map<String, Student> studentList ;

    // Constructor
    public Admin(String email, String password, String name, DataManager dataManager) {
        super(email, password, name);
        this.dataManager = dataManager;
        this.courseList = dataManager.getCourseList();
        this.studentList = dataManager.getAllStudent();
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
            if(c.getprof().equals((prof))){
                System.out.println("Professor " + prof +" is assigned to " + c.getCode());
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

//    // Method to register a new professor
//    public void registerProfessor(String email, String password, String name, Course course) {
//        Prof professor = new Prof(email, password, name, course);
//        dataManager.signUp(professor);
//        System.out.println("Professor registered successfully.");
//    }

    // Method to view all students
    public void viewAllStudents() {
        if (studentList.isEmpty()) {
            System.out.println("No students found.");
        } else {
            // Iterate over the values of the studentList Map to access Student objects
            for (Student student : studentList.values()) {
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
    public void updateStudentDetails() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Student Roll No.:");
        int rollNum = sc.nextInt();
        sc.nextLine(); // consume the leftover newline

        Student studentToUpdate = null;

        // Loop through the list of students to find the student with the matching roll number
        for (Student student : studentList.values()) { // assuming studentList is a Map<String, Student>
            if (student.getID() == rollNum) {
                studentToUpdate = student;
                break;
            }
        }

        if (studentToUpdate != null) {
            System.out.println("Enter Student Email:");
            String email = sc.nextLine();
            System.out.println("Enter Student Name:");
            String name = sc.nextLine();

            studentToUpdate.setEmail(email);
            studentToUpdate.setName(name);
            System.out.println("Student details updated successfully.");
        } else {
            System.out.println("No student found with Roll No. " + rollNum);
        }
    }

    public void deleteStudentDetails() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Student Roll No.:");
        int rollNum = sc.nextInt();
        sc.nextLine(); // consume the leftover newline

        Student studentToRemove = null;

        // Loop through the list of students to find the student with the matching roll number
        for (Student student : studentList.values()) { // assuming studentList is a Map<String, Student>
            if (student.getID() == rollNum) {
                studentToRemove = student;
                break;
            }
        }

        // If a matching student is found, remove them from the studentList
        if (studentToRemove != null) {
            studentList.remove(studentToRemove.getEmail()); // remove based on email (key in the map)
            System.out.println("Student with Roll No. " + rollNum + " removed successfully.");
        } else {
            System.out.println("No student found with Roll No. " + rollNum);
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

}