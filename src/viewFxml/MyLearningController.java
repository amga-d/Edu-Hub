package viewFxml;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;

public class MyLearningController implements Initializable {

    @FXML
    private Button btnCompleted;

    @FXML
    private Button btnInProgress;

    @FXML
    private ScrollPane completePane;

    @FXML
    private ScrollPane inProgressPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        btnCompleted.getStyleClass().add("inactive");

        // Toggle button states on click
        btnInProgress.setOnAction(event -> {
            btnInProgress.getStyleClass().add("active");
            btnInProgress.getStyleClass().remove("inactive");
            btnCompleted.getStyleClass().add("inactive");
            btnCompleted.getStyleClass().remove("active");
            completePane.setVisible(true);
            inProgressPane.setVisible(true);
        });

        btnCompleted.setOnAction(event -> {
            btnCompleted.getStyleClass().add("active");
            btnCompleted.getStyleClass().remove("inactive");
            btnInProgress.getStyleClass().add("inactive");
            btnInProgress.getStyleClass().remove("active");
            completePane.setVisible(true);
            inProgressPane.setVisible(false);

        });
    }
}
