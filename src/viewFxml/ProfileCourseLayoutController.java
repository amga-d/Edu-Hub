package viewFxml;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import model.Course;

public class ProfileCourseLayoutController {

    @FXML
    private Label courseDescription;

    @FXML
    private ImageView courseImage;

    @FXML
    private Label courseName;

    public void initialCompletedCourse(Course course){
        courseName.setText(course.getCourseName());
        courseDescription.setText(course.getCourseDescription());
        courseImage.setImage(course.getImage());
    }
    

 
}
