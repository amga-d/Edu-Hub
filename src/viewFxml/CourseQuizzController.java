package viewFxml;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class CourseQuizzController {

    @FXML
    private Label courseTitleLabel;

    @FXML
    private ProgressBar courseProgressBar;

    @FXML
    private ImageView courseImageView;

    @FXML
    private Label courseDescriptionLabel;

    @FXML
    private Label courseLongDescriptionLabel;

    @FXML
    private ImageView quizzesImageView;

    @FXML
    public void initialize() {
        // Initialize the UI components here

        // Set the course title
        courseTitleLabel.setText("Mathematics");

        // Set the progress bar value
        courseProgressBar.setProgress(0.0);

        // Load the course image
        Image courseImage = new Image(getClass().getResourceAsStream("/Resources/courses_Images/Calculus.png"));
        courseImageView.setImage(courseImage);

        // Set the course description
        courseDescriptionLabel.setText("Course Description:");

        // Set the long course description
        courseLongDescriptionLabel.setText("This introductory course in calculus is designed to provide students with a fundamental understanding of the concepts and techniques of differential and integral calculus. Topics covered include function, limits, differential, and integrals. Students will learn to apply these concepts to solve real-world problems in fields such as physics, engineering, economics, and biology.");

        // Load the quizzes image
        Image quizzesImage = new Image(getClass().getResourceAsStream("/Resources/quizzes_Images/Math.png"));
        quizzesImageView.setImage(quizzesImage);
    }
}

