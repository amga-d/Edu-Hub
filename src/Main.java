import service.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import viewFxml.loginController;

public class Main extends Application {
    
    AccountService accountService = new AccountServiceImpl();


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("viewFxml/login.fxml")); 
        Parent root = loader.load();
        loginController logincontroller = loader.getController();
        logincontroller.setAccountService(accountService);
        logincontroller.load();
        
        root.setClip(createRoundedRectangle(900, 600, 30));

        Scene scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT);

        Image icon = new Image(getClass().getResourceAsStream("/Resources/logo.jpeg"));

        primaryStage.setScene(scene);
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.setTitle("edu-Hub");
        primaryStage.getIcons().add(icon);
        primaryStage.show();
    }

    private javafx.scene.shape.Rectangle createRoundedRectangle(double width, double height, double radius) {
        javafx.scene.shape.Rectangle rectangle = new javafx.scene.shape.Rectangle(width, height);
        rectangle.setArcWidth(radius);
        rectangle.setArcHeight(radius);
        return rectangle;
    }


    /*
    TODO :
    1. the profile page for user which has there menu
        a. (Achievement) which has the completed courses pane
        b. edit information 
        c. delete Account
    2. edit course Information by Insturctor 
    3. create the Admin interface with all needed pages
    3. Delete the chat Feature
    4. add the searchbar
    5.change the structure of of the material and quizzes to be combained on one class called lesson
    */

}
