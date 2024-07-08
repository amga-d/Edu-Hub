package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javafx.scene.image.Image;

public class User extends Account {
    private String job;
    private boolean gender; // t: male , f:female
    private LocalDate date;
    private int age;

    private List<String> registeredCoursesId; // List of courses the user is enrolled in

    // Getters and Setters for fullName and address

    public User(String password, String email, String fullName, boolean gender, LocalDate date, String job,
            String profilePath) {
        super(fullName,password, email,profilePath,"Student");
        this.job = job;
        this.gender = gender;
        this.date = date;
        this.registeredCoursesId = new ArrayList<>();
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


    public void registerCourse(Course course) {
        if (!registeredCoursesId.contains(course.getCourseId())) {
            registeredCoursesId.add(course.getCourseId());
        }
    }

    public void unregisterCourse(Course course) {
        if (registeredCoursesId.contains(course.getCourseId())) {
            registeredCoursesId.remove(course.getCourseId());
        }
    }



    public List<String> getRegisteredCoursesId() {
        return registeredCoursesId;
    }

    public void setRegisteredCoursesId(List<String> registeredCoursesId) {
        this.registeredCoursesId = registeredCoursesId;
    }
    
    @Override
    public String toString() {
        return "Student{" + "id=" + super.getId() + ", name=" + super.getName() + ", gender"
        + gender + ", date=" + date + ", age=" + age + ", registeredCoursesId"
        + registeredCoursesId + '}';
    }

}
