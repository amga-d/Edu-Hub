package viewFxml;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import model.Course;

public class CourseLayoutController implements Initializable{
    
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
    

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void setCourseInfo(Course Course){
        courseName.setText(Course.getCourseName());
        courseDesciption.setText(Course.getCourseDescription());
        rate.setText(Course.getCourseRating()+"");
        tag.setText(Course.getTag());
        courseImage.setImage(Course.getImage());
    }
    
}
