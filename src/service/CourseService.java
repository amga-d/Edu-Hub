package service;
import java.util.List;

import model.Account;
import model.Course;
import model.Instructor;
import model.User;

public interface CourseService {

    void createCourse(Instructor instructor, String courseName, String courseDescription, String courseCategory, String imagePath);
    void deleteCourse(Course course);
    void registerUserToCourse(User user, Course course);
    void unregisterUserFromCourse(User user, Course course);
    Course getCourseByName(String courseName);
    List<Course> getCoursesByInstructor(Instructor instructor);
    List<Course> getAllCourses();
    List<Course> getRandomCourses(int num);
    List<Course> getCourseByCategory(String category);
    
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
