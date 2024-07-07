package viewFxml;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

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


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        btnCompleted.getStyleClass().add("inactive");
        displayMyCourse(inProgressCourseContainer);
        displayMyCourse(completedCourseContainer);
        displayMyCourse(completedCourseContainer);


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

    public void displayMyCourse(VBox container){
        FXMLLoader loader= new FXMLLoader(getClass().getResource("MyCourseLayout.fxml"));
        try {
            Parent root = loader.load();
            container.getChildren().add(root);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        
    }
}
