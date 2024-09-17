package Model;
// import java.util.ArrayList;
import java.util.List;

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
            this.course.setCredits(credits);
            System.out.println("Course credits updated ");
        } 
        else 
        {
            System.out.println("No course assigned.");
        }
    }

    // public void updateTimings(int startTime, int endTime) 
    // {
    //     if (course != null) 
    //     {
    //         this.course.setStartTime(startTime);
    //         this.course.setEndTime(endTime);
    //         System.out.println("Timings updated for " + this.course.getCode());
    //     }
    //     else
    //     {
    //         System.out.println("No course assigned.");
    //     }
    // }

    public void updatePrerequisites(List<Course> prerequisites) 
    {
        if (course != null) 
        {
            this.course.setPrereq(prerequisites);
            System.out.println("Prerequisites updated for " + this.course.getCode());
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
    public void viewEnrolledStudents() 
    {
        if (course != null) 
        {
            System.out.println("Students enrolled in course  " + this.course.getCode() + ":");
            for (Student student : this.course.getStudents()) {
                System.out.println(student);
            }
        }
        else
        {
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
        } 
        else 
        {
                System.out.println("No course assigned.");
        }
    }
}