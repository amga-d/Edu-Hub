package viewFxml;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public class MentorMenuController implements Initializable{

     @FXML
    private BorderPane borderPane;

    @FXML
    private Pane chatButton;

    @FXML
    private Pane dashBoardButton;

    @FXML
    private Pane editProfileButton;

    @FXML
    private Pane logOut;

    @FXML
    private ImageView profileImage;

    @FXML
    private Pane settingButton;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            Pane pane = FXMLLoader.load(getClass().getResource("MentorDashBoard.fxml"));
            borderPane.setCenter(pane);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
