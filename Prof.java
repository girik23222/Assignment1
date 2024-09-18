package Model;
// import java.util.ArrayList;
import java.util.List;
import Model.*;

public class Prof extends User
{
    private Course course = null;

    

    public Prof(String email, String password, String name, Course course)
    {
        super(email, password, name);
        this.course = course ;
    }

    //getter method for course
    public Course getCourse()
    {
        return course;
    }
    public void setCourse(Course course)
    {
        this.course = course;
    }

    public void assignGrade(Student student, int grade) 
    {
        if (course == null) 
        {
            System.out.println("Professor is not assigned to any course.");
            return;
        }
        if (student == null) 
        {
            System.out.println("Student is not assigned to any course.");
            return;
        }
        if(grade>10 || grade<3)
        {
            System.out.println("Invalid grade");
        }
        if (course.getStudents().contains(student)) 
        {
            course.setGrade(student, grade);
            System.out.println("Grade " + grade + " assigned to student " + student.getName() + " for course " + course.getTitle());

            // if all currentCourses of student are graded, student completed the semester
            for(Course c: student.getCurrent())
            {
                if(!(c.getGrade(student)>4 && c.getGrade(student)<=10))
                {
                    return;
                }
            }
            student.setSemester(student.getSemester()+1);
            // System.out.println("Student " + student.getName() + " has completed semester " + student.getSem() + " for course " + course.getTitle());
            student.completedSemester();
        } 

        else 
        {
            System.out.println("Student " + student.getName() + " is not enrolled in the course " + course.getTitle() + ".");
        }
    }

    // Manage course details
    public void updateCredits(int credits) 
    {
        if (course != null) 
        {
            // credits should be 2 or 4
            if (credits!= 2 || credits!= 4)
            {
                System.out.println("Wrong credits (Credits can be 2 or 4");
                return;
            }
            this.course.setCredits(credits);
            System.out.println("Course credits updated ");
        } 
        else 
        {
            System.out.println("No course assigned.");
        }
    }

     public void updateTimings(String timings)
     {
         if (course != null)
         {
             this.course.setTimings(timings);
             System.out.println("Timings updated for " + this.course.getCode());
         }
         else
         {
             System.out.println("No course assigned.");
         }
     }



    public void updateLimit(int limit)
    {
        if (course != null) 
        {
            course.setLimit(limit);
            System.out.println("Max Enrolments to " + limit);
        }
        else
        {
            System.out.println("No course assigned.");
        }
    }

    // View enrolled students
    public void viewEnrolledStudents() {
        if (course != null) {
            System.out.println("Students enrolled in course " + this.course.getCode() + ":");
            System.out.println();

            // Get the list of students enrolled in the course
            List<Student> students = this.course.getStudents();

            if (students != null && !students.isEmpty()) {
                for (Student student : students) {
                    // Print student details, assuming there's a method to get the student name or ID
                    System.out.println("ID: " + student.getID() + ", Name: " + student.getName());
                }
            } else {
                System.out.println("No students are currently enrolled in this course.");
            }
        } else {
            System.out.println("No course assigned.");
        }
    }


    //Method to view course details
    public void viewCourseDetails() 
    {
        if (course != null) 
        {
            System.out.println("Course Code: " + course.getCode());
            System.out.println("Course Title: " + course.getTitle());
            System.out.printf("Course Credits: %d\n", course.getCredits());
            System.out.println("Prerequisites: " + course.getPrereq());
            System.out.println("Course Max enrollment : "+course.getLimit());
            System.out.println("Course Timings : "+course.getTimings());
        } 
        else 
        {
                System.out.println("No course assigned.");
        }
    }
}