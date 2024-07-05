package viewFxml;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.Course;
import service.CourseService;
import service.CourseServiceImpl;

public class MentorDashBoardController implements Initializable {

    @FXML
    private TableView<Course> courseTableView;
    @FXML
    private TableColumn<Course, String> courseNameColumn;
    @FXML
    private TableColumn<Course, Integer> totalUsersColumn;
    @FXML
    private TableColumn<Course, String> courseCategoryColumn;
    @FXML
    private Label courseNameLabel;
    @FXML
    private Label courseDescriptionLabel;
    @FXML
    private Label courseRatingLabel;
    @FXML
    private Label totalUsersLabel;
    @FXML
    private Label courseCategoryLabel;
    @FXML
    private ImageView courseImage;
    

    private CourseService courseService;
    private ObservableList<Course> courseList;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        courseService = new CourseServiceImpl();
        courseList = FXCollections.observableArrayList(courseService.getRandomCourses(10));

        courseNameColumn.setCellValueFactory(new PropertyValueFactory<>("courseName"));
        totalUsersColumn.setCellValueFactory(
                cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getRegisteredUsers().size()));

        courseCategoryColumn.setCellValueFactory(new PropertyValueFactory<>("tag"));

        courseTableView.setItems(courseList);

        courseTableView.getSelectionModel().selectedItemProperty().addListener(
            (observable, oldValue, newValue) -> updateLabels(newValue)
        );

    }
    private void updateLabels(Course course) {
        if (course != null) {
            courseNameLabel.setText(  course.getCourseName());
            courseDescriptionLabel.setText( course.getCourseDescription());
            courseRatingLabel.setText(course.getCourseRating()+"");
            totalUsersLabel.setText(course.getRegisteredUsers().size()+"");
            courseCategoryLabel.setText(course.getTag());
            Image temporary = new Image(getClass().getResourceAsStream("../Resources/loading_pic.png"));
            courseImage.setImage(temporary);

        } else {
            courseNameLabel.setText("");
            courseDescriptionLabel.setText("");
            courseRatingLabel.setText("");
            totalUsersLabel.setText("");
            courseCategoryLabel.setText("");
        }
    }
}
