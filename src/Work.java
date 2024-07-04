
import java.time.LocalDate;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Account;
import model.Course;
import model.User;
import service.AccountService;
import service.AccountServiceImpl;
import service.CourseService;
import service.CourseServiceImpl;
import viewFxml.MainController;

public class Work extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage arg0) throws Exception {
        AccountService accountService = new AccountServiceImpl();
        CourseService courseService = new CourseServiceImpl();
        courseService.setAccountService(accountService);

        Course math = new Course("Mathematic", "basic math ");
        Course physics = new Course("Physics", "basic physics");
        
        courseService.addCourse(physics);
        courseService.addCourse(math);

        Account account = new User("8", "8", "agsf", true, LocalDate.now(), "Student");
        Account account2 = new User("8", "8", " 333", true, LocalDate.now(), "Student");

        User student =((User)account);
        accountService.createAccount(account);
        accountService.createAccount(account2);


        courseService.registerUser(physics, student);
        // student.addCourse(physics);


        FXMLLoader loader = new FXMLLoader(getClass().getResource("viewFxml/Main.fxml"));
        Parent root = loader.load();

        MainController mainController = loader.getController();
        mainController.setAccount(account,accountService);

        Scene s = new Scene(root);
        arg0.setScene(s);
        arg0.show();
        arg0.setFullScreen(true);
    }

}
