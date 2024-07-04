package service;
import java.util.List;

import model.Course;
import model.User;

public interface CourseService {
    void addCourse(Course course);
    void removeCourse(Course course);
    List<Course> getAllCourses();
    Course getCourseByName(String courseName);
    void updateCourse(Course course);

    void setAccountService(AccountService accountService);
    void registerUser(Course course, User user);
    void unregisterUser(Course course, User user);
    double getUserProgress(Course course, User user);
}
