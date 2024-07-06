import java.time.LocalDate;
import java.util.List;

import model.Account;
import model.Course;
import model.Instructor;
import model.User;
import service.AccountService;
import service.AccountServiceImpl;
import service.CourseService;
import service.CourseServiceImpl;

public class TestingDataModel {
    public static void main(String[] args) {
        AccountService accountService = new AccountServiceImpl();
        CourseService courseService = new CourseServiceImpl(accountService);
        // accountService.createAccount(new User("11", "11", "11", true, LocalDate.now(), "ss", ""));
        // accountService.createAccount(new User("111", "111", "111", true, LocalDate.now(), "ss", ""));  

       
        
        accountService.createAccount(new Instructor("100", "34323", "32332", false, LocalDate.now(), "dd", "s"));
        
        // Instructor instructor = (Instructor)accountService.getAccountById("32@2");
        // User user = (User)accountService.getAccountById("11@2");

        // courseService.createCourse(instructor, "geee", "ddsdfdsf", "law", "");

        // courseService.deleteCourse(courseService.getCourseByName("geee"));
        // courseService.deleteCourse(courseService.getCourseByName("geee"));
        // courseService.deleteCourse(courseService.getCourseByName("Basic Calculus"));
        List<Course> courses = courseService.getAllCourses();
        // courseService.deleteCourse(courses.getFirst());
        for (Course course : courses) {
            System.out.println(course);
        }


        
        // for (Account user1 : accountService.getAllAccounts()) {
        //     accountService.deleteAccount(user1);
        // }for (Account user1 : accountService.getAllAccounts()) {
        //     System.out.println(user1);
        // }

       
        // courseService.registerUserToCourse(user, );

       
        
        // courseService.registerUserToCourse(user, courses.getFirst());
    }
}
