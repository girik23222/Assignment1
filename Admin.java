package Model;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;

import Model.*;


public class Admin extends User {
    private DataManager dataManager;
    private List<Course> courseList;
    public Map<String, Student> studentList ;
    public Scanner scaner = new Scanner(System.in);

    // Constructor
    public Admin(String email, String password, String name, DataManager dataManager) {
        super(email, password, name);
        this.dataManager = dataManager;
        this.courseList = dataManager.getCourseList();
        this.studentList = dataManager.getAllStudent();
    }

    // Method to add a new course
    public void addCourse(String title, String code, String prof, int semester, int credits, List<Course> prereq,String timings,String location)
    {
        for (Course c : courseList) 
        {
            if (c.getCode().equals(code)) 
            {
                System.out.println("Course with code " + code + " already exists.");
                return;
            }
            if(c.getprof().equals((prof))){
                System.out.println("Professor " + prof +" is already assigned to " + c.getCode());
                return;
            }
        }
        Course course = new Course(title, code, prof, semester, credits, prereq,timings,location);
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
    public void viewAllComplaints(){
        if (studentList.isEmpty()) {
            System.out.println("No students found.");
        } else {
            for (Student student : studentList.values()) {
                System.out.println( "Student with Roll No: " + student.getID());
                for (int i = 0; i < student.complaints.size(); i++) {
                    List<String> complaint = student.complaints.get(i);
                    System.out.println("Complaint " + (i + 1) + ": " + complaint.get(0) + ", Status: " + complaint.get(1));
                }
                System.out.println();
            }
        }
    }

    public void updateComplaintStatus() {
        if (studentList.isEmpty()) {
            System.out.println("No students found.");
        } else {
            System.out.println("Enter the roll number: ");
            int id = scaner.nextInt();

            boolean studentFound = false;  // To track if the student is found or not

            for (Student student : studentList.values()) {  // Assuming studentList is a Map or Collection
                if (student.getID() == id) {
                    studentFound = true;
                    if (student.complaints.isEmpty()) {
                        System.out.println("No complaints found for this student.");
                    } else {
                        // Display complaints with their index
                        for (int i = 0; i < student.complaints.size(); i++) {
                            List<String> complaint = student.complaints.get(i);
                            System.out.println("Complaint " + (i + 1) + ": " + complaint.get(0) + ", Status: " + complaint.get(1));
                        }

                        // Ask for complaint number to update
                        System.out.println("Enter the Complaint Number to resolve: ");
                        int choice = scaner.nextInt();

                        // Validate the user's choice
                        if (choice > 0 && choice <= student.complaints.size()) {
                            List<String> selectedComplaint = student.complaints.get(choice - 1);  // Get the chosen complaint
                            selectedComplaint.set(1, "Resolved");  // Update the status to "Resolved"
                            System.out.println("Complaint " + choice + " status updated to Resolved.");
                        } else {
                            System.out.println("Invalid complaint number.");
                        }
                    }
                }
            }

            if (!studentFound) {
                System.out.println("Student with the given roll number not found.");
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
        for (Student student : studentList.values()) {
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
            System.out.println("Enter Student Semseter:");
            int semeseter = sc.nextInt();
            studentToUpdate.setEmail(email);
            studentToUpdate.setName(name);
            studentToUpdate.setSemester(semeseter);
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


        for (Student student : studentList.values()) {
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