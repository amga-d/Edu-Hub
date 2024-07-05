package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class User extends Account {
    private String job;
    private boolean gender; // t: male , f:female
    private LocalDate date;
    private int age;
    private String profilePath;
    private List<String> enrolledCourses; // List of courses the user is enrolled in

    public User(String password, String email, String fullName, Boolean gender, LocalDate date, String job) {
        super(fullName,password, email);
        this.gender = gender;
        this.date = date;
        this.job = job;
        this.enrolledCourses = new ArrayList<>();
        countAge();
    }
    

    // Getters and Setters for fullName and address

    public User(String password, String email, String fullName, boolean gender, LocalDate date, String job,
            String profilePath) {
        super(fullName,password, email);
        this.job = job;
        this.gender = gender;
        this.date = date;
        this.profilePath = profilePath;
        this.enrolledCourses = new ArrayList<>();
        countAge();
    }

    private void countAge() {
        this.age = LocalDate.now().getYear() - date.getYear();
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getProfilePath() {
        return profilePath;
    }

    public void setProfilePath(String profilePath) {
        this.profilePath = profilePath;
    }

    public List<String> getEnrolledCourses() {
        return enrolledCourses;
    }

    // Methods
    public void addCourse(Course course) {
        String id = course.getCourseId();
        if (!enrolledCourses.contains(id)) {
            enrolledCourses.add(id);
        }
    }

    public void removeCourse(Course course) {
        enrolledCourses.remove(course);
    }

    public double getCourseProgress(Course course) {
        // TODO_Implement logic to get progress for a specific course
        return 0.0;
    }

    @Override
    public String toString() {
        return "Profile{" +
                "fullName='" +  
                ", job='" + job + '\'' +
                ", gender=" + gender +
                ", date=" + date +
                ", age=" + age +
                ", profilePath='" + profilePath + '\'' +
                '}';
    }

    public void setEnrolledCourses(List<String> enrolledCourses) {
        this.enrolledCourses = enrolledCourses;
    }

}
