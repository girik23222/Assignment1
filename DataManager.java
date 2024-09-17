package Model;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataManager {
    // Singleton instance
    private static DataManager instance = null;

    // Data Storage
    private List<Course> courseList;
    private Map<String, String> userCredentials;  // Stores email and password
    private Map<String, Student> studentData;     // Maps email to Student object
    private Map<String, Prof> professorData; // Maps email to Prof object
    private Map<String, Admin> adminData;         // Maps email to Admin object

    // Private constructor to prevent instantiation from other classes
    private DataManager() {
        courseList = new ArrayList<>();
        userCredentials = new HashMap<>();
        studentData = new HashMap<>();
        professorData = new HashMap<>();
        adminData = new HashMap<>();
    }

    // Get the singleton instance
    public static DataManager getInstance() {
        if (instance == null) {
            instance = new DataManager();
        }
        return instance;
    }

    // Manage courses
    public void addCourse(Course course) {
        courseList.add(course);
    }

    public List<Course> getCourseList() {
        return courseList;
    }

    // User Registration and Management

    // Method to register a new user (Student, Prof, Admin)
    public <T extends User> void signUp(T user) {
        String email = user.getEmail();
        if (userCredentials.containsKey(email)) {
            System.out.println("User already registered with this email.");
            return;
        }

        // Store credentials and map to the respective user type
        userCredentials.put(email, user.getPassword());
        if (user instanceof Student) {
            studentData.put(email, (Student) user);
        } else if (user instanceof Prof) {
            professorData.put(email, (Prof) user);
        } else if (user instanceof Admin) {
            adminData.put(email, (Admin) user);
        }

        System.out.println("User " + user.getName() + " registered successfully!");
    }

    // Method for login
    public User login(String email, String password) {
        if (!userCredentials.containsKey(email)) {
            System.out.println("Email not found. Please Sign Up.");
            return null;
        }
        if (!userCredentials.get(email).equals(password)) {
            System.out.println("Invalid password.");
            return null;
        }

        User loggedInUser = getUserByEmail(email);
        if (loggedInUser != null) {
            System.out.println(loggedInUser.getName() + " logged in successfully.");
        }
        return loggedInUser;
    }

    // Password recovery (dummy implementation)
    public void forgotPassword(String email) {
        if (!userCredentials.containsKey(email)) {
            System.out.println("Email not found.");
            return;
        }

        // In a real system, we would send a password reset link
        System.out.println("Password reset instructions sent to " + email);
    }

    // General method to retrieve a user by email
    public User getUserByEmail(String email) {
        if (studentData.containsKey(email)) {
            return studentData.get(email);
        } else if (professorData.containsKey(email)) {
            return professorData.get(email);
        } else if (adminData.containsKey(email)) {
            return adminData.get(email);
        }
        return null; // User not found
    }

    // List all registered users by type
    public <T extends User> List<T> getAllUsers(Class<T> userType) {
        if (userType == Student.class) {
            return new ArrayList<>((Collection<? extends T>) studentData.values());
        } else if (userType == Prof.class) {
            return new ArrayList<>((Collection<? extends T>) professorData.values());
        } else if (userType == Admin.class) {
            return new ArrayList<>((Collection<? extends T>) adminData.values());
        }
        return new ArrayList<>(); // Empty list if type doesn't match
    }

    // List all registered users for debugging or admin purposes
    public void listRegisteredUsers() {
        for (String email : userCredentials.keySet()) {
            User user = getUserByEmail(email);
            if (user != null) {
                System.out.println("Registered User: " + user.getName());
            }
        }
    }
}
