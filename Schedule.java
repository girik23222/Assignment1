package Model;

public class Schedule {
    // Attributes
    private String day;
    private int startTime;
    private int endTime;
    private String location;

    // Constructor
    public Schedule(String day, int startTime, int endTime, String location) {
        this.day = day;
        this.startTime = startTime;
        this.endTime = endTime;
        this.location = location;
    }

    // Getter for day
    public String getDay() {
        return day;
    }

    // Setter for day
    public void setDay(String day) {
        this.day = day;
    }

    // Getter for startTime
    public int getStartTime() {
        return startTime;
    }

    // Setter for startTime
    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    // Getter for endTime
    public int getEndTime() {
        return endTime;
    }

    // Setter for endTime
    public void setEndTime(int endTime) {
        this.endTime = endTime;
    }

    // Getter for location
    public String getLocation() {
        return location;
    }

    // Setter for location
    public void setLocation(String location) {
        this.location = location;
    }

    // Helper method to format the time as HH:mm
    private String formatTime(int time) {
        int hours = time / 100;
        int minutes = time % 100;
        return String.format("%02d:%02d", hours, minutes);
    }

    // toString method for easy printing
    @Override
    public String toString() {       
        return "Day = " + day + ", Time: " + formatTime(startTime) + " - " + formatTime(endTime) + ", Location: " + location;
    }
}