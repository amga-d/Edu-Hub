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
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import model.Account;
import model.Course;
import model.User;
import service.CourseService;
import service.CourseServiceImpl;

public class HomeController implements Initializable {
    @FXML
    HBox mostPopularCourse;
    @FXML
    HBox recommendedForYou;
    @FXML
    Pane enrollPane;

    @FXML
    private Button EnrollButton;

    @FXML
    private ImageView enrolledImage;

    private Account account;
    private CourseService courseService;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public void handleEnrollButton(ActionEvent e) {
        Course course = courseService.getCourseByName(((Label) enrollPane.getChildren().get(1)).getText());
        courseService.registerUserToCourse((User) account, course);
        EnrollButton.setVisible(false);
        enrolledImage.setVisible(true);
    }

    private void addCourse(Course course, HBox box) {
        VBox courseLayoutBox = loadCourselayout(course);
        box.getChildren().add((courseLayoutBox));
    }

    private VBox loadCourselayout(Course course) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("HomeCourseLayout.fxml"));
            VBox courseLayoutBox = (VBox) loader.load();
            CourseLayoutController courseLayoutController = loader.getController();
            courseLayoutController.setCourseInfo(course, enrollPane,(User)account);
            return courseLayoutBox;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }

    public void intiialHomePage() {
        List<Course> popularCourses = courseService.getRandomCourses(6);
        if (popularCourses != null) {
            for (int i = 0; i < popularCourses.size(); i++) {
                addCourse(popularCourses.get(i), mostPopularCourse);
            }

        }
        List<Course> recommendedCourses = courseService.getRandomCourses(6);
        if (recommendedCourses != null) {
            for (int i = 0; i < recommendedCourses.size(); i++) {
                addCourse(recommendedCourses.get(i), recommendedForYou);
            }

        }
    }

    public void setCourseService(CourseService courseService) {
        this.courseService = courseService;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

}
