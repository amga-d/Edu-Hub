package service;
import java.util.List;

import model.Account;
import model.Course;
import model.Instructor;
import model.Material;
import model.Quiz;
import model.User;

public interface CourseService {

    void createCourse(Instructor instructor, String courseName, String courseDescription, String courseCategory, String imagePath);
    void deleteCourse(Course course);
    void registerUserToCourse(User user, Course course);
    void unregisterUserFromCourse(User user, Course course);
    Course getCourseByName(String courseName);
    List<Course> getCoursesByInstructor(Instructor instructor);
    List<Course> getCoursesByUser(User user);
    List<Course> getAllCourses();
    List<Course> getRandomCourses(int num);
    List<Course> getCourseByCategory(String category);
    AccountService getAccountService();
    void deletUserCourses(User user);

    void createQuiz(Course course ,String title,String question,String[]choices,int rightAnswer);
    void createMaterial(Course course ,String title,String content);
    void updateQuizCompletion(User user, Quiz quiz);
    void updateMaterialCompletion(User user, Material material);
    boolean isQuizCompleted(User user, Quiz quiz);
    boolean isMaterialCompleted(User user, Material material) ;
    void deleteMaterial(Course course, Material material);


    // List<Account>getUsersRegisterdToCourse(Course course);
    // void createCourse(Instructor instructor, String courseName, String courseDescription,String tag, String imagePath);
    // void deleteCourse(Course course);

    // // void addCourse(Course course);
    // // void removeCourse(Course course);

    // List<Course> getAllCourses();
    // Course getCourseByName(String courseName);

    // void setAccountService(AccountService accountService);
    // void registerUserToCourse( User user, Course course);
    // void unregisterUserFormCourse( User user, Course course);
    // double getUserProgress(Course course, User user);

}
