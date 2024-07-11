package viewFxml;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import model.Course;
import model.User;
import service.CourseService;

public class MyLearningController implements Initializable {

    @FXML
    private Button btnCompleted;

    @FXML
    private Button btnInProgress;

    @FXML
    private ScrollPane completePane;

    @FXML
    private ScrollPane inProgressPane;

    @FXML
    private VBox inProgressCourseContainer;

    @FXML
    private VBox completedCourseContainer;



    private List<Course> completedCourses;
    private List<Course> inProgressCourses;
    private User user;

    private CourseService courseService;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        completedCourses = new ArrayList<>();
        inProgressCourses = new ArrayList<>();

        btnCompleted.getStyleClass().add("inactive");

        // Toggle button states on click
        btnInProgress.setOnAction(event -> {
            btnInProgress.getStyleClass().remove("inactive");
            btnCompleted.getStyleClass().add("inactive");
            completePane.setVisible(false);
            inProgressPane.setVisible(true);
        });

        btnCompleted.setOnAction(event -> {
            btnCompleted.getStyleClass().remove("inactive");
            btnInProgress.getStyleClass().add("inactive");
            completePane.setVisible(true);
            inProgressPane.setVisible(false);

        });

    }

    public void numberchildren(ActionEvent e){
        System.out.println("Number of children: " + inProgressCourseContainer.getChildren().size());
    }


    public void displayMyCourse(VBox container, List<Course> courses) {

        if (!courses.isEmpty()) {    
            try {   
                for (int i = 0; i < courses.size(); i++) {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("MyCourseLayout.fxml"));
                    HBox root = loader.load();
                    MyCourseLayoutController controller = loader.getController();
                    controller.updateContent(courses.get(i),user,courseService);
                    container.getChildren().add(root);
                }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        else{
            Label label = new Label("No courses found");
            container.getChildren().add(label);
        }

    }

    public void initialMyLearningPage(List<Course> mycourses, User user, CourseService courseService) {
        this.user = user;
        this.courseService = courseService;
        if (mycourses != null) {
            for (Course course : mycourses) {
                if (course.isCourseCompleteByUser(user)) {
                    completedCourses.add(course);
                } else {
                    inProgressCourses.add(course);
                }
            }
        }
        displayMyCourse(inProgressCourseContainer, inProgressCourses);
        displayMyCourse(completedCourseContainer, completedCourses);
    }
    

}
