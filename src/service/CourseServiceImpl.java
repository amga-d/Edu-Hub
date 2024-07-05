package service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import model.Account;
import model.AccountModel;
import model.Course;
import model.CourseModel;
import model.User;

public class CourseServiceImpl implements CourseService {
    private CourseModel courseModel;
    private AccountService accountService;
    private List<Course> courses;

    public CourseServiceImpl() {
        courseModel = new CourseModel();
        courses = courseModel.loadCourses();
        updateCountId();
    }

    public void setAccountService(AccountService accountService){
        this.accountService = accountService;
    }

    private void updateCountId() {
        if (!courses.isEmpty()) {
            String idstr = courses.getLast().getCourseId();
            int indx = idstr.indexOf("@");
            int id = Integer.parseInt(idstr.substring(indx + 1));
            Course.setCountId(id);
        }
    }

    @Override
    public void addCourse(Course course) {
        // if (!courses.contains(course)) {
        // }
        courses.add(course);
        courseModel.saveCourses(courses);
    }

    @Override
    public void removeCourse(Course course) {
        courses.remove(course);
    }

    @Override
    public List<Course> getAllCourses() {
        return courses;
    }

    @Override
    public Course getCourseByName(String courseName) {
        for (Course course : courses) {
            if (course.getCourseName().equalsIgnoreCase(courseName)) {
                return course;
            }
        }
        return null;
    }

    @Override
    public void registerUser(Course course, User user) {
        course.registerUser(user);
        courseModel.saveCourses(courses);
        accountService.updateAccount(user);
    }

    @Override
    public void unregisterUser(Course course, User user) {
        course.unregisterUser(user);
        courseModel.saveCourses(courses);
        accountService.updateAccount(user);
    }

    @Override
    public double getUserProgress(Course course, User user) {
        return course.countUserProgress(user);
    }

    @Override
    public void updateCourse(Course course) {
        for (int i = 0; i < courses.size(); i++) {
            if (courses.get(i).getCourseId().equals(course.getCourseId())) {
                courses.set(i, course);
                break;
            }
        }
        courseModel.saveCourses(courses);
    }

    @Override
    public List<Course> getRandomCourses(int num) {
        List<Course> shuffledList = new ArrayList<>(courses);
        Collections.shuffle(shuffledList); 
        if (num > courses.size()) {
            num = courses.size();
        }
        return shuffledList.subList(0, num);
    }

    @Override
    public List<Course> getCourseByCategory(String category) {
        List<Course> categorizedCourses = new ArrayList<>();
        for (Course course : courses) {
            if (course.getTag().equalsIgnoreCase(category)) {
                categorizedCourses.add(course);
            }
        }
        return categorizedCourses;
    }

    
}