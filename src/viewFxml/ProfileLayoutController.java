package viewFxml;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.LoadException;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import model.Course;
import model.User;
import service.AccountService;
import service.CourseService;

public class ProfileLayoutController implements Initializable {

    @FXML
    private Label accountName;

    @FXML
    private Label accountName1;

    @FXML
    private Button achievementButton;

    @FXML
    private Button deleteAccountButton;

    @FXML
    private Button editaccountburtton;

    @FXML
    private Button editaccountburtton1;

    @FXML
    private ImageView profileImage;

    @FXML
    private BorderPane borderPane;

    @FXML
    private VBox completedCoursesBox;

    private User user;
    private CourseService courseService;
    private AccountService accountService;

    private Node achievementPane;

    private MainController parentController;

    private List<Course> completedCourses;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    public void handleAchievementButton() {
        initialAchievmentPage();
        borderPane.setCenter(achievementPane);
    }

    @FXML
    private void handleEditAccountButton() {
        EditAccountController controller = changeCenter("EditAccountLayout.fxml").getController();
        controller.initailEditPage(user, accountService);
        controller.setParentController(this);
        clickedbox(editaccountburtton);
    }

    @FXML 
    private void handleDeleteAccountButton(){
        DeleteAccountLayoutController controller =changeCenter("DeleteAccountLayout.fxml").getController();
        controller.initialDeletingAccountPage(parentController,accountService,user,this);
        clickedbox(deleteAccountButton);

    }

    private FXMLLoader changeCenter(String filepath) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(filepath));
        Parent root;
        try {
            root = loader.load();
            borderPane.setCenter(root);
            return loader;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;

    }

    public void updateUser() {
        parentController.updateUser();
        initialAchievmentPage();
        borderPane.setCenter(achievementPane);

    }


    private void initialAchievmentPage() {
        accountName.setText(user.getName());
        accountName1.setText(user.getName());
        clipImage(user.getImage());
        clickedbox(achievementButton);
        displayCompletedCourses(completedCourses);

    }

    private void clipImage(Image image) {
        profileImage.setImage(image);
        double radius = (Math.min(profileImage.getFitHeight(), profileImage.getFitWidth())) / 2;
        Circle clipCircle = new Circle(radius, radius, radius);
        profileImage.setClip(clipCircle);
    }

    private void clickedbox(Button selectedButton) {
        achievementButton.getStyleClass().remove("Selected");
        editaccountburtton.getStyleClass().remove("Selected");
        editaccountburtton1.getStyleClass().remove("Selected");
        deleteAccountButton.getStyleClass().remove("Selected");

        selectedButton.getStyleClass().add("Selected");

    }

    public void setParentController(MainController mainController) {
        this.parentController = mainController;
    }

    private void displayCompletedCourses(List<Course> courses){
        if (!courses.isEmpty()) {
            completedCoursesBox.getChildren().clear();
            try {
                for (int i = 0; i < courses.size(); i++) {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("ProfileCourseLayout.fxml"));
                    HBox completedCourse = loader.load();
                    ProfileCourseLayoutController controller = loader.getController();
                    controller.initialCompletedCourse(courses.get(i));
                    completedCoursesBox.getChildren().add(completedCourse);
                }

            } catch (Exception e) {
                // TODO: handle exception
            }
        }
    }

    public void initialProfile(User user, List<Course> completedCourses, AccountService accountService) {
        this.user = user;
        this.courseService = courseService;
        this.accountService = accountService;
        this.completedCourses = completedCourses;
        initialAchievmentPage();
        achievementPane = borderPane.getCenter();
    }

}
