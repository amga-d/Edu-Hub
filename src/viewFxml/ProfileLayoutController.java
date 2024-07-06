package viewFxml;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class ProfileLayoutController {

    @FXML
    private Button homeButton;

    @FXML
    private Button coursesButton;

    @FXML
    private Button profileButton;

    @FXML
    private Button settingsButton;

    @FXML
    private Button logoutButton;

    @FXML
    public void initialize() {
        homeButton.setOnAction(event -> handleHome());
        coursesButton.setOnAction(event -> handleCourses());
        profileButton.setOnAction(event -> handleProfile());
        settingsButton.setOnAction(event -> handleSettings());
        logoutButton.setOnAction(event -> handleLogout());
    }

    private void handleHome() {
        // Code to navigate to Home
    }

    private void handleCourses() {
        // Code to navigate to Courses
    }

    private void handleProfile() {
        // Code to navigate to Profile
    }

    private void handleSettings() {
        // Code to navigate to Settings
    }

    private void handleLogout() {
        // Code to handle Logout
    }
}
