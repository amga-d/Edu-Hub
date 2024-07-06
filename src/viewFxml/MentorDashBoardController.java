package viewFxml;

import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import model.Account;
import model.Course;
import service.AccountService;
import service.CourseService;
import service.CourseServiceImpl;

public class MentorDashBoardController implements Initializable {

    @FXML
    private TextField CourseCategoryCreate;
    @FXML
    private Label worning;

    @FXML
    private TextField courseDescriptionCreate;

    @FXML
    private TextField courseCategoryCreate;

    @FXML
    private ImageView courseImageCreate;

    @FXML
    private TextField courseNameCreate;

    @FXML
    private Button createButton;

    @FXML
    private Button newCourseButton;

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
    @FXML
    private Pane createCoursePane;
    @FXML
    private ImageView closeButton;

    private String absolutePath;
    private CourseService courseService;
    private Account account;
    private ObservableList<Course> courseList;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        courseService = new CourseServiceImpl();
        courseList = FXCollections.observableArrayList(courseService.getAllCourses());

        courseNameColumn.setCellValueFactory(new PropertyValueFactory<>("courseName"));
        totalUsersColumn.setCellValueFactory(
                cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getRegisteredUsers().size()));

        courseCategoryColumn.setCellValueFactory(new PropertyValueFactory<>("tag"));

        courseTableView.setItems(courseList);

        courseTableView.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> updateLabels(newValue));

        closeButton.setOnMouseClicked(e -> {
            createCoursePane.setVisible(false);
            courseNameCreate.setText("");
            courseDescriptionCreate.setText("");
            courseCategoryCreate.setText("");
        });

        courseImageCreate.setOnMouseClicked(e -> {
            changeImage();
        });
    }

    @FXML
    public void handleNewCourseButton() {
        createCoursePane.setVisible(true);
    }

    @FXML
    public void hundleCreateAccountButton() {
        String courseName = courseNameCreate.getText();
        String courseDescription = courseDescriptionCreate.getText();
        String courseCategory = courseCategoryCreate.getText();
        if (courseName.isEmpty() || courseDescription.isEmpty() || courseCategory.isEmpty()) {
            worning.setText("Enter Course Information!!");
        } else {
            if (absolutePath == null){
                worning.setText("Upload Course Image");
            } else {
                Course course = new Course(courseName, courseDescription, 5, courseCategory,
                        absolutePath);

                courseService.addCourse(course);
                updateTableViewContent(courseService.getAllCourses());

                createCoursePane.setVisible(false);
                courseNameCreate.setText("");
                courseDescriptionCreate.setText("");
                courseCategoryCreate.setText("");
            }

        }
    }

    public void updateTableViewContent(List<Course> updatedCourses) {
    courseList.setAll(updatedCourses);
    courseTableView.refresh();
}

    private void changeImage() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Image File");
        fileChooser.getExtensionFilters().addAll(
        new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
        fileChooser.setInitialDirectory(new File("src/Resources"));
        File selectedFile = fileChooser.showOpenDialog(createButton.getScene().getWindow());

        if (selectedFile != null) {
            absolutePath = selectedFile.toURI().toString();
            courseImageCreate.setImage(new Image(absolutePath));
        }
    }

    private void updateLabels(Course course) {
        if (course != null) {
            courseNameLabel.setText(course.getCourseName());
            courseDescriptionLabel.setText(course.getCourseDescription());
            courseRatingLabel.setText(course.getCourseRating() + "");
            totalUsersLabel.setText(course.getRegisteredUsers().size() + "");
            courseCategoryLabel.setText(course.getTag());

            try {
                courseImage.setImage(course.getImage());
            } catch (Exception e) {
                courseImage.setImage(
                        new Image(getClass().getResourceAsStream("..//Resources/Courses_Images/NO_IMAGE.png")));
                e.printStackTrace();
            }

        } else {
            courseNameLabel.setText("");
            courseDescriptionLabel.setText("");
            courseRatingLabel.setText("");
            totalUsersLabel.setText("");
            courseCategoryLabel.setText("");

        }
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
