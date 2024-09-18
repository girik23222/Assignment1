package Model;

import Model.*;
import java.util.ArrayList;

import java.util.List;

//implementation of ID
//implementation of viewProfessor

public class Student extends User{ //Inheritance
    private int semester;
    private int ID;
    private List<Course> completed;
    private List<Course> current;
    public List<List<String>> complaints;

    public Student(String email, String password, String name, int ID){
        super(email, password, name);
        this.semester=1;
        this.ID = ID;
        this.completed = new ArrayList<>();
        this.current = new ArrayList<>();
        this.complaints = new ArrayList<>();

    }
    public void addComplaint(String description) {
        List<String> complaint = new ArrayList<>();
        complaint.add(description);  // Add description
        complaint.add("Pending");    // Add default status "Pending"
        complaints.add(complaint);   // Add the complaint to the complaints list
        System.out.println("Complaint Registered Successfully");
    }

    // Method to view all complaints
    public void viewComplaints() {
        if (complaints.isEmpty()) {
            System.out.println("No complaints available.");
        } else {
            for (int i = 0; i < complaints.size(); i++) {
                List<String> complaint = complaints.get(i);
                System.out.println("Complaint " + (i + 1) + ": " + complaint.get(0) + ", Status: " + complaint.get(1));
            }
        }
    }

    public int getID()
    {
        return this.ID;
    }
    public List<Course> getCurrent()
    {
        return this.current;
    }
    public int getSemester(){
        return this.semester;
    }
    public void setSemester(int semester){
        this.semester = semester;
    }

    private int getGrades(int semester)
    {
        // This method will return the grades of the student for a specific semester
        int grades = 0;
        for (Course course : this.completed)
        {
            if (course.getSem() == semester)
            {
                grades+=course.getGrade(this);
            }
        }
        return grades;
    }

   // for all courses grades
   private int getGrades()
   {
       // This method will return the grades of the student for a specific semester
       int grades = 0;
       for (Course course : this.completed)
       {
               grades+=course.getGrade(this);
       }
       return grades;
   }

    private int getCredits(int semester)
    {
        // This method will return the credits of the student for a specific semester 
        int credits=0;
        for (Course course : this.completed)
        {
            if (course.getSem() == semester)
            {
                credits+=course.getCredits();
            }
        }
        return credits;
    }

    private int getCredits()
    {
        // This method will return the all credits of the student
        int credits=0;
        for (Course course : this.completed)
        {
            credits+=course.getCredits();
        }
        return credits;
    }


    public double Cgpa()
    {
        // This method will calculate the CGPA of the student based on the completed courses
        double totalGrades = this.getGrades();
        int   totalCredits = this.getCredits();

        if (totalCredits==0) {
            System.out.println("There are no Course Credits!");
            return 0;
        }

        return totalGrades/totalCredits;
    }

    public double Sgpa(int semester)
    {
        // This method will calculate the SGPA of the student based on the completed courses of the last semester
        if (semester>=this.semester) 
        {
            System.out.println("Grades not received .");
            return 0;
        }
        
        double totalGrades = this.getGrades(semester);
        int   totalCredits = this.getCredits(semester);

        return totalGrades/totalCredits;
    }

    public void viewcompleted()
    {
        // This method will display the completed courses of the student with grade
        for (Course course : this.completed)
        {
                System.out.println(course.getTitle() + " " + course.getGrade(this));
        }
    }

    public void viewAvailableCourses(int semester)
    {
        // This method will display the details of the current courses of the student
        List<Course> courseList = DataManager.getInstance().getCourseList();
        for (Course course : courseList)
        {
            if (course.getSem()==semester)
            {
                System.out.println("Course Title: "+ course.getTitle());
                System.out.println("Course Code: "+ course.getCode());
                System.out.println("Professor Name: "+ course.getprof());
                System.out.printf("Credits: %d\n", course.getCredits());
                System.out.printf("Max Enrolments: %d\n", course.getLimit());
                System.out.println("Prerequisites: " + course.getPrereq());

            }
        }
    }
// See this part -----> TODO
    public void viewAvailableCourses()
    {
        // This method will display the details of the all courses of the student
        List<Course> courseList = DataManager.getInstance().getCourseList();
        for (Course course : courseList)
        {
            if (course.getSem()==this.semester)
            {
                System.out.println("Course Title: "+ course.getTitle());
                System.out.println("Course Code: "+ course.getCode());
                System.out.println("Professor Name: "+ course.getprof());
                System.out.printf("Credits: %d\n", course.getCredits());
                System.out.printf("Max Enrolments: %d\n", course.getLimit());
                System.out.println("Prerequisites: ");
                for (Course preq : course.getPrereq())
                {
                    System.out.println(preq.getCode());
                }
                System.out.println();

            }
        }
    }

