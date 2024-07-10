import model.Account;
import model.Course;
import model.User;
import service.AccountService;
import service.AccountServiceImpl;
import service.CourseService;
import service.CourseServiceImpl;

public class TestingDataModel  {
    public static void main(String[] args) {
        AccountService accountService = new AccountServiceImpl();
        CourseService courseService = new CourseServiceImpl(accountService);

        // for (Account account : accountService.getAllAccounts()) {
        //    if (account instanceof User) {
        //     accountService.deleteUserAccount((User)account);
        //    }
        // }
        // for (Account account : accountService.getAllAccounts()) {
        //     System.out.println(account);
        //  }
        // // accountService.deleteUserAccount((User)accountService.getAccountById("Am@7"));

        // String[] q = {"expo","expo","expo","expo"};
        // for (Course course :courseService.getAllCourses() ) {
        //     courseService.createMaterial(course, "expo", "expo");
        //     courseService.createMaterial(course, "expo", "expo");
        //     courseService.createMaterial(course, "expo", "expo");
        //     courseService.createMaterial(course, "expo", "expo");
        //     // courseService.createQuiz(course, "expo", "expo" , q, 3);
        //     // courseService.deleteMaterial(course, null);
        //     System.out.println(course);
        // } 
        
        // System.out.println(courseService.getCourseByName("Civil Law"));
        
        // Course course = courseService.getCourseByName("Basic Chemistry"); 
        // System.out.println(course);

        // System.out.println(courseService.getCourseByName("biology"));
        // courseService.getCou
        
    }
}
