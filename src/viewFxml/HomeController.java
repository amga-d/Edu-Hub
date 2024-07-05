package viewFxml;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.Course;
import service.CourseService;
import service.CourseServiceImpl;

public class HomeController implements Initializable {
    @FXML
    HBox mostPopularCourse;
    @FXML
    HBox recommendedForYou;

    CourseService courseService;

    

    @Override
    public void initialize(URL location, ResourceBundle resources) {
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
            courseLayoutController.setCourseInfo(course);
            return courseLayoutBox;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }
    

    public void intiialHomePage() {
        List<Course> popularCourses = courseService.getRandomCourses(5);
        for (int i = 0; i < popularCourses.size(); i++) {
            addCourse(popularCourses.get(i), mostPopularCourse);
        }
        List<Course> recommendedCourses = courseService.getRandomCourses(5);

        for (int i = 0; i < popularCourses.size(); i++) {
            addCourse(popularCourses.get(i), recommendedForYou);
        }
    }

    public void setCourseService(CourseService courseService) {
        this.courseService = courseService;
    }

}
