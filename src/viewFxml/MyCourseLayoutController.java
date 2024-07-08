package viewFxml;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import model.Course;
import model.User;

public class MyCourseLayoutController implements Initializable{
    
    
    @FXML
    private Label courseDesciption;

    @FXML
    private ImageView courseImage;

    @FXML
    private Label courseName;

    @FXML
    private Pane opencourseButton;

    @FXML
    private ProgressBar progressBar;

    @FXML
    private HBox root;

    @FXML
    private Label tag;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }

  

    public void updateContent(Course course,User user) {
        courseName.setText(course.getCourseName());
        courseDesciption.setText(course.getCourseDescription());
        tag.setText(course.getTag());
        progressBar.setProgress(course.countUserProgress(user));
        courseImage.setImage(course.getImage());
 
    }
    
}
