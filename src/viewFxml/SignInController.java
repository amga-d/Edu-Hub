package viewFxml;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.stage.Stage;
import model.Account;
import model.Admin;
import model.Instructor;
import model.User;
import service.AccountService;

public class SignInController implements Initializable {
    @FXML
    private Label hello;
    @FXML
    private Label welcome;
    @FXML
    private Label email;
    @FXML
    private Label password;
    @FXML
    private TextField emailField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Label errorLabel;
    @FXML
    private AccountService accountService;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        LinearGradient linearGradient = new LinearGradient(
                0, 0, 1, 0, // startX, startY, endX, endY
                true, // proportional
                CycleMethod.NO_CYCLE,
                new Stop(0, Color.web("#0D1925")),
                new Stop(0.4972, Color.web("#059379")));

        hello.setTextFill(linearGradient);
        welcome.setTextFill(linearGradient);
        password.setTextFill(linearGradient);
        email.setTextFill(linearGradient);

        setChange(emailField, email);
        setChange(passwordField, password);
    }

    private void setChange(TextField field, Label lable){
        field.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.isEmpty()) {
                field.setStyle("-fx-opacity: 0.5;");
                lable.setStyle("-fx-opacity: 0.5;");
            } else {
                field.setStyle("-fx-opacity: 1.0;");
                lable.setStyle("-fx-opacity: 1.0;");
            }
        });
        field.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                field.setStyle("-fx-opacity: 1.0;");
                lable.setStyle("-fx-opacity: 1.0;");
            } else {
                if (field.getText().isEmpty()) {
                    field.setStyle("-fx-opacity: 0.5;");
                    lable.setStyle("-fx-opacity: 0.5;");
                }
            }
        });
        
    }

    public void openMainPage(Account account) {
        Parent root;
        if (account instanceof User){
            try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("Main.fxml"));
            root = loader.load();
            MainController mainController = loader.getController();
            mainController.setAccount(account,accountService);
            openStage(root);

        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
        }
        else if(account instanceof Instructor){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("MentorMenu.fxml"));
            try {
                root = loader.load();
                MentorMenuController controller = loader.getController();
                controller.setAccount(account,accountService);
                controller.InitialMenu();
                openStage(root);
            } catch (IOException e) {

                e.printStackTrace();
            }



        }
        else{
            System.out.println("Account is not a user or instructor");
        }

                    
    }

    private void openStage(Parent root){  
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setFullScreen(true);
        stage.setScene(scene);
        getStage().close();
        stage.show();}

    
    private Stage getStage() {
        Stage stage = (Stage) password.getScene().getWindow();
        return stage;
    }

    @FXML
    public void signIn(ActionEvent e) {
        String email = emailField.getText();
        String password = passwordField.getText();

        if (email.isEmpty() || password.isEmpty()) {
            errorLabel.setText("Username and password must not be empty.");
        }

        else if (accountService.authenticate(email, password)) {
            errorLabel.setText("");
            try {
                openMainPage(accountService.getAccountByEmail(email));
            } catch (Exception e1) {
                System.err.println("an error occur 'Account not found'"+e1.getMessage());
            }
        } else {
            errorLabel.setText("Invalid username or password.");
        }
    }

    public void setAccountService(AccountService accountService) {
        this.accountService = accountService;
    }
}
