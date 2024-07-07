
import java.time.LocalDate;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
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
        Account account = accountService.getAccountById("am@1");
        FXMLLoader loader = new FXMLLoader (getClass().getResource("viewFXML/Main.fxml"));
            Parent mainPage = loader.load();
            MainController mainController = loader.getController();
            mainController.setAccount(account,accountService);
        // Parent rooParent = FXMLLoader.load(getClass().getResource("viewFxml/MentorMenu.fxml"));
        Scene scene = new Scene(mainPage);
        arg0.setScene(scene);
        arg0.show();
        arg0.setFullScreen(true);
        // arg0.setFullScreen(true);
    }
    // @Override
    // public void start(Stage arg0) throws Exception {
    //     AccountService accountService = new AccountServiceImpl();
    //     CourseService courseService = new CourseServiceImpl();
    //     courseService.setAccountService(accountService);

    
    //     Course math = new Course("Mathematic", "basic math ");
    //     Course physics = new Course("Physics", "basic physics");
        

    //     courseService.addCourse(new Course("djksfsdkf", "dfsdf", 5, "Mathematics",""));
    //     courseService.addCourse(physics);
    //     courseService.addCourse(math);
    //     courseService.addCourse(new Course("Calculs", "Basicde efeewfew"
    //     , 5, "Law",""));

    //     Account account = new User("8", "8", 
    //     "agsf", true, LocalDate.now(), "Student");
    //     Account account2 = new User("8", "8", " 333", true, LocalDate.now(), "Student");

    //     User student =((User)account);
    //     accountService.createAccount(account);
    //     accountService.createAccount(account2);


    //     // courseService.registerUser(physics, student);
    //     // student.addCourse(physics);


    //     FXMLLoader loader = new FXMLLoader(getClass().getResource("viewFxml/Main.fxml"));
    //     Parent root = loader.load();

    //     MainController mainController = loader.getController();
    //     mainController.setAccount(account,accountService);

    //     // root = FXMLLoader.load(getClass().getResource("scrollpane.fxml"));
    //     Scene s = new Scene(root);
    //     // ((ScrollPane)root).setPannable(true);
    //     arg0.setScene(s);
    //     arg0.show();
    //     // arg0.setFullScreen(true);
    //     // arg0.setFullScreen(true);
    // }

}
