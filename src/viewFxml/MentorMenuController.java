package viewFxml;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.Account;
import service.AccountService;

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

    @FXML
    private Label nameLabel;

    private Account account;
    private AccountService accountService;


    
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

  
    public void setAccount(Account account,AccountService accountService){
        this.account = account;
        this.accountService = accountService;
    }

    public void InitialMenu(){
        openDashBoard();
        profileImage.setImage(account.getImage());
        nameLabel.setText(account.getName());
    }

    public void openDashBoard(){
        try {
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("MentorDashBoard.fxml"));
            Pane pane = loader.load();
            borderPane.setCenter(pane);

            MentorDashBoardController controller = loader.getController();
            controller.setAccount(account,accountService);
            controller.initialDashBoard();

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        logOut.setOnMouseClicked(e->{
            handleSignOut();
        });
        
    }

     private void handleSignOut() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
        try {
            Parent root = loader.load();
            loginController logincontroller = loader.getController();
            logincontroller.setAccountService(accountService);
            logincontroller.load();

            root.setClip(createRoundedRectangle(900, 600, 30));

            Scene scene = new Scene(root);
            scene.setFill(Color.TRANSPARENT);

            Image icon = new Image(getClass().getResourceAsStream("/Resources/logo.jpeg"));
            Stage stage = new Stage();
            stage.setScene(scene);


            stage.initStyle(StageStyle.TRANSPARENT);
            stage.setTitle("edu-Hub");
            stage.getIcons().add(icon);
            
            ((Stage) logOut.getScene().getWindow()).close();
            
            stage.show();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }


    private javafx.scene.shape.Rectangle createRoundedRectangle(double width, double height, double radius) {
        javafx.scene.shape.Rectangle rectangle = new javafx.scene.shape.Rectangle(width, height);
        rectangle.setArcWidth(radius);
        rectangle.setArcHeight(radius);
        return rectangle;
    }
}
