package viewFxml;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import model.Account;
import model.Course;
import model.User;
import service.CourseService;

public class CoursesContorller implements Initializable {
    @FXML
    private VBox coursesScPane;
    @FXML
    private Button allButton;

    @FXML
    private Button architectureButton;

    @FXML
    private Button chemistryButton;

    @FXML
    private Button computerButton;

    @FXML
    private Button lawButton;

    @FXML
    private Button mathematicsButton;
    @FXML
    private Pane enrollPane;
    @FXML
    private Button EnrollButton;
    @FXML
    private ImageView enrolledImage;


    private Account account;
    private CourseService courseService;
    

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void handleEnrollButton(ActionEvent e){
        Course course = courseService.getCourseByName(((Label) enrollPane.getChildren().get(1)).getText());
        courseService.registerUserToCourse((User)account,course);
        EnrollButton.setVisible(false);
        enrolledImage.setVisible(true);
    }


    private void addCourses(List<Course> category) {
        coursesScPane.getChildren().clear();
        for (Course course : category) {
            HBox courseLayoutBox = loadCourselayout(course);
            coursesScPane.getChildren().add((courseLayoutBox));
        }

    }

    @FXML
    private void handleLawButton(ActionEvent e) {
        handleClick();
        lawButton.getStyleClass().add("selected");
        List<Course> category = courseService.getCourseByCategory("Law");
        addCourses(category);
    }

    @FXML
    private void handleAllButton(ActionEvent e) {
        handleClick();
        allButton.getStyleClass().add("selected");
        List<Course> category = courseService.getRandomCourses(12);
        addCourses(category);
    }

    @FXML
    private void handleComputerButton(ActionEvent e) {
        handleClick();
        computerButton.getStyleClass().add("selected");
        List<Course> category = courseService.getCourseByCategory("computer");
        addCourses(category);
    }

    @FXML
    private void handleMathematicsButton(ActionEvent e) {
        handleClick();
        mathematicsButton.getStyleClass().add("selected");
        List<Course> category = courseService.getCourseByCategory("mathematics");
        addCourses(category);
    }

    @FXML
    private void handlechemistryButton(ActionEvent e) {
        handleClick();
        chemistryButton.getStyleClass().add("selected");
        List<Course> category = courseService.getCourseByCategory("chemistry");
        addCourses(category);
    }

    @FXML
    private void handlearchitectureButton(ActionEvent e) {
        handleClick();
        architectureButton.getStyleClass().add("selected");
        List<Course> category = courseService.getCourseByCategory("architecture");
        addCourses(category);
    }

    private HBox loadCourselayout(Course course) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("CoCourseLayout.fxml"));
            HBox courseLayoutBox = (HBox) loader.load();
            CourseLayoutController courseLayoutController = loader.getController();
            courseLayoutController.setCourseInfo(course,enrollPane,(User)account);
            return courseLayoutBox;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }

    public void initialCoursePage() {
        allButton.getStyleClass().add("selected");

        List<Course> courses = courseService.getRandomCourses(12);
        addCourses(courses);
    }

    public void setCourseService(CourseService courseService) {
        this.courseService = courseService;
    }

    private void handleClick() {
        allButton.getStyleClass().remove("selected");
        computerButton.getStyleClass().remove("selected");
        mathematicsButton.getStyleClass().remove("selected");
        chemistryButton.getStyleClass().remove("selected");
        architectureButton.getStyleClass().remove("selected");
        lawButton.getStyleClass().remove("selected");

    }

    public void setAccount(Account account) {
        this.account =  account;
    }

}
