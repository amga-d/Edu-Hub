package viewFxml;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import model.Account;
import model.Course;
import model.User;
import service.CourseService;

public class CoursePreviewController implements Initializable {

    @FXML
    private Label CourseName;

    @FXML
    private Label Quiz1;

    @FXML
    private Label Quiz2;

    @FXML
    private Label Quiz3;

    @FXML
    private Label Quiz4;

    @FXML
    private ImageView done;

    @FXML
    private ImageView done2;

    @FXML
    private ImageView done3;

    @FXML
    private ImageView done4;

    @FXML
    private ImageView doneQ1;

    @FXML
    private ImageView doneQ2;

    @FXML
    private ImageView doneQ3;

    @FXML
    private ImageView doneQ4;

    @FXML
    private Label lessonName;

    @FXML
    private Label lessonName2;

    @FXML
    private Label lessonName3;

    @FXML
    private Label lessonName4;

    @FXML
    private ImageView courseImage;

    @FXML
    private ProgressBar progressBar;

    @FXML
    private Label courseDescription;

    private CourseService courseService;
    private User user;
    private Course course;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void initial(User user, Course course, CourseService courseService) {
        this.courseService = courseService;
        this.user = user;
        this.course = course;

        updateContent();
        updateProgress();
        progressBar.setProgress(course.calculateProgress(user));

    }

    private void updateProgress() {
        Quiz1.setOnMouseClicked(e -> {
            if (!doneQ1.isVisible()) {
                courseService.updateQuizCompletion(user,course.getQuizzes().get(0) );
                progressBar.setProgress(course.calculateProgress(user));
                
                doneQ1.setVisible(true);
            }
        });
        Quiz2.setOnMouseClicked(e -> {
            if (!doneQ2.isVisible()) {
                courseService.updateQuizCompletion(user,course.getQuizzes().get(1) );
                progressBar.setProgress(course.calculateProgress(user));
                
                doneQ2.setVisible(true);
            }
        });
        Quiz3.setOnMouseClicked(e -> {
            if (!doneQ3.isVisible()) {
                courseService.updateQuizCompletion(user,course.getQuizzes().get(2) );
                progressBar.setProgress(course.calculateProgress(user));
                
                doneQ3.setVisible(true);
            }
        });
        Quiz4.setOnMouseClicked(e -> {
            if (!doneQ4.isVisible()) {
                courseService.updateQuizCompletion(user,course.getQuizzes().get(3) );
                doneQ4.setVisible(true);
                progressBar.setProgress(course.calculateProgress(user));

            }
        });
        lessonName.setOnMouseClicked(e -> {
            if (!done.isVisible()) {
                courseService.updateMaterialCompletion(user, course.getMaterials().get(0));
                done.setVisible(true);
                progressBar.setProgress(course.calculateProgress(user));

            }
        });
        lessonName2.setOnMouseClicked(e -> {
            if (!done2.isVisible()) {
                courseService.updateMaterialCompletion(user, course.getMaterials().get(1));
                done2.setVisible(true);
                progressBar.setProgress(course.calculateProgress(user));

            }
        });
        lessonName3.setOnMouseClicked(e -> {
            
            if (!done3.isVisible()) {
                courseService.updateMaterialCompletion(user, course.getMaterials().get(2));
                progressBar.setProgress(course.calculateProgress(user));
                done3.setVisible(true);
            }

        });
        lessonName4.setOnMouseClicked(e -> {
            
            if (!done4.isVisible()) {
                courseService.updateMaterialCompletion(user, course.getMaterials().get(3));
                done4.setVisible(true);
                progressBar.setProgress(course.calculateProgress(user));

            }
        });
    }

    private void updateContent() {
        CourseName.setText(course.getCourseName());
        courseImage.setImage(course.getImage());
        courseDescription.setText(course.getCourseDescription());

        if (courseService.isMaterialCompleted(user, course.getMaterials().get(0))) {
            done.setVisible(true);
        }
        if (courseService.isMaterialCompleted(user, course.getMaterials().get(1))) {
            done2.setVisible(true);
        }
        if (courseService.isMaterialCompleted(user, course.getMaterials().get(2))) {
            done3.setVisible(true);
        }
        if (courseService.isMaterialCompleted(user, course.getMaterials().get(3))) {
            done4.setVisible(true);
        }
        
        if (courseService.isQuizCompleted(user, course.getQuizzes().get(0))) {
            doneQ1.setVisible(true);
        }
        if (courseService.isQuizCompleted(user, course.getQuizzes().get(1))) {
            doneQ2.setVisible(true);
        }
        if (courseService.isQuizCompleted(user, course.getQuizzes().get(2))) {
            doneQ3.setVisible(true);
        }
        if (courseService.isQuizCompleted(user, course.getQuizzes().get(3))) {
            doneQ4.setVisible(true);
        }
    }

}
