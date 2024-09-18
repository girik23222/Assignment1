package Model;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
// import Model.Student;

public class Course {
    private String title;
    private String code;
    private String prof;
    private int sem;
    private int credits;
    private List<Course> prereq;  // List of prerequisite courses
    private List<Student> enrolled;
    private List<Schedule> schedule;
    private Map<Student, Integer> studentGrades;  // Mapping between student and their grade
    private int limit;
     private int startTime;
     private int endTime;

    // Constructor
    public Course(String title, String code, String prof, int sem, int credits, List<Course> prereq) {
        this.title = title;
        this.code = code;
        this.prof = prof;
        this.sem = sem;
        this.credits = credits;
        this.prereq = prereq != null ? prereq : new ArrayList<>();
        this.enrolled = new ArrayList<>();
        this.schedule = new ArrayList<>();
        this.studentGrades = new HashMap<>();
        this.limit = 60; // Default maximum enrollments, can be updated later
        // this.startTime = 0000; // Default start time, can be updated later
        // this.endTime = 0001; // Default end time, can be updated later
    }

    // Getters and Setters
    public String getTitle() {
        return title;
    }

    public String getCode() {
        return code;
    }

    public String getprof() {
        return prof;
    }

    public int getSem() {
        return sem;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
       this.credits = credits;

    }

    public List<Course> getPrereq() {
        return prereq;
    }

    public void setPrereq(List<Course> prereq) {
        this.prereq = prereq;
    }

    public List<Student> getStudents() {
        return enrolled;
    }

    public boolean addStudent(Student student) {
        if (enrolled.size() < limit) {
            enrolled.add(student);
            return true;
        } else {
            System.out.println("Course is full! Cannot enroll more students.");
            return false;
        }
    }

    public void removeStudent(Student student) {
        enrolled.remove(student);
    }

    public List<Schedule> getschedule() {
        return schedule;
    }

    public void setschedule(List<Schedule> schedule) {
        this.schedule = schedule;
    }

     public int getStartTime() {
         return startTime;
     }

     public void setStartTime(int startTime) {
         this.startTime = startTime;
     }

     public int getEndTime() {
         return endTime;
     }

     public void setEndTime(int endTime) {
         if(endTime>startTime)
         {
             this.endTime = endTime;
         }
     }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getLimit() {
        return limit;
    }

    // Grade management
    public void setGrade(Student student, int grade) {
        if (enrolled.contains(student)) {
            if (grade <= 10 && grade >= 3) {
                studentGrades.put(student, grade);
                System.out.println("Grade updated for " + student.getName() + ": " + grade);
            } else {
                System.out.println("Please enter grade in range (3-10)");
            }
        } else {
            System.out.println("Student is not enrolled in this course.");
        }
    }
    

    public int getGrade(Student student) {
        return studentGrades.getOrDefault(student, 0);
    }

    public Map<Student, Integer> getStudentGrades() {
        return studentGrades;
    }

    // View enrolled students
    public void viewenrolled() {
        if (enrolled.isEmpty()) {
            System.out.println("No students are currently enrolled.");
        } else {
            for (Student student : enrolled) {
                System.out.println("Student Name: " + student.getName() + ", Roll No: " + student.getID() + ", Email: " + student.getEmail());
            }
        }
    }
}