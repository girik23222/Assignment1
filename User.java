package Model;
public abstract class User {
    private String email;
    private String password;
    private String name;

    public User(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
    }

    public void logout() {
        System.out.println(" Successfully logged out.");
    }

    public String getEmail() { //Encapsulation
        return email;
    }
    public String setEmail(String email) { //Encapsulation
        this.email = email;
        return email;
    }
    public String setName(String name) { //Encapsulation
        this.name = name;
        return name;
    }
    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }
}