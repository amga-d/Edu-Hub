package viewFxml;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import model.Account;
import model.Course;
import model.User;

public class CourseLayoutController implements Initializable {

    @FXML
    private Label courseDesciption;

    @FXML
    private ImageView courseImage;

    @FXML
    private Label courseName;

    @FXML
    private Pane opencourse;

    @FXML
    private Label rate;

    @FXML
    private Label tag;

    private Pane enrollPane;
    private Course course;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void setCourseInfo(Course course, Pane enrollPane,User user) {
        courseName.setText(course.getCourseName());
        courseDesciption.setText(course.getCourseDescription());
        rate.setText(course.getCourseRating() + "");
        tag.setText(course.getTag());
        courseImage.setImage(course.getImage());
        setEnrollPane(enrollPane);
        setCourse(course);
        handleOpenCourse(user);
    }

    public void handleOpenCourse(User user) {
        opencourse.setOnMouseClicked(e -> {
            ObservableList<Node> Children = enrollPane.getChildren();
            ((ImageView) Children.get(0)).setImage(course.getImage());
            ((Label) Children.get(1)).setText(course.getCourseName());
            ((Label) Children.get(2)).setText(course.getCourseDescription());
            ((Label) Children.get(3)).setText(course.getTag());
            
            if (user.getRegisteredCoursesId().contains(course.getCourseId())) {
                Children.get(5).setVisible(true);
                Children.get(4).setVisible(false);
            }
            else{
                Children.get(5).setVisible(false);
                Children.get(4).setVisible(true);
            }
            enrollPane.setVisible(true);
            enrollPane.getScene().setOnMousePressed(event -> {
                if (enrollPane.isVisible()) {
                    if (!enrollPane.contains(event.getX(), event.getY())
                    ) {
                        enrollPane.setVisible(false);
                    }
                }
            });

        });

    }

    public void setEnrollPane(Pane enrollPane) {
        this.enrollPane = enrollPane;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

}
