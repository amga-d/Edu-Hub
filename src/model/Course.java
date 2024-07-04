package model;

import java.util.ArrayList;
import java.util.List;

public class Course {
    private String courseName;
    private String courseDescription;
    private double courseRating;
    private List<Quiz> quizzes;
    private List<Material> materials;
    private List<String> registeredUsers;
    private String courseId;

    static private int countId = 0;

    // Constructor
    public Course(String courseName, String courseDescription) {
        this.courseName = courseName;
        this.courseDescription = courseDescription;
        this.courseRating = 0.0;
        this.quizzes = new ArrayList<>();
        this.materials = new ArrayList<>();
        this.registeredUsers = new ArrayList<>();
        countId += 1;
        intialCourseId();
    }

    private void intialCourseId(){
        String idstr = courseName.substring(0, 2) + "@"+countId;
        this.courseId = idstr;
    }

    // Getters and Setters
    
    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseDescription() {
        return courseDescription;
    }

    public void setCourseDescription(String courseDescription) {
        this.courseDescription = courseDescription;
    }

    public double getCourseRating() {
        return courseRating;
    }

    public void setCourseRating(double courseRating) {
        this.courseRating = courseRating;
    }

    public List<Quiz> getQuizzes() {
        return quizzes;
    }

    public List<Material> getMaterials() {
        return materials;
    }

    public List<String> getRegisteredUsers() {
        return registeredUsers;
    }

    // Methods
    public void registerUser(User user) {
        if (!registeredUsers.contains(user)) {
            registeredUsers.add(user.getUserId());
            user.addCourse(this); // Add the course to the user's list
        }
    }

    public void unregisterUser(User user) {
        if (registeredUsers.contains(user)) {
            registeredUsers.remove(user.getUserId());
            user.removeCourse(this); // Remove the course from the user's list
        }
    }

    public double countUserProgress(User user) {
        // Implement logic to count user's progress based on completed quizzes and materials
        return 0.0; // Placeholder return value
    }

    public void addQuiz(Quiz quiz) {
        quizzes.add(quiz);
    }

    public void addMaterial(Material material) {
        materials.add(material);
    }

    public void setQuizzes(List<Quiz> quizzes) {
        this.quizzes = quizzes;
    }

    public void setMaterials(List<Material> materials) {
        this.materials = materials;
    }

    public void setRegisteredUsers(List<String> registeredUsers) {
        this.registeredUsers = registeredUsers;
    }

    public String getCourseId() {
        return courseId;
    }

    public static int getCountId() {
        return countId;
    }

    public static void setCountId(int countId) {
        Course.countId = countId;
    }

    
}