    public void viewCourse(String courseCode)
    {
        // This method will display the details of a particular course
        List<Course> courseList = DataManager.getInstance().getCourseList();

        for (Course course : courseList)
        {
            if (course.getCode().equals(courseCode))
            {
                System.out.println("Course Title: "+ course.getTitle());
                System.out.println("Course Code: "+ course.getCode());
                System.out.println("Professor Name: "+ course.getprof());
                System.out.printf("Credits: %d\n", course.getCredits());
                System.out.printf("Max Enrolments: %d\n", course.getLimit());
//                System.out.println("Prerequisites: ");
                for (Course preq : course.getPrereq())
                {
                    System.out.println(preq.getCode());
                }
                System.out.println();
                    

            }
        }
    }

    public void viewGrades(int semester)
    {
        // This method will display the grades of the student for a specific semester
        if (semester>=this.semester) 
        {
            System.out.println("Grades not received .");
            return;
        }
        for (Course course : this.completed)
        {
            if(course.getSem()==semester)
            {
                System.out.println("Title: "+ course.getTitle() + ", Grade: " + course.getGrade(this));
            }
        }
    }

    public void viewGrades()
    {
        // This method will display the grades of the student for a specific semester
        for (Course course : this.completed)
        {
            System.out.println("Semester: "+ course.getSem()+ ", Title: "+ course.getTitle() + ", Grade: " + course.getGrade(this));
        }
    }
    public void viewSchedule() 
    {
        // This method displays the schedule of the courses the student is taking
        for (Course course: this.current){
            System.out.println("Weekly Schedule: ");
            System.out.println("Course Code :" + course.getCode());
            System.out.println("Course Location : " + course.getLocation());
            System.out.println("Course Timings " + course.getTimings());
            System.out.println();
        }
    }
    
    public void trackProgress(int semester)
    {
        // This method will display the progress of the student for a specific semester
//        if (semester>=this.semester)
//        {
//            System.out.println("Grades not received for this semester yet.");
//        }
        viewGrades(semester);
        System.out.println("SGPA of Student " + this.getName() + " is " + this.Sgpa(semester));
    }
    public void trackProgress()
    {
        // This method will display the progress of the student for all completed semesters
        viewGrades();
        System.out.println("CGPA of Student " + this.getName() + " is " + this.Cgpa());
    }
    public void registerCourse(String code) {
        // This method will register a course for the student
        List<Course> courseList = DataManager.getInstance().getCourseList();

        for (Course course : this.current) {
            if (course.getCode().equals(code)) {
                System.out.println("You are already registered for this course.");
                return;
            }
        }

        for (Course c : this.completed) {
            if (c.getCode().equals(code)) {
                System.out.println("You have already completed this course.");
                return;
            }
        }
        // Create a new course object
        Course course = null;
        for (Course c : courseList) {
            if (c.getCode().equals(code)) {
                course = c;
                break;
            }
        }
        if (course == null)
        {
            System.out.println("Wrong Course Code");
            return;
        }
        if(course.getSem()!=this.semester)
        {
            System.out.println("This Course is not available in this semester");
            return;
        }
        int totalCredits = this.current.stream().mapToInt(Course::getCredits).sum();
        if (totalCredits + course.getCredits() > 20) {
            System.out.println("You cannot register for more than 20 credits.");
            return;
        }

        if(!this.completed.containsAll(course.getPrereq())){
            System.out.println("You do not meet the prerequisites for this course.");
            return;
        }
        // Add the course to the overall courseList
        if(course.addStudent(this)){
            this.current.add(course);
            System.out.println("Course registered: " + course.getTitle());
            return;
        }
        else return;
    }
    
    public void dropCourse(String code) {
        for (Course course : this.current) {
            if (course.getCode().equals(code)) {
                this.current.remove(course);
                System.out.println("Course with code " + code + " dropped.");
                return;
            }
        }
        System.out.println("You are not enrolled in this course.");
    }

    public void completedSemester(){
        this.completed.addAll(this.current);
        this.current.clear();
    }

    @Override
    public String toString() {
        return "Name: " + this.getName() + ", ID: " + this.getID() + ", Email: " + this.getEmail();
    }
}