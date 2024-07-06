package viewFxml;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
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
    }
}
