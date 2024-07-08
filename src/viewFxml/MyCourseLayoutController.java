package viewFxml;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import model.Course;
import model.User;
import service.CourseService;

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


  

    public void updateContent(Course course,User user,CourseService courseService) {

        courseName.setText(course.getCourseName());
        courseDesciption.setText(course.getCourseDescription());
        tag.setText(course.getTag());
        progressBar.setProgress(course.calculateProgress(user));
        courseImage.setImage(course.getImage());
        
        opencourseButton.setOnMouseClicked(e ->{
            handleOpenCoursePreview(course,user,courseService);
        });
        
    }

    public void handleOpenCoursePreview(Course course,User user,CourseService courseService){
        Parent borderPane ;
        borderPane = opencourseButton.getParent();
        for (int i = 0; i < 8 ; i++) {
            borderPane = borderPane.getParent();
        }
        

        FXMLLoader loader = new FXMLLoader(getClass().getResource("CoursePreviewLayout.fxml"));
        try {
            ((BorderPane)borderPane).setRight(null);
            ((BorderPane)borderPane).setCenter(loader.load());

            CoursePreviewController coursePreviewController=  loader.getController();
            coursePreviewController.initial(user ,course,courseService);
        } catch (IOException e) {

            e.printStackTrace();
        }
        
        
    }


  
    
    
    
}
