package service;

import model.Course;
import model.Account;
import model.Instructor;
import model.Material;
import model.Quiz;
import model.User;
import model.CourseModel;
import model.AccountModel;
import service.CourseService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CourseServiceImpl implements CourseService {

    private static List<Course> courses;
    private List<Account> accounts;

    private CourseModel courseModel;
    private AccountService accountService;

    public CourseServiceImpl(AccountService accountService) {
        this.courseModel = new CourseModel();
        this.accountService = accountService;
        this.courses = courseModel.loadCourses();
        this.accounts = accountService.getAllAccounts();
        updateCountId();
    }

    public AccountService getAccountService() {
        return this.accountService;
    }

    private void saveCourses() {
        courseModel.saveCourses(courses);
    }

    private void updateCountId() {
        if (!courses.isEmpty()) {
            String idstr = courses.get(courses.size() - 1).getCourseId();
            int indx = idstr.indexOf("@");
            int id = Integer.parseInt(idstr.substring(indx + 1));
            Course.setCountId(id);
        }
    }

    @Override
    public void updateQuizCompletion(User user, Quiz quiz) {
        quiz.setCompleted(user);
        saveCourses();
    }

    @Override
    public void updateMaterialCompletion(User user, Material material) {
        material.setCompleted(user);
        saveCourses();
    }

    @Override
    public boolean isQuizCompleted(User user, Quiz quiz) {
        return quiz.isCompleted(user);
    }

    @Override
    public boolean isMaterialCompleted(User user, Material material) {
        return material.isCompleted(user);
    }

    @Override
    public void createCourse(Instructor instructor, String courseName, String courseDescription, String courseCategory,
            String imagePath) {
        Course course = new Course(courseName, courseDescription, courseCategory, imagePath, instructor);
        courses.add(course);
        instructor.addCourse(course);
        saveCourses();
        accountService.saveAccounts(); // Save accounts to update instructor's courses
    }

    @Override
    public void deleteCourse(Course course) {
        courses.remove(course);
        Instructor instructor = (Instructor) accountService.getAccountById(course.getInstructorId());
        instructor.removeCourse(course);

        List<String> userIds = course.getRegisteredUserIds();
        for (Account account : accounts) {
            if (account instanceof User) {
                User user = (User) account;
                if (userIds.contains(user.getId())) {
                    user.unregisterCourse(course);
                }
            }
        }

        saveCourses();
        accountService.saveAccounts(); // Save accounts to update all users' courses
    }

    @Override
    public List<Course> getAllCourses() {
        return courses;
    }

    @Override
    public Course getCourseByName(String courseName) {
        for (Course course : courses) {
            if (course.getCourseName().equals(courseName)) {
                return course;
            }
        }
        return null;
    }

    @Override
    public List<Course> getCoursesByInstructor(Instructor instructor) {
        List<Course> instructorCourses = new ArrayList<Course>();
        for (Course course : courses) {
            if (course.getInstructorId().equals(instructor.getId())) {
                instructorCourses.add(course);
            }
        }
        return instructorCourses;
    }

    @Override
    public void registerUserToCourse(User user, Course course) {
        course.registerUser(user);
        user.registerCourse(course);
        saveCourses();
        accountService.saveAccounts();
    }

    @Override
    public void unregisterUserFromCourse(User user, Course course) {
        course.unregisterUser(user);
        user.unregisterCourse(course);
        saveCourses();
        accountService.saveAccounts();
    }

    @Override
    public List<Course> getRandomCourses(int num) {
        List<Course> shuffledList = new ArrayList<>(courses);
        Collections.shuffle(shuffledList);
        if (courses.size() == 0 || num <= 0) {
            return null;
        }
        if (num > courses.size()) {
            num = courses.size();
        }
        return shuffledList.subList(0, num);

    }

    @Override
    public List<Course> getCoursesByUser(User user) {
        List<Course> userCourses = new ArrayList<Course>();
        String userId = user.getId();
        user.getRegisteredCoursesId();
        for (Course course : courses) {
            if (course.getRegisteredUserIds().contains(userId)) {
                userCourses.add(course);
            }
        }
        return userCourses;
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

    @Override
    public void deletUserCourses(User user) {
        List<Course> coursesByUser = getCoursesByUser(user);
        if (!coursesByUser.isEmpty()) {
            for (Course course : coursesByUser) {
                course.removeUserCompletionStatus(user);
            }
            saveCourses();
        }
    }

    @Override
    public void createMaterial(Course course, String title, String content) {
        course.addMaterial(new Material(title, content));
        saveCourses();
    }

    @Override
    public void createQuiz(Course course, String title, String question, String[] choices, int rightAnswerIndx) {
        course.addQuiz(new Quiz(title, question, choices, rightAnswerIndx));
        saveCourses();
    }

    @Override
    public void deleteMaterial(Course course, Material material) {
        course.removeMaterials();
        saveCourses();
    }

}

// @Override
// public List<Account> getUsersRegisterdToCourse(Course course) {
// List<String> userIds = course.getRegisteredUserIds();
// List<Account> registeredUsers = new ArrayList<>();
// for (Account account : accounts) {
// if (account instanceof User) {
// User user = (User) account;
// if (userIds.contains(user.getId())) {
// registeredUsers.add(account);
// }
// }
// }
// return registeredUsers;
// }
// private void establishRelationships() {
// // Establish relationships between courses and instructors
// for (Course course : courses) {
// for (Account account : accounts) {
// if (account instanceof Instructor) {
// Instructor instructor = (Instructor) account;
// if (course.getInstructorId().equals(instructor.getId())) {
// instructor.addCourse(course);
// course.setInstructor(instructor);
// }
// }
// }
// }

// // Establish relationships between courses and users
// for (Course course : courses) {
// for (Account account : accounts) {
// if (account instanceof User) {
// User user = (User) account;
// if (user.getRegisteredCourseIds().contains(course.getId())) {
// user.registerCourse(course);
// course.registerUser(user);
// }
// }

// }
// }
// }

// @Override
// public double getUserProgress(Course course, User user) {
// return course.countUserProgress(user);
// }

// }
// return categorizedCourses;
// }

// public void setAccountService(AccountService accountService){
// this.accountService = accountService;
// }
