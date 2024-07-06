package viewFxml;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import service.AccountService;

public class loginController implements Initializable {
    @FXML
    VBox vBox;
    private Parent pane;
    private FXMLLoader loader;
    private TranslateTransition t;
    private AccountService accountService;

    @FXML
    public void openSignin(ActionEvent event) {
        moveVbox(0, "SignIn.fxml");
        SignInController signInController = loader.getController();
        signInController.setAccountService(accountService);
    }

    @FXML
    public void openSignup(ActionEvent event) {
        moveVbox(380, "SignUp.fxml");
        SignUpController signUpController = loader.getController();
        signUpController.setAccountService(accountService);
    }

    private void moveVbox(double x, String resources) {
        t.setToX(x);
        t.play();

        try {
            this.loader = new FXMLLoader(getClass().getResource(resources));
            pane = loader.load();
            vBox.getChildren().removeAll();
            vBox.getChildren().setAll(pane);
        } catch (IOException e1) {
            e1.printStackTrace();
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        t = new TranslateTransition(Duration.millis(700), vBox);
        try {
            this.loader = new FXMLLoader(getClass().getResource("SignIn.fxml"));
            pane = loader.load();
            vBox.getChildren().setAll(pane);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    public void load(){
        SignInController signInController = loader.getController();
        signInController.setAccountService(accountService);
    }
    
    public void setAccountService(AccountService accountService) {
        this.accountService = accountService;
    }

}
